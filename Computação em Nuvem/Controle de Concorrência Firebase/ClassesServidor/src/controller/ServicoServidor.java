/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Aluno;
import com.google.gson.Gson;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Rafael Braz
 */
public class ServicoServidor extends UnicastRemoteObject implements Servico {

    private final ControleEspera controladora;

    public ServicoServidor() throws RemoteException {
        controladora = new ControleEspera();
    }

    @Override
    public boolean inserirAluno(String aluno) throws RemoteException {
        try {
            Requisicao r = new Requisicao(Requisicao.INSERT, (new Gson()).fromJson(aluno, Aluno.class));
            controladora.adicionarRequisicao(r);
            while (!r.isFinalizado()) {
                Thread.sleep(100);
            }
            return (Boolean) r.getResultado();
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean alterarAluno(Aluno aluno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirAluno(Aluno aluno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
