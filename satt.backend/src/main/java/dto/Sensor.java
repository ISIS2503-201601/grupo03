/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author jf.ceron10
 */public class Sensor {
    private String zona;
    private double latitud;
    private double longitud;
    /*
    Altura y velocidad de la ultima ola registrada por el sensor
    */
    private double altura;
    private double velocidad;

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public Sensor(String zona, double latitud, double longitud, double altura, double velocidad) {
        this.zona = zona;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altura = altura;
        this.velocidad = velocidad;
    }
    
    public Sensor(){
        super();
    }
}
