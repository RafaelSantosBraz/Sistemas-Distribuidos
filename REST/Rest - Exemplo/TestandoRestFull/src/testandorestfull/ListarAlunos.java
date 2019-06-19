/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testandorestfull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;
import org.classes.Alunos;

/**
 *
 * @author DELL-Fabio
 */
public class ListarAlunos {
    public static void main(String[] args) {
        // TODO code application logic here    
        List<Alunos> lista = new ArrayList();
        java.lang.reflect.Type listType = new TypeToken<ArrayList<Alunos>>(){}.getType();
        
        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/WebRestSD/webresources/Alunos");
        String response = wr.get(String.class);  
        Gson gson = new Gson();
        lista = gson.fromJson(response, listType );
                
        for (int i = 0; i < lista.size();i++)
        {
            System.out.println(lista.get(i).getNome());                
        }        
    }          
}
