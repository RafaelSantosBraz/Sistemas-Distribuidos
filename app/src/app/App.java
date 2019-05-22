/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import ws.Livro;

/**
 *
 * @author cc47192962899
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println((int) soma(1, 3));
        System.out.println(retornaLivro().getNome());
        ((ArrayList<Livro>) retornaLista()).forEach((t) -> {
            System.out.println(t.getNome());
        });
    }

    private static double soma(double num1, double num2) {
        ws.WSAndroid_Service service = new ws.WSAndroid_Service();
        ws.WSAndroid port = service.getWSAndroidPort();
        return port.soma(num1, num2);
    }

    private static Livro retornaLivro() {
        ws.WSAndroid_Service service = new ws.WSAndroid_Service();
        ws.WSAndroid port = service.getWSAndroidPort();
        return port.retornaLivro();
    }

    private static java.util.List<ws.Livro> retornaLista() {
        ws.WSAndroid_Service service = new ws.WSAndroid_Service();
        ws.WSAndroid port = service.getWSAndroidPort();
        return port.retornaLista();
    }

}
