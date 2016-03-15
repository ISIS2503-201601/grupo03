/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.ejb;

import dto.Boletin;
import dto.Coordenadas;
import dto.Evento;
import dto.Sensor;
import dto.Zona;
import java.util.ArrayList;
import java.util.List;
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
    public Evento generarEvento(Evento e) {
        //Se introduce el evento a la base de datos
        try{persistencia.create(e);}
        catch (Exception ex) {ex.printStackTrace();}
        
        double[][] coordenadas= new double[4000][2];
        for (int i=0;i<4000;i++) {
            coordenadas[i][0]=0.0025*i;
            coordenadas[i][1]=0.0025*i;
        }
        double latEvento=e.getLatitud();
        double lonEvento=e.getLongitud();
        
        //Se localiza el sensor mas cercano al evento
        double distanciaMinima=Integer.MAX_VALUE;
        double latCercano=0;
        double lonCercano=0;
        
        for(int i=0;i<4000;i++) {
            double dist=distancia(coordenadas[i][0], latEvento, coordenadas[i][1], lonEvento);
            
            if (dist<distanciaMinima) 
            {
                distanciaMinima=dist;
                latCercano=coordenadas[i][0];
                lonCercano=coordenadas[i][1];
            }
        }
        
        //Se consigue el ultimo registro del sensor mas cercano
        Sensor cercano=(Sensor) persistencia.findById(Sensor.class, new Coordenadas(latCercano,lonCercano));
        
        //Se inicializa un Thread que se encarga del seguimiento
        new ThreadSeguimiento(persistencia, cercano, distanciaMinima);
        
        //Finalmente se retorna el evento como confirmacion de la ejecucion
        return e;
    }
    
    
    
    private double distancia (double lat1, double lat2, double lon1, double lon2) {
        double sec1 = Math.sin(lat1)*Math.sin(lat2);
        double dl=Math.abs(lon1-lon2);
        double sec2 = Math.cos(lat1)* Math.cos(lat2);
        double centralAngle = Math.acos(sec1+sec2*Math.cos(dl));
        return  centralAngle * 6378.1;
    }

    @Override
    public List<Boletin> getBoletines() {
        
        return persistencia.findAll(Boletin.class);
    }

    @Override
    public List<Evento> getEventos() {
        return persistencia.findAll(Evento.class);
    }
    
}
