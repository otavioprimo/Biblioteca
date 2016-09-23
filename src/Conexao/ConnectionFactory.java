/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno6813
 */
public class ConnectionFactory {
    
    public java.sql.Statement stm;
    public ResultSet rs;
    public Connection conn;

    //string da parada    
    private String ocaminho = "jdbc:oracle:thin:@localhost:1521:XE";
    private String odriver = "oracle.jdbc.driver.OracleDriver";
    private String ousuario = "SYSTEM";
    //private String osenha = "oracle"; 
    private String osenha = "5886630";
    
    
    public void conectar(){
        try {
            System.setProperty("jdbc:Drivers", odriver);
            conn = DriverManager.getConnection(ocaminho, ousuario, osenha);
            //JOptionPane.showMessageDialog(null, "Conectado com sucesso");
            
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar. :\n" + se.getMessage());
        }
    }
    public void desconectar(){
        try {
            conn.close();
            //JOptionPane.showMessageDialog(null, "Desconectado com sucesso");
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar. :\n" + se.getMessage());
        }
    }
    
     public void executaSql(String sql) {
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs  = stm.executeQuery(sql);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL. : \n" + ex.getMessage());
        }       
        
    }
    
}
