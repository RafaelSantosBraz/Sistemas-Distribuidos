/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classses.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rafael
 */
public class BD {

    private final Connection con;

    public BD() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String user = "testuser";
        String password = "test623";
        con = DriverManager.getConnection(url, user, password);
    }

    public Cliente buscarCliente(String CPF) {
        try {
            Statement st = con.createStatement();
            ResultSet resultados = st.executeQuery("sql aqui");
            // manipular resultados
           
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
        }
        return new Cliente();
    }
}
