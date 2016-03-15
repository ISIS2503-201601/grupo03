/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitesos.satt.servicios;

import dto.Boletin;
import dto.Evento;
import dto.Sensor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logica.interfaces.IServicioBoletinLocal;
import logica.interfaces.IServicioMonitoreoLocal;

/**
 *
 * @author jf.ceron10
 */
@Path("/boletin")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BoletinService {
    /**
     * Referencia al Ejb
     */
    @EJB
    private IServicioBoletinLocal boletinEjb;
    
    @POST
    @Path("generar/")
    public Boletin generarBoletin(Evento e) {
        return boletinEjb.generarBoletin(e);
    }
}
