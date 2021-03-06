/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Genero;
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
public class DaoGenero {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Genero genero) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;
        cs = connOracle.conn.prepareCall("BEGIN ADD_GENERO(UPPER(?)); END;");
        cs.setString(1, genero.getTipo());
        cs.execute();

        connOracle.desconectar();
    }

    public void excluir(Genero genero) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;
        cs = connOracle.conn.prepareCall("BEGIN DLT_GENERO(?,?); END;");
        cs.setInt(1, genero.getIdGenero());
        cs.setString(2, genero.getTipo());
        cs.execute();

        connOracle.desconectar();

    }

    public void editar(Genero genero, String tipoAnt, int idPar) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN UPDT_GENERO(UPPER(?),?,?); END;");
        cs.setString(1, genero.getTipo());
        cs.setString(2, tipoAnt);
        cs.setInt(3, idPar);
        cs.execute();

        connOracle.desconectar();
    }

    public ArrayList<Genero> listar() throws SQLException {
        ArrayList<Genero> lista = new ArrayList<>();

        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDGenero, tipo ");
        sql.append("FROM Genero ");
        sql.append("ORDER BY IDGenero ASC");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Genero genero = new Genero();
            genero.setIdGenero(resultado.getInt("IDGenero"));
            genero.setTipo(resultado.getString("Tipo"));

            lista.add(genero);
        }
        connOracle.desconectar();

        return lista;
    }

    public ArrayList<Genero> listarPorTipo(Genero genero) throws SQLException {
        connOracle.conectar();
        ArrayList<Genero> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDGenero, tipo ");
        sql.append("FROM Genero ");
        sql.append("WHERE Tipo LIKE UPPER(?) ");
        sql.append("ORDER BY IDGenero ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + genero.getTipo() + "%");

        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Genero g = new Genero();
            g.setIdGenero(resultado.getInt("IDGenero"));
            g.setTipo(resultado.getString("Tipo"));

            lista.add(g);
        }
        connOracle.desconectar();

        return lista;
    }
    
     public void listarComboBox(JComboBox comboBox) throws SQLException {

        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDGenero, TIpo ");
        sql.append("FROM GENERO ");
        sql.append("ORDER BY Tipo ASC");

        comboBox.removeAllItems();
        comboBox.addItem("Escolha um Genero");
        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("IDGenero");
            String tipo = resultado.getString("Tipo");
            comboBox.addItem(new Genero(id, tipo));            
            comboBox.updateUI();
        }
    }
}
