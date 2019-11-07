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
public class Transferencia extends Operacao implements Serializable{

    private final int contaDestino;

    public Transferencia(int contaOrigem, int contaDestino, double valor, String data) {
        super(contaOrigem, valor, data);
        this.contaDestino = contaDestino;
    }

    public int getContaDestino() {
        return contaDestino;
    }

}
