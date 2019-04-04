/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpservidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fábio Moreno
 */
public class threadservidor implements Runnable  {

    /**
     * @return the conexao
     */
    public Socket getConexao() {
        return conexao;
    }

    /**
     * @param conexao the conexao to set
     */
    public void setConexao(Socket conexao) {
        this.conexao = conexao;
    }

    private Socket conexao;
    
    
    @Override
    public void run() {
        ObjectOutputStream saida = null;
        ObjectInputStream entrada = null ;     
        String mensagem = "";
                        System.out.println("Conexao estabelecida com: " + getConexao().getInetAddress().getHostAddress());

        try {
            //obtendo os fluxos de entrada e de saida
            saida = new ObjectOutputStream(getConexao().getOutputStream());
            entrada = new ObjectInputStream(getConexao().getInputStream());
            //enviando a mensagem ao cliente
            saida.writeObject("Conexao estabelecida com sucesso...\n");
        } catch (IOException ex) {
            Logger.getLogger(threadservidor.class.getName()).log(Level.SEVERE, null, ex);
        }                

                do {//fica aqui ate' o cliente enviar a mensagem FIM
                    try {
                        //obtendo a mensagem enviada pelo cliente
                        mensagem = (String) entrada.readObject();
                        System.out.println("Cliente>> " + mensagem);
                        
                        //enviando a mensagem ao cliente
                        saida.writeObject("mensagem recebida");
                    } catch (IOException iOException) {
                        System.err.println("erro: " + iOException.toString());
                    } catch (ClassNotFoundException ex) {
                Logger.getLogger(threadservidor.class.getName()).log(Level.SEVERE, null, ex);
            }
                } while (!mensagem.equals("FIM"));

                System.out.println("Conexao encerrada pelo cliente");
        try {
            saida.close();
            entrada.close();
             getConexao().close();
        } catch (IOException ex) {
            Logger.getLogger(threadservidor.class.getName()).log(Level.SEVERE, null, ex);
        }
                
               

    }
    
}
