/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
        try {
            ObjectOutputStream saida;
            ObjectInputStream entrada;
            Socket conexao;
            while (true) {
                conexao = servidor.accept();
                System.out.println("Conexão estabelecida com: " + conexao.getInetAddress().getHostAddress());
                saida = new ObjectOutputStream(conexao.getOutputStream());
                entrada = new ObjectInputStream(conexao.getInputStream());
                // Informar status da conexao ao cliente
                saida.writeObject("Conexao estabelecida com sucesso...\n");
                try {
                    String mensagem = (String) entrada.readObject();
                    System.out.println("Cliente>> " + mensagem);
                    boolean status = processarRequisicao(mensagem);
                    System.out.println("Resposta>> " + status);
                    saida.writeObject(status);
                } catch (IOException iOException) {
                    System.err.println("erro: " + iOException.toString());
                }
                saida.close();
                entrada.close();
                conexao.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro: " + e.toString());
        }
    }

    private boolean processarRequisicao(String mensagem) {
        if (!mensagem.matches("'.+',[0-9]+")) {
            return false;
        }
        String[] partes = mensagem.split(",");
        return base.buscar(partes[0] + partes[1]);
    }
}
