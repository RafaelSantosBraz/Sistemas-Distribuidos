/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fábio Moreno
 */
public class Alunos {
    private String nome;
    private int idade;
    private String RA;
    private int idaluno;
    
    public Alunos()
    {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }
    
    public int getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(int idaluno) {
        this.idaluno = idaluno;
    }

    
    public void getAluno(int Id) throws SQLException, ClassNotFoundException{
        Connection conn = conectaBanco.getConexaoMySQL();
        PreparedStatement stmt = null;
        try
        {
            stmt = conn.prepareStatement("select * from alunos where idalunos=?");
            stmt.setLong(1, Id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                this.setNome(rs.getString("nome"));
                this.setIdade(rs.getInt("idade"));
                this.setRA(rs.getString("ra"));
                this.setIdaluno(rs.getInt("idalunos"));
                rs.close();
            }
        }finally {
            if (stmt != null){
                stmt.close();
            }            
        }
    }


    public ArrayList<Alunos> getAlunoNome(String nome) throws SQLException, ClassNotFoundException{
        ArrayList<Alunos> lista = new ArrayList<>();
        Connection conn = conectaBanco.getConexaoMySQL();
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("select * from alunos where nome=?");
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Alunos a = new Alunos();
            a.setNome(rs.getString("nome"));
            a.setIdade(rs.getInt("idade"));
            a.setRA(rs.getString("ra"));
            a.setIdaluno(rs.getInt("idalunos"));
            lista.add(a);
        }

        if (stmt != null) {
            stmt.close();
        }
        return lista;
    }

    
    public ArrayList<Alunos> getAlunos() throws SQLException, ClassNotFoundException{
        Connection conn = conectaBanco.getConexaoMySQL();
        PreparedStatement stmt = null;
        ArrayList<Alunos> lista = new ArrayList<>();

        stmt = conn.prepareStatement("select * from alunos");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Alunos a = new Alunos();
            a.setNome(rs.getString("nome"));
            a.setIdade(rs.getInt("idade"));
            a.setRA(rs.getString("ra"));
            a.setIdaluno(rs.getInt("idalunos"));
            lista.add(a);
        }
        rs.close();
        if (stmt != null) {
            stmt.close();
        }
        return lista;
    }
    
    public String inserir() throws SQLException
    {
        Connection conn = conectaBanco.getConexaoMySQL();
        //banco.CriaConecta();
        String sql = "INSERT INTO alunos(nome,idade, ra) VALUES(?,?,?)";     
        String retorno = "erro";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.getNome());
            pst.setInt(2, this.getIdade());
            pst.setString(3, this.getRA());
            
            if(pst.executeUpdate()>0)
            {
                retorno = "ok";
            }                            
            
        } catch (SQLException ex) {
           // Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = ex.getMessage();
        }
        return retorno;
    }
    
    public String alterar() throws SQLException
    {
        Connection conn = conectaBanco.getConexaoMySQL();
        String sql = "UPDATE public.alunos " +
                     "   SET nome=?, idade=?, ra=? " +
                     " WHERE idalunos=? ";     
        String retorno = "erro";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.getNome());
            pst.setInt(2, this.getIdade());            
            pst.setString(3, this.getRA());
            pst.setInt(4, this.getIdaluno());
            
            if(pst.executeUpdate()>0)
            {
                retorno = "ok";
            }                            
            
        } catch (SQLException ex) {
           // Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = ex.getMessage();
        }
        return retorno;    
    }
    
    public String delete(int ID) throws SQLException
    {
        Connection conn = conectaBanco.getConexaoMySQL();
        String sql = " DELETE FROM public.alunos " +
                     " WHERE idalunos = ?" ;     
        String retorno = "erro";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, ID);           
            if(pst.executeUpdate()>0)
            {
                retorno = "ok";
            }                            
            
        } catch (SQLException ex) {
           // Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            //retorno = ex.getMessage();
        }
        return retorno;    
    }
    
}
