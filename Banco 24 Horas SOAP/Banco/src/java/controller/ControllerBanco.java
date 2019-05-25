package controller;

import classses.Cliente;
import classses.Operacao;
import java.util.ArrayList;

/**
 *
 * @author Rafael Braz
 */
public class ControllerBanco {

    private final BD bd;

    public ControllerBanco(String IP, String porta, String nomeBD, String usuario, String senha) {
        bd = new BD(IP, porta, nomeBD, usuario, senha);
    }

    public Cliente consultarCadastro(String CPF) {
        return bd.buscarCliente(CPF);
    }

    public boolean alterarCadastro(Cliente cliente) {
        return bd.alterarCliente(cliente);
    }

    public boolean realizarSaque(int conta, double valor) {
        if (valor > 0) {
            valor *= -1;
        }
        if (consultarSaldo(conta) + valor >= 0.0) {
            return bd.alterarValorConta(conta, valor);
        }
        return false;
    }

    public boolean realizarDeposito(int conta, double valor) {
        if (valor < 0) {
            return false;
        }
        return bd.alterarValorConta(conta, valor);
    }

    public double consultarSaldo(int conta) {
        return bd.buscarSaldo(conta);
    }

    public boolean realizarTransferencia(int contaOrigem, int contaDestino, double valor) {
        if (valor < 0) {
            return false;
        }
        if (consultarSaldo(contaOrigem) - valor >= 0) {
            return bd.realizarTransferencia(contaOrigem, contaDestino, valor);
        }
        return false;
    }

    public ArrayList<Operacao> consultarExtrato(int conta) {
        return bd.consultarExtrato(conta);
    }

    public boolean criarCadastro(Cliente cliente) {
        return bd.criarCliente(cliente);
    }

    public boolean criarConta(String CPF, double saldo) {
        return bd.criarConta(CPF, saldo);
    }

    public ArrayList<Integer> consultarNumerosContasCliente(String CPF) {
        return bd.buscarNumeroContasCliente(CPF);
    }

}
