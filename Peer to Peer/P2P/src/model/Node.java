/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Rafael Braz
 */
public class Node implements Serializable{

    private final String IP;
    private final int port;
    private final String regName;

    public Node(String IP, int port, String regName) {
        this.IP = IP;
        this.port = port;
        this.regName = regName;
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return port;
    }

    public String getRegName() {
        return regName;
    }

}
