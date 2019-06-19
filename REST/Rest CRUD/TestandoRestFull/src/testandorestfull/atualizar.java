package testandorestfull;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.classes.Aluno;

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
        Aluno a = new Aluno();
        a.setId(6);
        a.setNome("alex");
        a.setIdade(30);
        a.setSenha("bbb");
        a.setEmail("bbb");

        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/AlunoWS2/webresources/aluno");
        
        Gson gson = new Gson();
        String response = wr.type("application/json")
		   .post(String.class, gson.toJson(a));        
                
        System.out.println(response);                
    }  
}
