/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import classses.Cliente;
import com.google.gson.Gson;
import controller.ControllerBanco;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Rafael Braz
 */
@Path("Banco")
public class BancoResource {
    
    private final ControllerBanco controller;
    private final Gson conversorJson;
    
    public BancoResource() {
        controller = new ControllerBanco("localhost", "3306", "banco", "mysql", "mysql");
        conversorJson = new Gson();
    }
    
    @Context
    private UriInfo context;

    /**
     * Retrieves representation of an instance of ws.BancoResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of BancoResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultarCadastro/{CPF}")
    public String consultarCadastro(@PathParam("CPF") String CPF) {        
        return conversorJson.toJson(controller.consultarCadastro(CPF));
    }
    
}
