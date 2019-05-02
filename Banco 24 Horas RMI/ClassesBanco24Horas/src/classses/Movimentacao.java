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
public class Movimentacao {

    private int tipo;
    private double valor;
    private String data;

    public Movimentacao(int tipo, double valor, String data) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

    public Movimentacao() {

    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
