package classses;

import java.util.ArrayList;

/**
 *
 * @author Rafael Braz
 */
public class Conta {

    private final Cliente cliente;
    private final int numero;
    private final double saldo;
    private final ArrayList<Movimentacao> movimentacoes;
    private final ArrayList<Transferencia> transferencias;

    public Conta(Cliente cliente, int numero, double saldo, ArrayList<Movimentacao> movimentacoes, ArrayList<Transferencia> transferencias) {
        this.cliente = cliente;
        this.numero = numero;
        this.saldo = saldo;
        this.movimentacoes = movimentacoes;
        this.transferencias = transferencias;
    }

    public Conta(Cliente cliente, int numero, double saldo) {
        this.cliente = cliente;
        this.numero = numero;
        this.saldo = saldo;
        movimentacoes = new ArrayList<>();
        transferencias = new ArrayList<>();
    }

    public Conta() {
        cliente = new Cliente();
        numero = -1;
        saldo = -1;
        movimentacoes = new ArrayList<>();
        transferencias = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public ArrayList<Transferencia> getTransferencias() {
        return transferencias;
    }

}
