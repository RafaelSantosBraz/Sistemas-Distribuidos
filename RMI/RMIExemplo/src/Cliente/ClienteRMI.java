/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Classes.Aluno;
import Servico.Servico;
import com.google.gson.Gson;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Fábio Moreno
 */
public class ClienteRMI {
    public static void main(String args[])
    {
        try{
            Registry reg = LocateRegistry.getRegistry("172.16.0.5",9876);
            Servico a = (Servico) reg.lookup("server");
            String json = a.ConsultarAluno();
            Gson gson = new Gson();
            Aluno aluno = gson.fromJson(json, Aluno.class);
            System.out.println(a.soma(10, 12));
            System.out.println(aluno.getRa() + " " + aluno.getNome());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
