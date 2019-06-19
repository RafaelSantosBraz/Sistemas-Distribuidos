package testandorestfull;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.classes.Alunos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL-Fabio
 */
public class atualizar {
    public static void main(String[] args) {
        // TODO code application logic here    
        Alunos a = new Alunos();
        a.setIdaluno(1);
        a.setNome("alex");
        a.setIdade(30);
        a.setRA("449");

        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/WebRestSD/webresources/Alunos");
        
        Gson gson = new Gson();
        String response = wr.type("application/json")
		   .post(String.class, gson.toJson(a));        
                
        System.out.println(response);                
    }  
}
