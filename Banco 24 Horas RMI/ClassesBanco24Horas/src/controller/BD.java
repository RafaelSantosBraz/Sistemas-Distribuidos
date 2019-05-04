/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classses.Cliente;
import classses.Movimentacao;
import classses.Operacao;
import classses.Transferencia;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import transmissao.ConexaoBanco;

/**
 *
 * @author rafael
 */
public class BD {

    private final Connection con;

    public BD() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/banco?useSSL=false";
        String user = "mysql";
        String password = "mysql";
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

    public boolean realizarTransferencia(int contaOrigem, int contaDestino, ConexaoBanco conexaoBancoDetino, double valor) throws RemoteException {
        try {
            Statement st = con.createStatement();
            int resultado = st.executeUpdate("UPDATE conta SET saldo = saldo - " + valor + " WHERE numero = " + contaOrigem + ";");
            boolean resultado2 = conexaoBancoDetino.getServico().realizarTransferencia(contaDestino, contaOrigem, valor);
            return resultado == 1 && resultado2 && gerarTransferencia(contaOrigem, contaDestino, valor, getDataHoraAtualMysql());
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public boolean receberTransferenciaBancos(int contaOrigem, int contaDestino, double valor) {
        try {
            Statement st = con.createStatement();
            int resultado1 = st.executeUpdate("UPDATE conta SET saldo = saldo + " + valor + " WHERE numero = " + contaDestino + ";");
            return resultado1 == 1 && gerarTransferencia(contaOrigem, contaDestino, valor, getDataHoraAtualMysql());
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

    public ArrayList<Operacao> consultarExtrato(int conta) {
        try {
            ArrayList<Operacao> extrato = new ArrayList<>();
            Statement st = con.createStatement();
            ResultSet resultadosMov = st.executeQuery("SELECT * FROM movimentacao WHERE numero = " + conta + ";");
            st = con.createStatement();
            ResultSet resultadosTrans = st.executeQuery("SELECT * FROM transferencia WHERE contaorigem = " + conta + " OR " + "contadestino = " + conta + ";");
            resultadosMov.beforeFirst();
            while (resultadosMov.next()) {
                Movimentacao mov = new Movimentacao(conta, resultadosMov.getInt("tipo"), resultadosMov.getDouble("valor"), resultadosMov.getString("datahora"));
                extrato.add(mov);
            }
            resultadosTrans.beforeFirst();
            while (resultadosTrans.next()) {
                Transferencia trans = new Transferencia(resultadosTrans.getInt("contaorigem"), resultadosTrans.getInt("contadestino"), resultadosTrans.getDouble("valor"), resultadosTrans.getString("datahora"));
                extrato.add(trans);
            }
            extrato.sort((u, t) -> {
                if (u.getData().compareTo(t.getData()) < 0) {
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
