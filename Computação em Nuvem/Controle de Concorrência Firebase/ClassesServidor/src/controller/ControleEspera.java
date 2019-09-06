/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael Braz
 */
public class ControleEspera {
    
    private final List<Requisicao> requisicoes;
    
    public ControleEspera() {
        requisicoes = new ArrayList<>();
    }
    
    public void adicionarRequisicao(Requisicao requisicao) {
        requisicoes.add(requisicao);
    }
    
    private void removerRequisicao(Requisicao requisicao) {
        requisicoes.remove(requisicao);
    }
    
    private Requisicao peek() {
        if (requisicoes.isEmpty()) {
            return null;
        }
        Requisicao aux = requisicoes.get(0);
        requisicoes.remove(aux);
        return aux;
    }
    
    public void escalonar() {
        ArrayList<Requisicao> prontos = new ArrayList<>();
        Requisicao topo = peek();
        if (topo == null) {
            return;
        }
        requisicoes.forEach((t) -> {
            if (!dependenciasConjunto(prontos, t)) {
                prontos.add(t);
            }
        });
        if (prontos.size() > 1) {
            for (int c = 1; c < prontos.size(); c++) {
                requisicoes.remove(prontos.get(c));
            }
        }
        executarRequisicoesProntas(prontos);
    }
    
    private boolean dependenciasConjunto(List<Requisicao> conjunto, Requisicao amostra) {
        return conjunto.stream().anyMatch((r) -> (dependenciaItensDados(r, amostra)));
    }
    
    private boolean dependenciaItensDados(Requisicao r1, Requisicao r2) {
        return r1.getAlunos().stream().anyMatch((a1) -> (r2.getAlunos().stream().anyMatch((a2) -> (a1.getID() == a2.getID()))));
    }
    
    private void executarRequisicoesProntas(List<Requisicao> requisicoes) {
        requisicoes.parallelStream().forEach((t) -> {
            t.executar();
        });
    }
    
}
