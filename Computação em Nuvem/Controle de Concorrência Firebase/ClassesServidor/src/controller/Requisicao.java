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

    public static final int NO_TYPE = -1;
    public static final int INSERT = 0;
    public static final int UPDATE = 1;
    public static final int DELETE = 2;

    private final int tipo;
    private boolean finalizado;
    private final List<Aluno> alunos;
    private Object resultado;

    public Requisicao(int tipo, List<Aluno> alunos) {
        finalizado = false;
        this.tipo = normalizarTipo(tipo);
        resultado = null;
        if (alunos == null) {
            this.alunos = new ArrayList<>();
        } else {
            this.alunos = alunos;
        }
    }

    public Requisicao(int tipo, Aluno aluno) {
        finalizado = false;
        this.tipo = normalizarTipo(tipo);
        resultado = null;
        alunos = new ArrayList<>();
        if (aluno != null) {
            alunos.add(aluno);
        }
    }

    private int normalizarTipo(int tipo) {
        switch (tipo) {
            case INSERT:
            case UPDATE:
            case DELETE:
                return tipo;
        }
        return NO_TYPE;
    }

    public void executar() {
        switch (tipo) {
            case INSERT:
                inserirAluno();
                break;
            case UPDATE:
                alterarAluno();
                break;
            case DELETE:
                removerAluno();
                break;
            case NO_TYPE:
                resultado = null;
        }
        finalizar();
    }

    private void finalizar() {
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

    public Object getResultado() {
        return resultado;
    }

    private void inserirAluno(){
        
    }
    
    private void alterarAluno(){
        
    }
    
    private void removerAluno(){
        
    }
    
}
