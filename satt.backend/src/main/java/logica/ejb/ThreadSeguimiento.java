/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.ejb;

import dto.Sensor;
import dto.Zona;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.interfaces.IServicioPersistenciaLocal;

/**
 *
 * @author jf.ceron10
 */
public class ThreadSeguimiento extends Thread {
    
    private IServicioPersistenciaLocal persistencia;
    int indiceSensor;
    double distancia;

    /**
     *
     * @param persistencia
     */
    public ThreadSeguimiento (IServicioPersistenciaLocal persistencia, int indiceSensor, double distancia) {
        this.persistencia=persistencia;
        this.indiceSensor=indiceSensor;
        this.distancia=distancia;
        start();
    }
    
    @Override
    public void run() {
        
        for (int i=0;i<20;i++) {
            
            ArrayList<Sensor> sensores=(ArrayList<Sensor>) persistencia.findAll(Sensor.class);
            Sensor cercano=sensores.get(indiceSensor);

            //Se deduce el tiempo de llegada a la costa
            double tiempoLlegada=distancia/cercano.getVelocidad();

            //Se determina el perfil de alerta dados los parametros y zona
            int urgencia=0;
            Zona zona=(Zona)persistencia.findById(Zona.class, cercano.getZona());
            if (tiempoLlegada<=zona.gettMax()) urgencia++;
            if (cercano.getAltura()>=zona.getAlturaMin()) urgencia++;
            if (zona.getNombre().startsWith("P")) urgencia++;
            else if (cercano.getAltura()>=zona.getAlturaMin()*1.5) urgencia++;
            
            try {
                //Se envia la informacion a la Direccion de Gestion del Riesgo

            //Se esperan 5 minutos antes de verificar si es necesaria una actualizacion
            sleep(5*60);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            distancia-=cercano.getVelocidad()*5*60;
            
            //Se verifica la diferencia con la anterior altura
            double alturaAnterior=cercano.getAltura();
            sensores=(ArrayList<Sensor>) persistencia.findAll(Sensor.class);
            cercano=sensores.get(indiceSensor);
            
            if (Math.abs(alturaAnterior-cercano.getAltura())<=1.5) break;
        }
    }
}
