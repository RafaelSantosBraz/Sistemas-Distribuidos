/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classses.Cliente;
import classses.Conta;
import classses.Operacao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import transmissao.ConexaoBanco;

/**
 *
 * @author rafael
 */
public class ServicoBanco extends UnicastRemoteObject implements Servico {

    private final BD bd;

    public ServicoBanco(String IP, String porta, String nomeBD, String usuario, String senha) throws RemoteException {
        super();
        bd = new BD(IP, porta, nomeBD, usuario, senha);
    }

    @Override
    public Cliente consultarCadastro(String CPF) throws RemoteException {
        return bd.buscarCliente(CPF);
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
    public boolean transferirCadastro(int conta, ConexaoBanco conexaoBancoDestino) throws RemoteException {
        return bd.alterarBancoConta(conta, conexaoBancoDestino);
    }

    @Override
    public double consultarSaldo(int conta) throws RemoteException {
        return bd.buscarSaldo(conta);
    }

    @Override
    public boolean realizarTransferencia(int contaOrigem, int contaDestino, double valor) throws RemoteException {
        return bd.realizarTransferencia(contaOrigem, contaDestino, valor);
    }

    @Override
    public ArrayList<Operacao> consultarExtrato(int conta) {
        return bd.consultarExtrato(conta);
    }

    @Override
    public boolean realizarTransferencia(int contaOrigem, int contaDestino, ConexaoBanco conexaoBancoDestino, double valor) throws RemoteException {
        return bd.realizarTransferencia(contaOrigem, contaDestino, conexaoBancoDestino, valor);
    }

    @Override
    public boolean processarTransferenciaBanco(int contaOrigem, int contaDestino, double valor) throws RemoteException {
        return bd.receberTransferenciaBancos(contaOrigem, contaDestino, valor);
    }

    @Override
    public boolean processarTransferenciaCadastro(Conta conta) throws RemoteException {
        return bd.receberCadastroBanco(conta);
    }

}
