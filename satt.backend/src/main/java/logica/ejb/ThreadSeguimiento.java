/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.ejb;

import dto.Boletin;
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
public class ThreadSeguimiento extends Thread {private IServicioPersistenciaLocal persistencia;
    private Sensor cercano;
    private double distancia;
    private boolean monitoreo;
    private boolean primera;

    /**
     *
     * @param persistencia
     */
    public ThreadSeguimiento (IServicioPersistenciaLocal persistencia, Sensor cercano, double distancia) {
        this.persistencia=persistencia;
        this.cercano=cercano;
        this.distancia=distancia;
        monitoreo=true;
        primera=true;
        start();
    }
    
    @Override
    public void run() {
        
        double ultimaAltura=0;
        while (monitoreo) {
            //Se deduce el tiempo de llegada a la costa
            double tiempoLlegada=distancia/cercano.getVelocidad();
            
            //Se determina el perfil de alerta dados los parametros y zona
            int urgencia=0;
            Zona zona=(Zona)persistencia.findById(Zona.class, cercano.getZona());
            if (tiempoLlegada<=zona.gettMax()) urgencia++;
            if (cercano.getAltura()>=zona.getAlturaMin()) urgencia++;
            if (zona.getNombre().startsWith("P")) urgencia++;
            else if (cercano.getAltura()>=zona.getAlturaMin()*1.5) urgencia++;
            
            String perfil="";
            if (urgencia==0) perfil="informativo";
            else if (urgencia==1) perfil="precaucion";
            else if (urgencia==2) perfil="alerta";
            else perfil="alarma";
            
            if (Math.abs(ultimaAltura-cercano.getAltura())>1.5 || primera) {
                ultimaAltura=cercano.getAltura();
                Boletin boletin=new Boletin(perfil, zona.getNombre(), tiempoLlegada, ultimaAltura);
                try {
                    //Se envia la informacion a la Direccion de Gestion del Riesgo
                    persistencia.create(boletin);

                    //Se esperan 5 minutos antes de verificar si es necesaria una actualizacion
                    sleep(5*60);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                distancia-=cercano.getVelocidad()*5*60;
                primera=false;
            }
            else monitoreo=false;
        }
    }
}
