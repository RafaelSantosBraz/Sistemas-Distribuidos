/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import classses.Cliente;
import classses.Operacao;
import java.util.ArrayList;
import transmissao.ConexaoBanco;
import view.TelaInicial;

/**
 *
 * @author cc45966446830
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

    }
    //</editor-fold>       

    private int banco;

    public Controladora(int banco) {
        this.banco = banco;
    }

    public int getBanco() {
        return banco;
    }

    public void setBanco(String nomeBanco) {
        if ("BB".equals(nomeBanco)) {
            banco = ConexaoBanco.BB;
        } else if ("Caixa".equals(nomeBanco)) {
            banco = ConexaoBanco.CAIXA;
        }
    }

    public void criarTelaInicial() {
        new TelaInicial().setVisible(true);
    }

    public String consultarCadastro(String cpf) throws Exception {
        Cliente cliente = new ConexaoBanco(banco).getServico().consultarCadastro(cpf);
        return cliente == null ? null : cliente.getNome();
    }

    public boolean alterarCadastro(String cpf, String nome) throws Exception {
        return new ConexaoBanco(banco).getServico().alterarCadastro(new Cliente(cpf, nome));
    }

    public ArrayList<Operacao> consultarExtrato(int conta) throws Exception {
        return new ConexaoBanco(banco).getServico().consultarExtrato(conta);
    }

    public double consultarSaldo(int conta) throws Exception {
        return new ConexaoBanco(banco).getServico().consultarSaldo(conta);
    }

    public boolean sacar(int conta, double valor) throws Exception {
        return new ConexaoBanco(banco).getServico().realizarSaque(conta, valor);
    }

    public boolean depositar(int conta, double valor) throws Exception {
        return new ConexaoBanco(banco).getServico().realizarDeposito(conta, valor);
    }

    public boolean transferirCadastro(int conta, int index) throws Exception {
        return new ConexaoBanco(banco).getServico().transferirCadastro(conta, new ConexaoBanco(index));
    }

    public boolean transferirValor(int contaOrigem, int contaDestino, double valor) throws Exception {
        return new ConexaoBanco(banco).getServico().realizarTransferencia(contaOrigem, contaDestino, valor);
    }

    public boolean transferirValor(int contaOrigem, int contaDestino, int index, double valor) throws Exception {
        return new ConexaoBanco(banco).getServico().realizarTransferencia(contaOrigem, contaDestino, new ConexaoBanco(index), valor);
    }

    public ArrayList<Integer> consultarNumerosContas(String CPF) throws Exception {
        return new ConexaoBanco(banco).getServico().consultarNumerosContasCliente(CPF);
    }

    public boolean criarCliente(String CPF, String nome) throws Exception {
        return new ConexaoBanco(banco).getServico().criarCadastro(new Cliente(CPF, nome));
    }

    public boolean criarConta(String CPF, double saldo) throws Exception {
        return new ConexaoBanco(banco).getServico().criarConta(CPF, saldo);
    }
}
