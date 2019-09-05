/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Aluno;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Rafael Braz
 */
public class ServicoServidor implements Servico {

    @Override
    public boolean inserirAluno(Aluno aluno) throws RemoteException {
        try {
            Requisicao r = new Requisicao(0, aluno);
            while (!r.isFinalizado()) {
                Thread.sleep(100);                
            }
            return r.getResultado();
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean alterarAluno(Aluno aluno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirAluno(int ID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
