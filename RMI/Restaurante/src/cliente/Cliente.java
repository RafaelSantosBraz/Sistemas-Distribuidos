/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import classes.Entrega;
import com.google.gson.Gson;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import servico.Servico;

/**
 *
 * @author aluno
 */
public class Cliente {

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 9876);
            Servico a = (Servico) reg.lookup("serverR");
            String json = a.consultarFrete("Vila ");
            Gson gson = new Gson();
            Entrega e = gson.fromJson(json, Entrega.class);
            System.out.println(e.getBairro() + " " + e.getValor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
