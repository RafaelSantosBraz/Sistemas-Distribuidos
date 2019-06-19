/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testandorestfull;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.classes.Alunos;

/**
 *
 * @author DELL-Fabio
 */
public class consultar {
    public static void main(String[] args) {
        // TODO code application logic here    
        Alunos a = new Alunos();
        
        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/WebRestSD/webresources/Alunos/2");
        String response = wr.get(String.class);  
        Gson gson = new Gson();
        a = gson.fromJson(response, Alunos.class );
                
        System.out.println(a.getNome());                
    }      
}
