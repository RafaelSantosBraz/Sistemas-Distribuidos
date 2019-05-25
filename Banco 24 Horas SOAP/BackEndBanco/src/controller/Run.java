/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classses.Cliente;

/**
 *
 * @author Rafael Braz
 */
public class Run {

    public static void main(String[] args) {
        ControllerBanco c = new ControllerBanco("localhost", "3306", "banco", "mysql", "mysql");
        Cliente cli = c.consultarCadastro("47192962899");
    }
}
