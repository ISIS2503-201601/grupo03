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
public class Boletin {

    public Boletin(String perfilAlerta, String zona, double tiempoLlegada, double altura) {
        this.perfilAlerta = perfilAlerta;
        this.zona = zona;
        this.tiempoLlegada = tiempoLlegada;
        this.altura = altura;
    }
    
    public Boletin () {
        super();
    }

    public String getPerfilAlerta() {
        return perfilAlerta;
    }

    public void setPerfilAlerta(String perfilAlerta) {
        this.perfilAlerta = perfilAlerta;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public double getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(double tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
    private String perfilAlerta;
    private String zona;
    private double tiempoLlegada;
    private double altura;
}
