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
import org.classes.Alunos;

/**
 *
 * @author DELL-Fabio
 */
public class inserir {

    public static void main(String[] args) {
        // TODO code application logic here
        Alunos a = new Alunos();
        a.setNome("fabio");
        a.setIdade(20);
        a.setRA("567");

        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/WebRestSD/webresources/Alunos");
        
        Gson gson = new Gson();
        String response = wr.type("application/json")
		   .put(String.class, gson.toJson(a));                
        System.out.println(response);                
    }  
}
