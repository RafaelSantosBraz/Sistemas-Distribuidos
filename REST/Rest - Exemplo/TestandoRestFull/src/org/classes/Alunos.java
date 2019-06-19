/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fábio Moreno
 */
public class Alunos {
    private String nome;
    private int idade;
    private String RA;
    private int idaluno;
    
    public Alunos()
    {

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
    
    public int getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(int idaluno) {
        this.idaluno = idaluno;
    }
    
}
