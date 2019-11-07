/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import classes.Entrega;
import com.google.gson.Gson;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author aluno
 */
public class ServEntrega extends UnicastRemoteObject implements Servico{

    public ServEntrega() throws RemoteException{
        super();
    }
    
    @Override
    public String consultarFrete(String bairro) throws RemoteException {
        Entrega e = new Entrega(bairro);
        if ("Vila Maria".equals(bairro)){
            e.setValor(120);
        } else {
            e.setValor(42);
        }
        Gson gson = new Gson();
        String json = gson.toJson(e);
        return json;
    }
    
}
