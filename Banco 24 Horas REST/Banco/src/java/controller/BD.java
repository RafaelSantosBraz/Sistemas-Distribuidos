package controller;

import classses.Cliente;
import classses.Movimentacao;
import classses.Operacao;
import classses.Transferencia;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Rafael Braz
 */
public class BD {

    private final ConexaoBD conexao;

    public BD(String IP, String porta, String nomeBD, String usuario, String senha) {
        conexao = new ConexaoBD(IP, porta, nomeBD, usuario, senha);
    }

    public Cliente buscarCliente(String CPF) {
        try {
            ResultSet resultados = executarQuery("SELECT * FROM cliente WHERE cpf = '" + CPF + "';");
            if (resultados.first()) {
                return new Cliente(CPF, resultados.getString("nome"));
            }
            return null;
        } catch (SQLException ex) {
            System.err.println("Erro de acesso aos resultados SQL! " + ex.toString());
            return null;
        }
    }

    public boolean alterarCliente(Cliente cliente) {
        return 1 == executarUpdate("UPDATE cliente SET nome = '" + cliente.getNome() + "' WHERE cpf like '" + cliente.getCPF() + "';");
    }

    public boolean alterarValorConta(int conta, double valor) {
        int resultado = executarUpdate("UPDATE conta SET saldo = saldo + " + valor + " WHERE numero = " + conta + ";");
        int tipo = 0;
        if (valor > 0.0) {
            tipo = 1;
        }
        return resultado == 1 && gerarMovimentacao(conta, tipo, valor, getDataHoraAtualMysql());
    }

    private boolean gerarMovimentacao(int conta, int tipo, double valor, String data) {
        return 1 == executarUpdate("INSERT INTO movimentacao (numero, tipo, valor, datahora) VALUES (" + conta + ", " + tipo + ", " + valor + ", '" + data + "')" + ";");
    }

    public String getDataHoraAtualMysql() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    }

    private boolean gerarTransferencia(int contaOrigem, int contaDestino, double valor, String data) {
        return 1 == executarUpdate("INSERT INTO transferencia (contaorigem, contadestino, valor, datahora) VALUES (" + contaOrigem + ", " + contaDestino + ", " + valor + ", '" + data + "')" + ";");
    }

    public boolean realizarTransferencia(int contaOrigem, int contaDestino, double valor) {
        int resultado = executarUpdate("UPDATE conta SET saldo = saldo - " + valor + " WHERE numero = " + contaOrigem + ";");
        int resultado1 = executarUpdate("UPDATE conta SET saldo = saldo + " + valor + " WHERE numero = " + contaDestino + ";");
        return resultado == 1 && resultado1 == 1 && gerarTransferencia(contaOrigem, contaDestino, valor, getDataHoraAtualMysql());
    }

    public Double buscarSaldo(int conta) {
        try {
            ResultSet resultados = executarQuery("SELECT saldo FROM conta WHERE numero = " + conta + ";");
            if (resultados.first()) {
                return resultados.getDouble("saldo");
            }
            return null;
        } catch (SQLException ex) {
            System.err.println("Erro de acesso aos resultados SQL! " + ex.toString());
            return null;
        }
    }

    public ArrayList<String> consultarExtrato(int conta) {
        try {
            ArrayList<Operacao> extrato = new ArrayList<>();
            ResultSet resultadosMov = executarQuery("SELECT * FROM movimentacao WHERE numero = " + conta + ";");
            ResultSet resultadosTrans = executarQuery("SELECT * FROM transferencia WHERE contaorigem = " + conta + " OR " + "contadestino = " + conta + ";");
            if (resultadosMov.first()) {
                do {
                    Movimentacao mov = new Movimentacao(conta, resultadosMov.getInt("tipo"), resultadosMov.getDouble("valor"), resultadosMov.getString("datahora"));
                    extrato.add(mov);
                } while (resultadosMov.next());
            }
            if (resultadosTrans.first()) {
                do {
                    Transferencia trans = new Transferencia(resultadosTrans.getInt("contaorigem"), resultadosTrans.getInt("contadestino"), resultadosTrans.getDouble("valor"), resultadosTrans.getString("datahora"));
                    extrato.add(trans);
                } while (resultadosTrans.next());
            }
            extrato.sort((Operacao u, Operacao t) -> {
                if (u.getData().compareTo(t.getData()) < 0) {
                    return -1;
                }
                return 1;
            });
            ArrayList<String> linhasExtrato = new ArrayList<>();
            extrato.forEach((op) -> {
                linhasExtrato.add(op.toString());
            });
            return linhasExtrato;
        } catch (SQLException ex) {
            System.err.println("Erro de acesso aos resultados SQL! " + ex.toString());
            return null;
        }
    }

    private int executarUpdate(String SQL) {
        try {
            return conexao.getCon().createStatement().executeUpdate(SQL);
        } catch (SQLException ex) {
            System.err.println("Erro ao executar SQL Update! " + ex.toString());
        }
        return -1;
    }

    private ResultSet executarQuery(String SQL) {
        try {
            return conexao.getCon().createStatement().executeQuery(SQL);
        } catch (SQLException ex) {
            System.err.println("Erro ao executar SQL Query! " + ex.toString());
        }
        return null;
    }

    public boolean criarCliente(Cliente cliente) {
        return 1 == executarUpdate("INSERT INTO cliente (cpf, nome) VALUES ('" + cliente.getCPF() + "', '" + cliente.getNome() + "');");
    }

    public boolean criarConta(String CPF, double saldo) {
        return 1 == executarUpdate("INSERT INTO conta (cod, cpf, saldo) VALUES (0, '" + CPF + "', " + saldo + ");");
    }

    public ArrayList<Integer> buscarNumeroContasCliente(String CPF) {
        ArrayList<Integer> contas = new ArrayList<>();
        ResultSet resultados = executarQuery("SELECT numero FROM conta WHERE cpf = '" + CPF + "';");
        try {
            if (resultados.first()) {
                do {
                    contas.add(resultados.getInt("numero"));
                } while (resultados.next());
            }
        } catch (SQLException ex) {
            System.err.println("Erro de acesso aos resultados SQL! " + ex.toString());
        }
        return contas;
    }
}
