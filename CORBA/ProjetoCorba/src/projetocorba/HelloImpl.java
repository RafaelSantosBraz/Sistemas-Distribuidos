/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocorba;

import HelloApp.HelloPOA;
import org.omg.CORBA.ORB;

/**
 *
 * @author Fábio Moreno
 */
public class HelloImpl extends HelloPOA{
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val; 
    }
    @Override
    public String sayHello() {
        return "\nOlá Sistemas Distribuídos !!\n";
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
    
}
