/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Aluno;
import com.google.gson.Gson;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Rafael Braz
 */
public class Controladora {

    //<editor-fold defaultstate="collapsed" desc="SINGLETON">
    private static Controladora instancia;

    public static Controladora getInstancia() {
        if (instancia == null) {
            instancia = new Controladora();
        }
        return instancia;
    }

    private Controladora() {
        servico = null;
        conectar();
    }
    //</editor-fold>   

    private Servico servico;

    private void conectar() {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 9876);
            servico = (Servico) reg.lookup("R");
        } catch (NotBoundException | RemoteException e) {
            servico = null;
        }
    }

    public Servico getServico() {
        return servico;
    }

    public boolean inserirAluno(String nome, int idade, String RA) {
        Aluno a = new Aluno(nome, idade, RA);
        try {
            if (servico != null) {
                return servico.inserirAluno(new Gson().toJson(a));
            } else {
                return false;
            }
        } catch (RemoteException e) {
            return false;
        }
    }
}
