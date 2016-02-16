/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author jf.ceron10
 */
public class Evento {

    private long latitud;
    private long longitud;
    private long distancia;

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public long getDistancia() {
        return distancia;
    }

    public void setDistancia(long distancia) {
        this.distancia = distancia;
    }

    public Evento(long latitud, long longitud, long distancia) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.distancia = distancia;
    }
    
    public Evento() {
        super();
    }
}
