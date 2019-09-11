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
    private static int esperando;
    
    public ControleEspera() {
        requisicoes = new ArrayList<>();
        esperando = 0;
        verificarTermino();
    }
    
    public void adicionarRequisicao(Requisicao requisicao) {
        requisicoes.add(requisicao);
    }
    
    private Requisicao peek() {
        if (requisicoes.isEmpty()) {
            return null;
        }
        Requisicao aux = requisicoes.get(0);
        requisicoes.remove(aux);
        return aux;
    }
    
    public static void notificarTerminoRequisicao() {
        esperando--;
    }
    
    private void escalonar() {
        ArrayList<Requisicao> prontos = new ArrayList<>();
        Requisicao topo = peek();
        if (topo == null) {
            return;
        }
        prontos.add(topo);
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
        esperando = prontos.size();
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
    
    private void verificarTermino() {
        Thread verificador = new Thread(() -> {
            try {
                while (true) {
                    if (esperando == 0 && requisicoes.size() > 0) {
                        escalonar();
                    } else {
                        Thread.sleep(100);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("erro thread");
            }
        });
        verificador.start();
    }
}
