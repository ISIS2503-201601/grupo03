/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.ejb;

import dto.Boletin;
import dto.Evento;
import dto.Sensor;
import dto.Zona;
import java.util.ArrayList;
import javax.ejb.Stateless;
import logica.interfaces.IServicioBoletinLocal;
import logica.interfaces.IServicioBoletinRemote;
import logica.interfaces.IServicioPersistenciaLocal;
import persistencia.ServicioPersistencia;

/**
 *
 * @author jf.ceron10
 */
@Stateless
public class ServicioBoletin implements IServicioBoletinLocal, IServicioBoletinRemote {

    private IServicioPersistenciaLocal persistencia;
    
    public ServicioBoletin () {
        persistencia=new ServicioPersistencia();
    }
    
    @Override
    public Boletin generarBoletin(Evento e) {
        ArrayList sensores=(ArrayList) persistencia.findAll(Sensor.class);
        double latEvento=e.getLatitud();
        double lonEvento=e.getLongitud();
        
        //Se localiza el sensor mas cercano al evento
        double distanciaMinima=Integer.MAX_VALUE;
        int indice =0;
        for(int i=0;i<sensores.size();i++) {
            Sensor s=(Sensor)sensores.get(i);
            double latSensor=s.getLatitud();
            double lonSensor=s.getLongitud();
            double dist=distancia(latSensor, latEvento, lonSensor, lonEvento);
            
            if (dist<distanciaMinima) 
            {
                distanciaMinima=dist;
                indice=i;
            }
        }
        Sensor cercano=(Sensor)sensores.get(indice);

        System.out.println(cercano==null);
        
        //Se deduce el tiempo de llegada a la costa
        double tiempoLlegada=distanciaMinima/cercano.getVelocidad();

        //Se determina el perfil de alerta dados los parametros y zona
        int urgencia=0;
        Zona zona=(Zona)persistencia.findById(Zona.class, cercano.getZona());
        
        System.out.println(zona==null);
        
        if (tiempoLlegada<=zona.gettMax()) urgencia++;
        if (cercano.getAltura()>=zona.getAlturaMin()) urgencia++;
        if (zona.getNombre().startsWith("P")) urgencia++;
        else if (cercano.getAltura()>=zona.getAlturaMin()*1.5) urgencia++;

        String perfil="";
        if (urgencia==0) perfil="informativo";
        else if (urgencia==1) perfil="precaucion";
        else if (urgencia==2) perfil="alerta";
        else perfil="alarma";
        
        String zonaGeneral="";
        if (zona.getNombre().startsWith("P")) zonaGeneral="Pacifico";
        else zonaGeneral="Atlantico";
        
        //Se inicializa un Thread que se encarga del seguimiento
        //new ThreadSeguimiento(persistencia, indice, distanciaMinima);
        
        //Finalmente se retorna el resultado
        return (new Boletin(perfil, zonaGeneral, tiempoLlegada, cercano.getAltura()));
    }
    
    
    
    private double distancia (double lat1, double lat2, double lon1, double lon2) {
        double sec1 = Math.sin(lat1)*Math.sin(lat2);
        double dl=Math.abs(lon1-lon2);
        double sec2 = Math.cos(lat1)* Math.cos(lat2);
        double centralAngle = Math.acos(sec1+sec2*Math.cos(dl));
        return  centralAngle * 6378.1;
    }
    
}
