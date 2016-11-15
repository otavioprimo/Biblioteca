/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Autor;
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
public class DaoAutor {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Autor autor) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_AUTOR(UPPER(?)); END;");
        cs.setString(1, autor.getNome());
        cs.execute();

        connOracle.desconectar();
    }

    public void excluir(Autor autor) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN DLT_AUTOR(?,?); END;");
        cs.setInt(1, autor.getIdAutor());
        cs.setString(2, autor.getNome());
        cs.execute();

        connOracle.desconectar();
    }

    public void editar(Autor autor, String nomeAnt, int idPar) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN UPDT_AUTOR(UPPER(?),?,?); END;");        
        cs.setString(1, autor.getNome());
        cs.setString(2, nomeAnt);
        cs.setInt(3, idPar);
        cs.execute();

        connOracle.desconectar();

    }

    public ArrayList<Autor> listar() throws SQLException {
        ArrayList<Autor> lista = new ArrayList<>();
        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDAutor, Nome_Autor ");
        sql.append("FROM Autor ");
        sql.append("ORDER BY IDAutor ASC");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Autor autor = new Autor();
            autor.setIdAutor(resultado.getInt("IDAutor"));
            autor.setNome(resultado.getString("Nome_Autor"));

            lista.add(autor);
        }
        connOracle.desconectar();

        return lista;
    }

    public void listarComboBox(JComboBox comboBox) throws SQLException {

        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDAutor, Nome_Autor ");
        sql.append("FROM Autor ");
        sql.append("ORDER BY Nome_Autor ASC");

        comboBox.removeAllItems();
        comboBox.addItem("Escolha Autor");
        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("IDAutor");
            String nome = resultado.getString("Nome_Autor");
            comboBox.addItem(new Autor(id,nome));
            comboBox.updateUI();
        }

    }

    public ArrayList<Autor> listarPorNome(Autor autor) throws SQLException {
        connOracle.conectar();
        ArrayList<Autor> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDAutor,Nome_Autor ");
        sql.append("FROM Autor ");
        sql.append("WHERE Nome_Autor LIKE UPPER(?) ");
        sql.append("ORDER BY IDAutor ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());

        pst.setString(1, "%" + autor.getNome() + "%");
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Autor a = new Autor();
            a.setIdAutor(resultado.getInt("IDAutor"));
            a.setNome(resultado.getString("Nome_Autor"));

            lista.add(a);
        }
        connOracle.desconectar();

        return lista;
    }

}
