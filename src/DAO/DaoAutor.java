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
import javax.swing.JOptionPane;

/**
 *
 * @author aluno6814
 */
public class DaoAutor {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Autor autor) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_AUTOR(SQ_AUTOR.NEXTVAL,UPPER(?)); END;");
        cs.setString(1, autor.getNome());
        cs.execute();
        JOptionPane.showMessageDialog(null, "Autor salvo com sucesso");

        connOracle.desconectar();
    }

    public void excluir(Autor autor) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN DLT_AUTOR(?,?); END;");
        cs.setInt(1, autor.getIdAutor());
        cs.setString(2, autor.getNome());
        cs.execute();

        JOptionPane.showMessageDialog(null, "Autor excluido com sucesso");

        connOracle.desconectar();
    }

    public void editar(Autor autor, String nomeAnt, int idNovo) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN UPDT_AUTOR(?,UPPER(?),?,?); END;");
        cs.setInt(1, autor.getIdAutor());
        cs.setString(2, autor.getNome());
        cs.setString(3, nomeAnt);
        cs.setInt(4, idNovo);
        cs.execute();

        JOptionPane.showMessageDialog(null, "Autor editado com sucesso");

        connOracle.desconectar();

    }

    public ArrayList<Autor> listar() throws SQLException {

        ArrayList<Autor> lista = new ArrayList<>();
        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDAutor, Nome ");
        sql.append("FROM Autor ");
        sql.append("ORDER BY IDAutor ASC");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Autor autor = new Autor();
            autor.setIdAutor(resultado.getInt("IDAutor"));
            autor.setNome(resultado.getString("Nome"));

            lista.add(autor);
        }
        connOracle.desconectar();

        return lista;
    }

    public void listarComboBox(JComboBox comboBox) throws SQLException {

        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT Nome ");
        sql.append("FROM Autor ");
        sql.append("ORDER BY Nome ASC");

        comboBox.removeAllItems();
        comboBox.addItem("Escolha Autor");
        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {

            comboBox.addItem(resultado.getString("Nome"));
            comboBox.updateUI();
        }

    }

    public ArrayList<Autor> listarPorNome(Autor autor) throws SQLException {
        connOracle.conectar();
        ArrayList<Autor> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDAutor,Nome ");
        sql.append("FROM Autor ");
        sql.append("WHERE Nome LIKE UPPER(?) ");
        sql.append("ORDER BY IDAutor ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());

        pst.setString(1, "%" + autor.getNome() + "%");
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Autor a = new Autor();
            a.setIdAutor(resultado.getInt("IDAutor"));
            a.setNome(resultado.getString("Nome"));

            lista.add(a);
        }
        connOracle.desconectar();

        return lista;
    }

}
