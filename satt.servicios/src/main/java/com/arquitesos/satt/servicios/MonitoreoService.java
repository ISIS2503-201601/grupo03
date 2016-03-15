/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitesos.satt.servicios;

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
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.interfaces.IServicioMonitoreoLocal;
import org.json.simple.JSONObject;
import persistencia.PersistenceManager;

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
    @Path("actualizar/")
     @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(List<Sensor> sp) {
       // monitoreoEjb.actualizarSensor(s);
       
          JSONObject rta = new JSONObject();
          Sensor s= sp.get(0);
          Sensor sen;
          sen= new Sensor(s.getZona(), s.getLatitud(), s.getLongitud(), s.getAltura(), s.getVelocidad());
           try {
           // entityManager.getTransaction().begin();
            entityManager.persist(sen);
           // entityManager.getTransaction().commit();
           // entityManager.refresh(sen);
            rta.put("competitor_id", sen.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            //if (entityManager.getTransaction().isActive()) {
              //  entityManager.getTransaction().rollback();
            //}
            sen= null;
        } finally {
        	entityManager.clear();
        	entityManager.close();
        }monitoreoEjb.actualizarSensor(sen);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
       
    }
    
    @GET
    @Path("sensores/")
    public List<Sensor> getSensores() {
        Query q = entityManager.createQuery("select u from Sensor");
        List<Sensor> sensores = q.getResultList();
        //Response res= Response.status(200).header("Access-Control-Allow-Origin", "*").entity(Sensor).build();
        return monitoreoEjb.getSensores();
    }
}