/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Aluno;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author rafael
 */
public interface Servico extends Remote {

    public boolean inserirAluno(String aluno) throws RemoteException;

    public boolean alterarAluno(Aluno aluno) throws RemoteException;

    public boolean excluirAluno(Aluno aluno) throws RemoteException;
    
}
