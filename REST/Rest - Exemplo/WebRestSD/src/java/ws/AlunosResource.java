/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import classes.Alunos;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


/**
 * REST Web Service
 *
 * @author Fábio Moreno
 */
@Path("Alunos")
public class AlunosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AlunosResource
     */
    public AlunosResource() {
    }

    //Retorna Aluno pelo id
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getAluno(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        //TODO return proper representation object
        Alunos a = new Alunos();
        a.getAluno(id);
        Gson json = new Gson();
        String resultado = json.toJson(a);
        return resultado;
    }
  
    //Retorna Lista de Alunos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAlunos() throws SQLException, ClassNotFoundException {
        //TODO return proper representation object
        ArrayList<Alunos> lista;
        Alunos a = new Alunos();
        lista = a.getAlunos();
        Gson json = new Gson();
        String resultado = json.toJson(lista);
        return resultado;
    }

    //Retorna Aluno pelo nome
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/nome/{nome}")
    public String getAlunoNome(@PathParam("nome") String nome) throws SQLException, ClassNotFoundException {
        //TODO return proper representation object
        Alunos a = new Alunos();
        ArrayList<Alunos> lista = a.getAlunoNome(nome);
        Gson json = new Gson();
        String resultado = json.toJson(a);
        return resultado;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postAluno(String content) throws SQLException{
        Gson json = new Gson();
        Alunos a = json.fromJson(content, Alunos.class);
        return a.inserir();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String deletar(@PathParam("id") int idaluno) throws SQLException {
        //TODO return proper representation object
        Alunos a = new Alunos();
        return a.delete(idaluno);
    }    
    
    /**
     * PUT method for updating or creating an instance of AlunosResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String putAluno(String content) throws SQLException {
        Gson json = new Gson();
        Alunos a = json.fromJson(content, Alunos.class);
        return a.alterar();
    }
}
