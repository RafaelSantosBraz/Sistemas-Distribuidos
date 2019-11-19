/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ws;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
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

import org.DAO.AlunoDAO;
import org.classes.Aluno;

/**
 * REST Web Service
 *
 * @author DELL-Fabio
 */
@Path("aluno")
public class AlunoResource {

    private Gson gson = new Gson();    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AlunoResource
     */
    public AlunoResource() {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultar/{idaluno}")    
    public String getAluno(@PathParam("idaluno") int idaluno) {
        //TODO return proper representation object
        Aluno a = new Aluno();
        AlunoDAO dao = new AlunoDAO();
        try{
            a = dao.retornaAluno(idaluno);
            return gson.toJson(a);
        }catch (Exception e) {
            return "";
        }
    }
        
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaAluno")    
    public String getAluno() {
        //TODO return proper representation object
        List<Aluno> lista = new ArrayList();
        AlunoDAO dao = new AlunoDAO();
        try{
            lista = dao.listaAluno();
            return gson.toJson(lista);
        }catch (Exception e) {
            return "não deu bom";
        }
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public String alterar(String dados) {
        String erro = "ok";
        AlunoDAO dao = new AlunoDAO();
        try {
            Aluno a = gson.fromJson(dados, Aluno.class );
            erro = dao.alterar(a);
            
        } catch (Exception e) {
           erro = e.getMessage();
        }
        finally
        {
            return erro;
        }
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public String inserir(String dados) {
        String erro = "ok";
        AlunoDAO dao = new AlunoDAO();
        try {
            Aluno a = gson.fromJson(dados, Aluno.class );
            erro = dao.inserir(a);
            
        } catch (Exception e) {
           erro = e.getMessage();
        }
        finally
        {
            return erro;
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("remove/{idaluno}")
    public String deletar(@PathParam("idaluno") int idaluno) {
        //TODO return proper representation object
        
        AlunoDAO dao = new AlunoDAO();
        String retorno = "não deu bom";
        try{
            int res = dao.delete(idaluno);  
            if (res == 1)
            {
                retorno = "deu bom";
            }
        }catch (Exception e) {
           e.getMessage();
        } 
        return retorno;
    }    
    
}
