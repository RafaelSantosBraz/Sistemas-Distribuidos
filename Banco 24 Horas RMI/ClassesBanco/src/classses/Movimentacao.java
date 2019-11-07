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
public class Movimentacao extends Operacao implements Serializable{

    private final int tipo;

    public Movimentacao(int conta, int tipo, double valor, String data) {
        super(conta, valor, data);
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

}
