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
import javax.swing.JOptionPane;
import org.classes.Alunos;
import org.classes.Counter;

/**
 *
 * @author DELL-Fabio
 */
public class inserir {

    public static void main(String[] args) {
        // TODO code application logic here
        Alunos a = new Alunos();
        a.setNome("fábio");
        a.setIdade(33);
        a.setRA("888");

        Counter contador = new Counter();

        Client c = Client.create();

        WebResource wr = c.resource("https://fir-aula-teste.firebaseio.com/alunos/" + contador.getContador() + ".json");
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(a));
        JOptionPane.showMessageDialog(null, response);
        /*
        Gson gson = new Gson();
        
        String response = wr.type("application/json")
		   .post(String.class, gson.toJson(a));                
         */
        System.out.println(response);
    }
}
