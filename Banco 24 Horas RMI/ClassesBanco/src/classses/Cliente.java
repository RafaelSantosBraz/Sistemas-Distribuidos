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
public class Cliente implements Serializable{

    private String CPF;
    private String nome;

    public Cliente() {
        CPF = "";
        nome = "";
    }

    public Cliente(String CPF, String nome) {
        this.CPF = CPF;
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
