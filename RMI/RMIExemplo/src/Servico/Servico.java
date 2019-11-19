/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servico;

import Classes.Aluno;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Fábio Moreno
 */
public interface Servico extends Remote {
    
   public String ConsultarAluno() throws RemoteException;
   
   public long soma(long a, long b) throws RemoteException;
}
