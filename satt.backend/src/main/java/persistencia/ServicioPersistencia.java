/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dto.Boletin;
import dto.Coordenadas;
import dto.Evento;
import dto.Sensor;
import dto.Zona;
import java.util.ArrayList;
import java.util.List;
import logica.interfaces.IServicioPersistenciaLocal;
import logica.interfaces.IServicioPersistenciaRemote;

/**
 *
 * @author jf.ceron10
 */
public class ServicioPersistencia implements IServicioPersistenciaLocal, IServicioPersistenciaRemote {

    private static ArrayList<Zona> zonas;
    private static ArrayList<Sensor> sensores;
    private static ArrayList<Evento> eventos;
    private static ArrayList<Boletin> boletines;
    
    public ServicioPersistencia() {
        if (sensores==null) {
            sensores=new ArrayList<Sensor>();
            
            //Creacion de sensores 
            
            for (int i=0;i<4000;i++) {
                String zona="";
                if (i<444) zona="A_Guajira";
                else if (i<444*2) zona="A_Magdalena";
                else if (i<444*3) zona="A_Sucre";
                else if (i<444*4) zona="A_Cordoba";
                else if (i<444*5) zona="A_Antioquia";
                else if (i<444*6) zona="P_Choco";
                else if (i<444*7) zona="P_Valle";
                else if (i<444*8) zona="P_Cauca";
                else zona="P_Narino";
                sensores.add(new Sensor(zona,0.0025*i, 0.0025*i, 0, 0));
            }
        }
        
        if (zonas==null) {
            zonas=new ArrayList<Zona>();
            
            //Creacion de las zonas
            zonas.add(new Zona("A_Guajira", 6000, 5));
            zonas.add(new Zona("A_Magdalena", 5000, 8));
            zonas.add(new Zona("A_Sucre", 6000, 14.5));
            zonas.add(new Zona("A_Cordoba", 5000, 16));
            zonas.add(new Zona("A_Antioquia", 10000, 10));
            zonas.add(new Zona("P_Choco", 8000, 5));
            zonas.add(new Zona("P_Valle", 9000, 11));
            zonas.add(new Zona("P_Cauca", 7500, 13));
            zonas.add(new Zona("P_Narino", 16000, 13.12));
        }
        
        if (eventos==null) eventos=new ArrayList<Evento>();
        if (boletines==null) boletines=new ArrayList<Boletin>();
    }
    
    @Override
    public void create(Object obj) throws Exception {
        if (obj instanceof Sensor) sensores.add((Sensor)obj);
        else if (obj instanceof Evento) eventos.add((Evento)obj);
        else if (obj instanceof Boletin) boletines.add((Boletin)obj);
        else if (obj instanceof Zona) zonas.add((Zona)obj);
    }

    @Override
    public void update(Object obj) {
        if (obj instanceof Sensor ) {
            Sensor s1=(Sensor)obj;
            for (int i=0; i<sensores.size(); i++) {
                Sensor s2=sensores.get(i);
                if (s1.getLatitud()==s2.getLatitud()&& s1.getLongitud()==s2.getLongitud()) {
                    sensores.set(i, s1);
                    break;
                }
            }
        }
        else throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List findAll(Class c) {
        if (c.equals(Sensor.class)) return sensores;
        else if (c.equals(Zona.class)) return zonas;
        else if (c.equals(Boletin.class)) return boletines;
        else if (c.equals(Evento.class)) return eventos;
        return new ArrayList();
    }

    @Override
    public Object findById(Class c, Object id) {
        if (c.equals(Sensor.class)) {
            //SOLO DEBE DEVOLVER EL REGISTRO MAS RECIENTE
            //DEL SENSOR EN ESAS COORDENADAS
            for (Sensor s : sensores) {
                Coordenadas coor=(Coordenadas)id;
                if (s.getLatitud()==coor.getLatitud()&&
                    s.getLongitud()==coor.getLongitud()) return s;
            }
        }
        else if (c.equals(Zona.class)) {
            for (Zona z : zonas) {
                if (z.getNombre().equals((String)id)) return z;
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
