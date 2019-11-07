/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import classes.Aluno;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author cc47192962899
 */
@Path("Aluno")
public class AlunoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AlunoResource
     */
    public AlunoResource() {
    }

    /**
     * Retrieves representation of an instance of ws.AlunoResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getNome() {
        return "Braz";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{nome}")
    public String getNumero(@PathParam("nome") String nome) {
        return "Olá " + nome;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{num1},{num2}")
    public int getNumero(@PathParam("num1") int num1, @PathParam("num2") int num2) {
        return num1 + num2;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/retorna")
    public String getAluno() {
        Aluno a = new Aluno();
        a.setNome("Braz");
        a.setIdade(20);
        Gson g = new Gson();
        return g.toJson(a);
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String puty(String content) {
        Aluno a;
        Gson g = new Gson();
        a = g.fromJson(content, Aluno.class);
        return a.getNome();
    }

    /**
     * PUT method for updating or creating an instance of AlunoResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
