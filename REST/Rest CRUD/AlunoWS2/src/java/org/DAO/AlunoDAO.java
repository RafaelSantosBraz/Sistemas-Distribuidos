/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.banco.Banco;
import org.classes.Aluno;

/**
 *
 * @author DELL-Fabio
 */
public class AlunoDAO {
    //public Banco banco;
    
    public AlunoDAO()
    {        
    }
    
    public String inserir(Aluno aluno)
    {
        Banco banco = new Banco();
        banco.CriaConecta();
        String sql = "INSERT INTO aluno(nome,idade,senha,email) VALUES(?,?,?,?)";     
        String retorno = "false";
        try {
            PreparedStatement pst = banco.con.prepareStatement(sql);
            pst.setString(1, aluno.getNome());
            pst.setInt(2, aluno.getIdade());
            pst.setString(3, aluno.getSenha());
            pst.setString(4, aluno.getEmail());
            
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
    
    public String alterar(Aluno aluno)
    {
        Banco banco = new Banco();
        banco.CriaConecta();
        String sql = "UPDATE public.aluno " +
                     "   SET nome=?, idade=?, email=?, senha=? " +
                     " WHERE idaluno=? ";     
        String retorno = "false";
        try {
            PreparedStatement pst = banco.con.prepareStatement(sql);
            pst.setString(1, aluno.getNome());
            pst.setInt(2, aluno.getIdade());            
            pst.setString(3, aluno.getEmail());
            pst.setString(4, aluno.getSenha());
            pst.setInt(5, aluno.getId());
            
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
    
    public int delete(int ID)
    {
        Banco banco = new Banco();
        banco.CriaConecta();
        String sql = " DELETE FROM public.aluno " +
                     " WHERE idaluno = ?" ;     
        int retorno = 0;
        try {
            PreparedStatement pst = banco.con.prepareStatement(sql);
            pst.setInt(1, ID);           
            if(pst.executeUpdate()>0)
            {
                retorno = 1;
            }                            
            
        } catch (SQLException ex) {
           // Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            //retorno = ex.getMessage();
        }
        return retorno;    
    }
    
     public Aluno retornaAluno(int ID)
    {
        Aluno al = new Aluno();
        
        Banco banco = new Banco();
        banco.CriaConecta();
        String sql = " SELECT * FROM public.aluno " +
                     " WHERE idaluno = " + ID;     
        try {
            Statement st = banco.con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
      
            while (rs.next())
            {
                al.setId(rs.getInt("idaluno"));
                al.setNome(rs.getString("nome"));
                al.setEmail(rs.getString("email"));
                al.setIdade(rs.getInt("idade"));
                al.setSenha(rs.getString("senha"));
            }
            st.close();
            
            
        } catch (Exception ex) {
           // Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;    
    }   
     
     public List<Aluno> listaAluno()
    {
        List<Aluno> lista = new ArrayList();
        
        Banco banco = new Banco();
        banco.CriaConecta();
        String sql = " SELECT * FROM public.aluno ";
        try {
            Statement st = banco.con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
      
            while (rs.next())
            {
                Aluno al = new Aluno();
                al.setId(rs.getInt("idaluno"));
                al.setNome(rs.getString("nome"));
                al.setEmail(rs.getString("email"));
                al.setIdade(rs.getInt("idade"));
                al.setSenha(rs.getString("senha"));
                lista.add(al);
            }
            st.close();
            
            
        } catch (Exception ex) {
           // Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;    
    }   
     
    
}
