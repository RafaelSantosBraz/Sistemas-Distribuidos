/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classses;

/**
 *
 * @author rafael
 */
public class Transferencia {

    private int contaOrigem;
    private int contaDestina;
    private double valor;
    private String data;

    public Transferencia(int contaOrigem, int contaDestina, double valor, String data) {
        this.contaOrigem = contaOrigem;
        this.contaDestina = contaDestina;
        this.valor = valor;
        this.data = data;
    }

    public Transferencia() {

    }

    public int getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(int contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public int getContaDestina() {
        return contaDestina;
    }

    public void setContaDestina(int contaDestina) {
        this.contaDestina = contaDestina;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
