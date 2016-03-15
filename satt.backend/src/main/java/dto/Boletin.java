/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author jf.ceron10
 */

@NoSql(dataFormat=DataFormatType.MAPPED)
@Entity
@XmlRootElement
public class Boletin implements Serializable{
     private static final long serialVersionUID = 1L;
    @Id
 	@GeneratedValue
 	@Field(name="_id")
 	private String id;
    
    @NotNull
    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdAt;
    
      @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Calendar updatedAt;
      
        @PreUpdate
    private void updateTimestamp() {
        this.updatedAt = Calendar.getInstance();
    }
 
    @PrePersist
    private void creationTimestamp() {
        this.createdAt = this.updatedAt = Calendar.getInstance();
    }
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
    
    public void nada(){}
    private String perfilAlerta;
    private String zona;
    private double tiempoLlegada;
    private double altura;
}
