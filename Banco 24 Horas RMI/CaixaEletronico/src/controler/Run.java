/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

/**
 *
 * @author rafael
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Lembrete:
        // para fazer as requisições para o Banco é necessário usar a classe ConexaoBanco e usar o método getServico() dela :)

        // Essa é a forma que o padrão Skeleton pede para que seja implementada a controladora. Assim pode-se chamar a controla de qualquer local :)
        Controladora.getInstancia().criarTelaInicial();
    }

}
