/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classses.Cliente;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author rafael
 */
public interface Servico extends Remote {

    public String consultarCadastro(long CPF) throws RemoteException;

    public boolean alterarCadastro(Cliente cliente) throws RemoteException;

    public boolean realizarSaque(double valor) throws RemoteException;

    public boolean realizarDeposito(double valor) throws RemoteException;

    public double consultarSaldo(long CPF) throws RemoteException;

    public boolean realizarTransferenciaContas(int contaOrigem, int contaDestino, double valor) throws RemoteException;

    public boolean realizarTransferenciaBancos(int codBancoOrigem, int contaOrigem, int codBancoDestino, int contaDestino, double valor) throws RemoteException;

    public boolean transferirCadastro(Cliente cliente, int codBancoDestino) throws RemoteException;
}
