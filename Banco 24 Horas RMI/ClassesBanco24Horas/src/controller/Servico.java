/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classses.Cliente;
import classses.Operacao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import transmissao.ConexaoBanco;

/**
 *
 * @author rafael
 */
public interface Servico extends Remote {

    public Cliente consultarCadastro(String CPF) throws RemoteException;

    public boolean alterarCadastro(Cliente cliente) throws RemoteException;

    public boolean realizarSaque(int conta, double valor) throws RemoteException;

    public boolean realizarDeposito(int conta, double valor) throws RemoteException;

    public double consultarSaldo(int conta) throws RemoteException;

    public boolean realizarTransferencia(int contaOrigem, int contaDestino, double valor) throws RemoteException;

    public boolean realizarTransferencia(int contaOrigem, int contaDestino, ConexaoBanco conexaoBancoDestino, double valor) throws RemoteException;

    public boolean transferirCadastro(int conta, int codBancoDestino) throws RemoteException;

    public ArrayList<Operacao> consultarExtrato(int conta) throws RemoteException;

    // Método auxiliar para transferência entre Bancos - apenas deve ser manipulada pela classe BD
    public boolean processarTransferenciaBanco(int contaOrigem, int contaDestino, double valor) throws RemoteException;
}
