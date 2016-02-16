/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitesos.satt.servicios;

import dto.Sensor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logica.interfaces.IServicioMonitoreoLocal;

/**
 *
 * @author jf.ceron10
 */
@Path("/monitoreo")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MonitoreoService {
    /**
     * Referencia al Ejb
     */
    @EJB
    private IServicioMonitoreoLocal monitoreoEjb;
    
    @PUT
    @Path("actualizar/")
    public Sensor actualizar(Sensor s) {
        monitoreoEjb.actualizarSensor(s);
        
        return s;
    }
    
    @GET
    @Path("sensores/")
    public List<Sensor> getSensores() {
        return monitoreoEjb.getSensores();
    }
}