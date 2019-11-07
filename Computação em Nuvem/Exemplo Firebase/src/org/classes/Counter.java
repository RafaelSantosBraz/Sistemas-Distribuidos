/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.classes;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import javax.swing.JOptionPane;

/**
 *
 * @author Fábio Moreno
 */
public class Counter {
    private int contador = 0;
    
    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public int getContador(){     
        Client c = Client.create();
        WebResource wr = c.resource("https://fir-aula-teste.firebaseio.com/Counter.json");
        String response = wr.get(String.class);
        Gson gson = new Gson();
        if (response.equals("null")){
            inicializacontador();
        } else
        {
            this.contador = gson.fromJson(response, Integer.class);
        }
        contador++;        
        response = wr.type("application/json").put(String.class, gson.toJson(contador));
        System.out.println(response);  
        return contador;
    }
    
    public void inicializacontador()
    {
        Client c = Client.create();      
        
        WebResource wr = c.resource("https://fir-aula-teste.firebaseio.com/Counter.json");
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(contador));
        System.out.println(response);  
    }
    



}
