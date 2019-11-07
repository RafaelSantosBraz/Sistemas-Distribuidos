/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Aluno;
import com.google.gson.Gson;
import com.sun.jersey.api.client.*;
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
                inserirAluno(0);
                break;
            case UPDATE:
                alterarAluno(0);
                break;
            case DELETE:
                removerAluno(0);
                break;
            case NO_TYPE:
                resultado = null;
        }
        finalizar();
    }

    private void finalizar() {
        finalizado = true;
        ControleEspera.notificarTerminoRequisicao();
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

    private void inserirAluno(int index) {
        Client c = Client.create();
        int contador = getContador();
        WebResource wr = c.resource("https://fir-aula-teste.firebaseio.com/alunos/" + contador + ".json");
        alunos.get(index).setID(contador);
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(alunos.get(index)));
        System.out.println("Inserir: " + response);
        resultado = !response.equals("null");
    }

    private void alterarAluno(int index) {
        Client c = Client.create();
        WebResource wr = c.resource("https://fir-aula-teste.firebaseio.com/alunos/" + alunos.get(index).getID() + ".json");
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(alunos.get(index)));
        System.out.println("Alterar: " + response);
        resultado = !response.equals("null");
    }

    private void removerAluno(int index) {
        Client c = Client.create();
        WebResource wr = c.resource("https://fir-aula-teste.firebaseio.com/alunos/" + alunos.get(index).getID() + ".json");
        String response = wr.delete(String.class);
        System.out.println("Apagar: " + response);
        resultado = response.equals("null");
    }

    private int getContador() {
        int contador = 0;
        Client c = Client.create();
        WebResource wr = c.resource("https://fir-aula-teste.firebaseio.com/Counter.json");
        String response = wr.get(String.class);
        Gson gson = new Gson();
        if (response.equals("null")) {
            inicializacontador(contador);
        } else {
            contador = gson.fromJson(response, Integer.class);
        }
        contador++;
        response = wr.type("application/json").put(String.class, gson.toJson(contador));
        //System.out.println(response);
        return contador;
    }

    private void inicializacontador(int contador) {
        Client c = Client.create();
        WebResource wr = c.resource("https://fir-aula-teste.firebaseio.com/Counter.json");
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(contador));
        System.out.println(response);
    }
}
