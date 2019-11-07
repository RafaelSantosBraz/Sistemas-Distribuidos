/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Rafael Braz
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String endereco = "localhost";
        int porta = 12345;
        try {
            Socket conexao;
            conexao = new Socket(endereco, porta);
            System.out.println("Conectado ao servidor " + endereco + ", na porta: " + porta);
            ObjectOutputStream saida;
            ObjectInputStream entrada;
            saida = new ObjectOutputStream(conexao.getOutputStream());
            entrada = new ObjectInputStream(conexao.getInputStream());
            String mensagem;
            //lendo a mensagem enviada pelo servidor
            mensagem = (String) entrada.readObject();
            System.out.println("Servidor>> " + mensagem);
            //Mensagem de saida
            Scanner ler = new Scanner(System.in);
            System.out.println("Digite o código do Cliente:");
            Integer codigo = ler.nextInt();
            System.out.println("Digite o valor:");
            Double valor = ler.nextDouble();
            saida.writeInt(codigo);
            saida.writeDouble(valor);
            saida.flush();
            //lendo a mensagem enviada pelo servidor
            Boolean resposta = entrada.readBoolean();
            if (resposta) {
                System.out.println("Compra aprovada! :)");
            } else {
                System.out.println("Compra negada! :(");
            }
            Double valorRestante = entrada.readDouble();
            System.out.println("Valor restante: R$" + valorRestante);
            saida.close();
            entrada.close();
            conexao.close();
        } catch (Exception e) {
            System.err.println("erro: " + e.toString());
        }
    }

}
