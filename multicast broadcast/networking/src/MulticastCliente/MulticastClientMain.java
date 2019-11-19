/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MulticastCliente;

import java.io.IOException;

/**
 *
 * @author Fábio Moreno
 */
public class MulticastClientMain {
    
    public static void main(String[] args) throws IOException{
        MulticastClient m = new MulticastClient();
        m.run();
    }
    
}
