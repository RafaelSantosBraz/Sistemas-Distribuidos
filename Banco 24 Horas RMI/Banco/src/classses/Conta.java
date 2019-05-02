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
public class Conta {

    private Cliente cliente;
    //private Banco banco;
    private int numero;
    private double saldo;

    public Conta(Cliente cliente, int numero) {
        this.cliente = cliente;
        this.numero = numero;
    }

}
