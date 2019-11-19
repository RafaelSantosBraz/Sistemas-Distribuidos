/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servico;

import Classes.Aluno;
import com.google.gson.Gson;
import com.sun.xml.internal.txw2.output.XmlSerializer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Fábio Moreno
 */
public class ServicoImp extends UnicastRemoteObject implements Servico {
    public ServicoImp() throws RemoteException{
        super();
    }

    @Override
    public String ConsultarAluno() throws RemoteException {
        Aluno a = new Aluno();
        a.setRa(42);
        a.setNome("O Braz");
        Gson gson = new Gson();
        String json = gson.toJson(a);
        return json;
    }


    @Override
    public long soma(long a, long b) throws RemoteException {
        return a + b; //To change body of generated methods, choose Tools | Templates.
    }
    
}
