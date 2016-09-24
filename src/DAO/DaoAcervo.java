/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Acervo;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno6814
 */
public class DaoAcervo {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Acervo acervo) throws SQLException {
        connOracle.conectar();
        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_ACERVO(SQ_ACERVO.NEXTVAL,?,?,?); END;");
        cs.setInt(1, acervo.getLivro().getIdLivro());
        cs.setInt(2, acervo.getQuantidade());
        cs.setString(3, acervo.getDt_entrada());
        cs.execute();
        JOptionPane.showMessageDialog(null, "Novo item adicionado ao acervo");

        connOracle.desconectar();
    }

    public void excluir(Acervo acervo) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN DLT_ACERVO(?,?); END;");
        cs.setInt(1, acervo.getIdItem());
        cs.setString(2, acervo.getLivro().getTitulo());
        cs.execute();
        JOptionPane.showMessageDialog(null, "Item deletado do acervo");

        connOracle.desconectar();
    }

    public void alterar(Acervo acervo,String nomeAnterior, int idPar) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;
        cs = connOracle.conn.prepareCall("BEGIN UPDT_ACERVO(?,?,?,?,?,?); END;");
        cs.setInt(1, acervo.getIdItem());
        cs.setInt(2, acervo.getLivro().getIdLivro());
        cs.setInt(3, acervo.getQuantidade());
        cs.setString(4, acervo.getDt_entrada());
        cs.setString(5, nomeAnterior);
        cs.setInt(6, idPar);
        cs.execute();      
                
        connOracle.desconectar();
    }

}
