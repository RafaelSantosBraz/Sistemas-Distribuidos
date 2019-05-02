/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classses.Cliente;
import com.google.gson.Gson;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ServicoBanco extends UnicastRemoteObject implements Servico {

    private final BD bd;

    public ServicoBanco() throws RemoteException, SQLException {
        super();
        bd = new BD();
    }

    @Override
    public String consultarCadastro(String CPF) throws RemoteException {
        return (new Gson()).toJson(bd.buscarCliente(CPF));
    }

    @Override
    public boolean alterarCadastro(Cliente cliente) throws RemoteException {
        return bd.alterarCliente(cliente);
    }

    @Override
    public boolean realizarSaque(int conta, double valor) throws RemoteException {
        if (valor > 0) {
            return bd.alterarValorConta(conta, valor * -1);
        }
        return bd.alterarValorConta(conta, valor);
    }

    @Override
    public boolean realizarDeposito(int conta, double valor) throws RemoteException {
        return bd.alterarValorConta(conta, valor);
    }

    @Override
    public double consultarSaldo(String CPF) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean realizarTransferenciaContas(int contaOrigem, int contaDestino, double valor) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean realizarTransferenciaBancos(int codBancoOrigem, int contaOrigem, int codBancoDestino, int contaDestino, double valor) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean transferirCadastro(Cliente cliente, int codBancoDestino) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
