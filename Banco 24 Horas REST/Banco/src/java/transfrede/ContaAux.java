/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfrede;

/**
 *
 * @author Rafael Braz
 */
public class ContaAux {

    private final String CPF;
    private final Double saldo;

    public ContaAux(String CPF, Double saldo) {
        this.CPF = CPF;
        this.saldo = saldo;
    }

    public String getCPF() {
        return CPF;
    }

    public Double getSaldo() {
        return saldo;
    }

}
