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

    public ServicoServidor() throws RemoteException {

    }

    @Override
    public boolean inserirAluno(String aluno) throws RemoteException {
        try {
            Requisicao r = new Requisicao(Requisicao.INSERT, (new Gson()).fromJson(aluno, Aluno.class));
            ControleEspera.getInstancia().adicionarRequisicao(r);
            while (!r.isFinalizado()) {
                Thread.sleep(100);
            }
            return (Boolean) r.getResultado();
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean alterarAluno(String aluno) throws RemoteException {
        try {
            Requisicao r = new Requisicao(Requisicao.UPDATE, (new Gson()).fromJson(aluno, Aluno.class));
            ControleEspera.getInstancia().adicionarRequisicao(r);
            while (!r.isFinalizado()) {
                Thread.sleep(100);
            }
            return (Boolean) r.getResultado();
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean excluirAluno(String aluno) throws RemoteException {
        try {
            Requisicao r = new Requisicao(Requisicao.DELETE, (new Gson()).fromJson(aluno, Aluno.class));
            ControleEspera.getInstancia().adicionarRequisicao(r);
            while (!r.isFinalizado()) {
                Thread.sleep(100);
            }
            return (Boolean) r.getResultado();
        } catch (InterruptedException e) {
            return false;
        }
    }

}
