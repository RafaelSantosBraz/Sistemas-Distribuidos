/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import classses.Cliente;
import com.google.gson.Gson;
import controller.Servico;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author rafael
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            // apenas um exemplo de conexão :)
//            Registry reg = LocateRegistry.getRegistry("172.16.0.5", 9876);
//            Servico a = (Servico) reg.lookup("server");
//            String json = a.consultarCadastro("12345678911");
//            Gson gson = new Gson();
//            Cliente cliente = gson.fromJson(json, Cliente.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Controladora cont = new Controladora();

    }

}
