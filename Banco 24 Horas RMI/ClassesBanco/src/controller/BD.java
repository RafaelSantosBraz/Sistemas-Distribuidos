/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classses.Cliente;
import classses.Conta;
import classses.Movimentacao;
import classses.Operacao;
import classses.Transferencia;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import transmissao.ConexaoBanco;

/**
 *
 * @author rafael
 */
public class BD {

    private final Connection con;
    private final Statement st;

    public BD() throws SQLException {
        con = createConnection();
        st = con.createStatement();
    }

    public Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "banco", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            //System.err.println(e.toString());
            e.printStackTrace(System.out);
        }
        return connection;
    }

    public Cliente buscarCliente(String CPF) {
        try {
            ResultSet resultados = st.executeQuery("SELECT * FROM cliente WHERE cpf = '" + CPF + "';");
            resultados.first();
            return new Cliente(CPF, resultados.getString("nome"));
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return null;
        }
    }

    public boolean alterarCliente(Cliente cliente) {
        try {
            int resultado = st.executeUpdate("UPDATE cliente SET nome = '" + cliente.getNome() + "' WHERE cpf like '" + cliente.getCPF() + "';");
            return resultado == 1;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public boolean alterarValorConta(int conta, double valor) {
        try {
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
            int resultado = st.executeUpdate("INSERT INTO movimentacao (numero, tipo, valor, datahora) VALUES (" + conta + ", " + tipo + ", " + valor + ", '" + data + "')" + ";");
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
            int resultado = st.executeUpdate("INSERT INTO transferencia (contaorigem, contadestino, valor, datahora) VALUES (" + contaOrigem + ", " + contaDestino + ", " + valor + ", '" + data + "')" + ";");
            return resultado == 1;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public boolean realizarTransferencia(int contaOrigem, int contaDestino, double valor) {
        try {
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
            int resultado = st.executeUpdate("UPDATE conta SET saldo = saldo - " + valor + " WHERE numero = " + contaOrigem + ";");
            boolean resultado2 = conexaoBancoDetino.getServico().processarTransferenciaBanco(contaOrigem, contaDestino, valor);
            return resultado == 1 && resultado2 && gerarTransferencia(contaOrigem, contaDestino, valor, getDataHoraAtualMysql());
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public boolean receberTransferenciaBancos(int contaOrigem, int contaDestino, double valor) {
        try {
            int resultado1 = st.executeUpdate("UPDATE conta SET saldo = saldo + " + valor + " WHERE numero = " + contaDestino + ";");
            return resultado1 == 1 && gerarTransferencia(contaOrigem, contaDestino, valor, getDataHoraAtualMysql());
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public boolean alterarBancoConta(int numeroConta, ConexaoBanco conexaoBancoDestino) throws RemoteException {
        try {
            ResultSet resultadosMov = st.executeQuery("SELECT * FROM movimentacao WHERE numero = " + numeroConta + ";");
            Statement stt = con.createStatement();
            ResultSet resultadosTrans = stt.executeQuery("SELECT * FROM transferencia WHERE contaorigem = " + numeroConta + " OR " + "contadestino = " + numeroConta + ";");
            Statement stt2 = con.createStatement();
            ResultSet resultadoCliente = stt2.executeQuery("SELECT * FROM cliente WHERE cpf = ( SELECT cpf FROM conta WHERE numero = " + numeroConta + " );");
            Statement stt3 = con.createStatement();
            ResultSet resultadoConta = stt3.executeQuery("SELECT * FROM conta WHERE numero = " + numeroConta + ";");
            resultadoCliente.first();
            Cliente cliente = new Cliente(resultadoCliente.getString("cpf"), resultadoCliente.getString("nome"));
            ArrayList<Movimentacao> movimentacoes = new ArrayList<>();
            if (resultadosMov.first()) {
                do {
                    Movimentacao mov = new Movimentacao(numeroConta, resultadosMov.getInt("tipo"), resultadosMov.getDouble("valor"), resultadosMov.getString("datahora"));
                    movimentacoes.add(mov);
                } while (resultadosMov.next());
            }
            ArrayList<Transferencia> transferencias = new ArrayList<>();
            if (resultadosTrans.first()) {
                do {
                    Transferencia trans = new Transferencia(resultadosTrans.getInt("contaorigem"), resultadosTrans.getInt("contadestino"), resultadosTrans.getDouble("valor"), resultadosTrans.getString("datahora"));
                    transferencias.add(trans);
                } while (resultadosTrans.next());
            }
            resultadoConta.first();
            Conta conta = new Conta(cliente, numeroConta, resultadoConta.getDouble("saldo"), movimentacoes, transferencias);
            // fim do processo de leitura
            boolean resultado1 = conexaoBancoDestino.getServico().processarTransferenciaCadastro(conta);
            // fim da cópia no outro banco
            int delecaoMov = st.executeUpdate("DELETE FROM movimentacao WHERE numero = " + numeroConta + ";");
            int delecaoTrans = st.executeUpdate("DELETE FROM transferencia WHERE contaorigem = " + numeroConta + ";");
            int delecaoConta = st.executeUpdate("DELETE FROM conta WHERE numero = " + numeroConta + ";");
            // fim da exclusão da conta no banco atual            
            return resultado1 && delecaoMov >= 1 && delecaoTrans >= 1 && delecaoConta >= 1;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }

    public Double buscarSaldo(int conta) {
        try {
            ResultSet resultados = st.executeQuery("SELECT saldo FROM conta WHERE numero = " + conta + ";");
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
            ResultSet resultadosMov = st.executeQuery("SELECT * FROM movimentacao WHERE numero = " + conta + ";");
            Statement stt = con.createStatement();
            ResultSet resultadosTrans = stt.executeQuery("SELECT * FROM transferencia WHERE contaorigem = " + conta + " OR " + "contadestino = " + conta + ";");
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
            extrato.sort((u, t) -> {
                if (u.getData().compareTo(t.getData()) < 0) {
                    return -1;
                }
                return 1;
            });
            return extrato;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return null;
        }
    }

    public boolean receberCadastroBanco(Conta conta) {
        try {
            int resultado1 = st.executeUpdate("INSERT INTO cliente (cpf, nome) VALUES ('" + conta.getCliente().getCPF() + "', '" + conta.getCliente().getNome() + "');");
            if (resultado1 == 0) {
                st.executeUpdate("UPDATE cliente SET nome = '" + conta.getCliente().getNome() + "' WHERE CPF = '" + conta.getCliente().getCPF() + "';");
            }
            resultado1 = st.executeUpdate("INSERT INTO conta (saldo, cpf) VALUES (" + conta.getSaldo() + ", '" + conta.getCliente().getCPF() + "');");
            conta.getMovimentacoes().forEach((mov) -> {
                gerarMovimentacao(conta.getNumero(), mov.getTipo(), mov.getValor(), mov.getData());
            });
            conta.getTransferencias().forEach((trans) -> {
                gerarTransferencia(trans.getContaOrigem(), trans.getContaDestino(), trans.getValor(), trans.getData());
            });
            return resultado1 == 1;
        } catch (SQLException ex) {
            System.err.println("Erro de manipulação do Banco de Dados! " + ex.toString());
            return false;
        }
    }
}
