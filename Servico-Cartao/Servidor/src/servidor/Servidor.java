/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Rafael Braz
 */
public class Servidor {

    private final int porta;
    private final ServerSocket servidor;
    private final BaseDados base;

    public Servidor(int porta, int tamFila) throws IOException {
        this.porta = porta;
        base = new BaseDados();
        servidor = new ServerSocket(porta, tamFila);
    }

    public Servidor(int porta) throws IOException {
        this.porta = porta;
        base = new BaseDados();
        // fila de tamanho 50 por padrão
        servidor = new ServerSocket(porta);
        System.out.println("Ouvindo na porta: " + porta);
    }

    public int getPorta() {
        return porta;
    }

    public void executar() {
        Thread inicial = new Thread(new Conexao(servidor, this));
        inicial.start();
    }

    public Boolean processarRequisicao(String mensagem) {
        if (!mensagem.matches("'.+',[0-9]+")) {
            return false;
        }
        String[] partes = mensagem.split(",");
        return base.buscar(partes[0] + partes[1]);
    }
}
