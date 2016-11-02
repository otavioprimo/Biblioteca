/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Estagiario;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author aluno6814
 */
public class DaoEstagiario {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Estagiario estagiario) throws SQLException{
        connOracle.conectar();
        
        CallableStatement cs;
        
        cs = connOracle.conn.prepareCall("");
    }
}
