/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Emprestimo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author aluno6814
 */
public class DaoEmprestimo {
    
    ConnectionFactory connOracle = new ConnectionFactory();
    
    public void salvar(Emprestimo emprestimo) throws SQLException{
        
    }
}
