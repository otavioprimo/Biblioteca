/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Livro;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author aluno6814
 */
public class DaoLivro {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Livro livro) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_LIVRO(UPPER(?),?,?,TO_TIMESTAMP(?,'DD/MM/YYYY'),UPPER(?),?,?,UPPER(?),?,UPPER(?)); END;");
        cs.setString(1, livro.getTitulo());
        cs.setInt(2, livro.getAutor().getIdAutor());
        cs.setInt(3, livro.getNum_pag());
        cs.setString(4, livro.getDt_lanc());
        cs.setString(5, livro.getEdicao());
        cs.setString(6, livro.getIsbn());
        cs.setInt(7, livro.getEditora().getIdEditora());
        cs.setString(8, livro.getPais());
        cs.setInt(9, livro.getGenero().getIdGenero());
        cs.setString(10, livro.getEstante());
        cs.execute();
        connOracle.desconectar();
    }

    public void excluir(Livro livro) throws SQLException {
        connOracle.conectar();
        CallableStatement cs;
        
        cs = connOracle.conn.prepareCall("BEGIN DLT_LIVRO(?,UPPER(?)); END;");
        cs.setInt(1, livro.getIdLivro());
        cs.setString(2, livro.getTitulo());
        cs.execute();
        connOracle.desconectar();
    }

    public void editar(Livro livro) throws SQLException {
        
    }
}
