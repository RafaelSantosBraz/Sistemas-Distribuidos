/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classses.Cliente;
import classses.Movimentacao;
import classses.Transferencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            ResultSet resultados = st.executeQuery("SELECT * FROM cliente WHERE cpf like '" + CPF + "';");
            resultados.first();
            return new Cliente(CPF, resultados.getString("nome"));
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return null;
        }
    }

    public boolean alterarCliente(Cliente cliente) {
        try {
            Statement st = con.createStatement();
            int resultado = st.executeUpdate("UPDATE cliente SET nome = '" + cliente.getNome() + "' WHERE cpf like '" + cliente.getCPF() + "';");
            return resultado == 1;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public boolean alterarValorConta(int conta, double valor) {
        try {
            Statement st = con.createStatement();
            int resultado = st.executeUpdate("UPDATE conta SET saldo = saldo + " + valor + " WHERE numero = " + conta + ";");
            int tipo = 0;
            if (valor > 0.0) {
                tipo = 1;
            }
            return resultado == 1 && gerarMovimentacao(conta, tipo, valor, getDataHoraAtualMysql());
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    private boolean gerarMovimentacao(int conta, int tipo, double valor, String data) {
        try {
            Statement st = con.createStatement();
            int resultado = st.executeUpdate("INSERT INTO movimentacao (numero, tipo, valor, datahora) VALUES (" + conta + ", " + tipo + ", " + valor + ", " + data + ")" + ";");
            return resultado == 1;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public String getDataHoraAtualMysql() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }

    private boolean gerarTransferencia(int contaOrigem, int contaDestino, double valor, String data) {
        try {
            Statement st = con.createStatement();
            int resultado = st.executeUpdate("INSERT INTO transferencia (contaorigem, contadestino, valor, datahora) VALUES (" + contaOrigem + ", " + contaDestino + ", " + valor + ", " + data + ")" + ";");
            return resultado == 1;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public boolean realizarTransferencia(int contaOrigem, int contaDestino, double valor) {
        try {
            Statement st = con.createStatement();
            int resultado = st.executeUpdate("UPDATE conta SET saldo = saldo - " + valor + " WHERE numero = " + contaOrigem + ";");
            int resultado1 = st.executeUpdate("UPDATE conta SET saldo = saldo + " + valor + " WHERE numero = " + contaDestino + ";");
            return resultado == 1 && resultado1 == 1 && gerarTransferencia(contaOrigem, contaDestino, valor, getDataHoraAtualMysql());
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public boolean alterarBancoConta(int conta, int banco) {
        try {
            Statement st = con.createStatement();
            int resultado = st.executeUpdate("UPDATE conta SET cod = " + banco + " WHERE numero = " + conta + ";");
            return resultado == 1;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public Double buscarSaldo(int conta) {
        try {
            Statement st = con.createStatement();
            ResultSet resultados = st.executeQuery("SELECT saldo FROM conta WHERE numero = " + conta + "';");
            resultados.first();
            return resultados.getDouble("saldo");
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return null;
        }
    }

    public ArrayList<Object> consultarExtrato(int conta) {
        try {
            ArrayList<Object> extrato = new ArrayList<>();
            Statement st = con.createStatement();
            ResultSet resultadosMov = st.executeQuery("SELECT * FROM movimentacao WHERE numero = " + conta + ";");
            st = con.createStatement();
            ResultSet resultadosTrans = st.executeQuery("SELECT * FROM transferencia WHERE contaorigem = " + conta + " OR " + "contadestino = " + conta + ";");
            resultadosMov.beforeFirst();
            while (resultadosMov.next()) {
                Movimentacao mov = new Movimentacao(resultadosMov.getInt("tipo"), resultadosMov.getDouble("valor"), resultadosMov.getString("datahora"));
                extrato.add(mov);
            }
            resultadosTrans.beforeFirst();
            while (resultadosTrans.next()) {
                Transferencia trans = new Transferencia(resultadosTrans.getInt("contaorigem"), resultadosTrans.getInt("contadestino"), resultadosTrans.getDouble("valor"), resultadosTrans.getString("datahora"));
                extrato.add(trans);
            }
            extrato.sort((u, t) -> {
                Movimentacao mov1 = null;
                Transferencia trans1 = null;
                Movimentacao mov2 = null;
                Transferencia trans2 = null;
                if (u instanceof Movimentacao) {
                    mov1 = (Movimentacao) u;
                } else {
                    trans1 = (Transferencia) u;
                }
                if (t instanceof Movimentacao) {
                    mov2 = (Movimentacao) t;
                } else {
                    trans2 = (Transferencia) t;
                }
                String ant, pos;
                if (mov1 != null) {
                    ant = mov1.getData();
                } else {
                    ant = trans1.getData();
                }
                if (mov2 != null) {
                    pos = mov2.getData();
                } else {
                    pos = trans2.getData();
                }
                int comp = ant.compareTo(pos);
                if (comp < 0) {
                    return 1;
                }
                return -1;
            });
            return extrato;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return null;
        }
    }
}
