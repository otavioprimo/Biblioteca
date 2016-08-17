/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.modCliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno6813
 */
public class DAOCliente {
    ConnectionFactory connOracle = new ConnectionFactory();
    modCliente mCliente = new modCliente();
    
    public void salvarCliente(modCliente mcliente){
        
        connOracle.conectar();
        String sql = "INSERT INTO  CLIENTE  (codigo,nome) VALUES (?,?)";
        try {
            PreparedStatement pst = connOracle.conn.prepareStatement(sql);
            pst.setInt(1, mCliente.getCodigo());
            pst.setString(2 , mCliente.getNome());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Cliente incluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir dados do cliente" + ex.getMessage());
        }
    }
}
