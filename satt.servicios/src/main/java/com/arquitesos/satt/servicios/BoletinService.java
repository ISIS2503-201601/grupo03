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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logica.interfaces.IServicioBoletinLocal;
import logica.interfaces.IServicioMonitoreoLocal;
import persistencia.PersistenceManager;

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
    @PersistenceContext(unitName = "mongoPU")
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @POST
    @Path("generar/")
    public Evento generarEvento(Evento e) {
        return boletinEjb.generarEvento(e);
    }
    
    @GET
    @Path("boletines/")
    public List<Boletin> getBoletines() {
        Query q = entityManager.createQuery("select u from Boletin");
        List<Boletin> boletines = q.getResultList();
        return boletinEjb.getBoletines();
    }
    
    @GET
    @Path("eventos/")
    public List<Evento> getEventos() {
        Query q = entityManager.createQuery("select u from Evento");
        List<Sensor> eventos = q.getResultList();
        return boletinEjb.getEventos();
    }
}
