/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author henriquericordi
 */
public class ViewController {
    
    //<editor-fold defaultstate="collapsed" desc="SINGLETON">
    private static ViewController instance;

    private ViewController() {
        
    }

    public static ViewController getInstance() {
        if (instance == null) {
            instance = new ViewController();
        }
        return instance;
    }
    //</editor-fold>
    
    
}
