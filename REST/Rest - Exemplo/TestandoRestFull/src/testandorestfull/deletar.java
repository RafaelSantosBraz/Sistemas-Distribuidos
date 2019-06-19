/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testandorestfull;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 *
 * @author DELL-Fabio
 */
public class deletar {
    public static void main(String[] args) {
        // TODO code application logic here         
        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/WebRestSD/webresources/Alunos/2");
        String response = wr.delete(String.class);  
                
        System.out.println(response);   
    }
}
