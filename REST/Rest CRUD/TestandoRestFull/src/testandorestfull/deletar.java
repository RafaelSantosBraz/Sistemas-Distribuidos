/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testandorestfull;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.classes.Aluno;

/**
 *
 * @author DELL-Fabio
 */
public class deletar {
    public static void main(String[] args) {
        // TODO code application logic here         
        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/AlunoWS2/webresources/aluno/remove/7");
        String response = wr.delete(String.class);  
                
        System.out.println(response);   
    }
}
