/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Rafael Braz
 */
public class BaseDados {

    private final HashMap<Integer, Double> base;
    private final String caminho;

    public BaseDados() {
        base = new HashMap<>();
        caminho = "D:\\GitHub\\Sistemas-Distribuidos\\Servico-Cartao\\Servidor\\base.txt";
        preencher();
    }

    public void adicionar(Integer chave, Double valor) {
        base.put(chave, valor);
    }

    public Double buscar(Integer chave) {
        return base.get(chave);
    }

    private void preencher() {
        try {
            FileReader arquivo = new FileReader(caminho);
            BufferedReader leitor = new BufferedReader(arquivo);
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                adicionar(Integer.valueOf(partes[0]), Double.valueOf(partes[1]));
            }
            if (base.isEmpty()) {
                preencherBaseAleatorio();
            }
        } catch (Exception e) {
            System.err.println("Erro de Arquivo! " + e.toString());
        }
    }

    public void atualizarArquivo() {
        try {
            new File(caminho).delete();
            FileWriter arquivo = new FileWriter(caminho, true);
            try (BufferedWriter escritor = new BufferedWriter(arquivo)) {
                base.forEach((t, u) -> {
                    try {
                        escritor.append(t.toString() + ";" + new BigDecimal(u).setScale(2, RoundingMode.HALF_EVEN) + "\n");
                    } catch (IOException ex) {
                        System.out.println("Erro: " + ex.toString());
                    }
                });
            }
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.toString());
        }
    }

    public void adicionarAtualizarArquivo(Integer chave, Double valor) {
        adicionar(chave, valor);
        atualizarArquivo();
    }

    private void preencherBaseAleatorio() {
        try {
            new File(caminho).delete();
            FileWriter arquivo = new FileWriter(caminho, true);
            try (BufferedWriter escritor = new BufferedWriter(arquivo)) {
                Random r = new Random();
                for (int c = 0; c < 10001; c++) {
                    escritor.append(c + ";" + r.nextInt(10001) + "." + r.nextInt(100) + "\n");
                }
            }
            preencher();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.toString());
        }
    }
}
