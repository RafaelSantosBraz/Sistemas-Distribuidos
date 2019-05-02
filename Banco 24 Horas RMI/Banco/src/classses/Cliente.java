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
public class Cliente {

    private long CPF;
    private String nome;

    public Cliente() {
        CPF = 0;
        nome = "";
    }

    public Cliente(long CPF, String nome) {
        this.CPF = CPF;
        this.nome = nome;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
