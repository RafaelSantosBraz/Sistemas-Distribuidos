/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import view.TelaInicial;

/**
 *
 * @author cc45966446830
 */
public class Controladora {

    //<editor-fold defaultstate="collapsed" desc="SINGLETON">
    private static Controladora instancia;

    public static Controladora getInstancia() {
        if (instancia == null) {
            instancia = new Controladora();
        }
        return instancia;
    }

    private Controladora() {

    }
    //</editor-fold>       

    public void criarTelaInicial() {
        new TelaInicial().setVisible(true);
    }
}
