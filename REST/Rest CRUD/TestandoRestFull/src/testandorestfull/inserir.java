/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testandorestfull;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.classes.Aluno;

/**
 *
 * @author DELL-Fabio
 */
public class inserir {

    public static void main(String[] args) {
        // TODO code application logic here
        Aluno a = new Aluno();
        a.setNome("fabio");
        a.setIdade(20);
        a.setSenha("abc");
        a.setEmail("abc");

        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/AlunoWS2/webresources/aluno/inserir");
        
        Gson gson = new Gson();
        String response = wr.type("application/json")
		   .put(String.class, gson.toJson(a));                
        System.out.println(response);                
    }  
}
