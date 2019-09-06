/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Rafael Braz
 */
public class Aluno {

    public static final int NO_ID = -1;
    
    private final int ID;
    private String nome;
    private int idade;
    private String RA;

    public Aluno(int ID) {
        this.ID = ID;
        nome = "";
        idade = 0;
        RA = "";
    }

    public Aluno() {
        ID = NO_ID;
        nome = "";
        idade = 0;
        RA = "";
    }

    public Aluno(int ID, String nome, int idade, String RA) {
        this.ID = ID;
        this.nome = nome;
        this.idade = idade;
        this.RA = RA;
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

}
