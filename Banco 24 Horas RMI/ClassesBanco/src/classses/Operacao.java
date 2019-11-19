/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classses;

import java.io.Serializable;

/**
 *
 * @author rafael
 */
public class Operacao implements Serializable{

    private final int contaOrigem;
    private final double valor;
    private final String data;

    public Operacao(int contaOrigem, double valor, String data) {
        this.contaOrigem = contaOrigem;
        this.valor = valor;
        this.data = data;
    }

    public int getContaOrigem() {
        return contaOrigem;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

}
