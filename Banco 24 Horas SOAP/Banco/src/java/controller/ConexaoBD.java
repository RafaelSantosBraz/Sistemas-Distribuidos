package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rafael Braz
 */
public class ConexaoBD {

    private final Connection con;

    public ConexaoBD(String IP, String porta, String nomeBD, String usuario, String senha) {
        con = createConnection(IP, porta, nomeBD, usuario, senha);
    }

    private Connection createConnection(String IP, String porta, String nomeBD, String usuario, String senha) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + IP + ":" + porta + "/" + nomeBD, usuario, senha);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao conectar com o Banco de Dados! " + e.toString());
        }
        return connection;
    }

    public Connection getCon() {
        return con;
    }

}
