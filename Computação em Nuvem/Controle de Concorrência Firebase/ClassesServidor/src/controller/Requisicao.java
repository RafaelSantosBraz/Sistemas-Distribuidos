/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Aluno;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael Braz
 */
public class Requisicao {

    public static final int INSERT = 0;
    public static final int UPDATE = 1;
    public static final int DELETE = 2;

    private final int tipo;
    private boolean finalizado;
    private final List<Aluno> alunos;
    private boolean resultado;

    public Requisicao(int tipo, List<Aluno> alunos) {
        finalizado = false;
        this.tipo = tipo;
        resultado = false;
        if (alunos == null) {
            this.alunos = new ArrayList<>();
        } else {
            this.alunos = alunos;
        }
    }

    public Requisicao(int tipo, Aluno aluno) {
        finalizado = false;
        this.tipo = tipo;
        resultado = false;
        alunos = new ArrayList<>();
        if (aluno != null) {
            alunos.add(aluno);
        }
    }

    public void finalizar() {
        finalizado = true;
    }

    public int getTipo() {
        return tipo;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public boolean getResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

}
