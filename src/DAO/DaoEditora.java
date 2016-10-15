/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Editora;
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
public class DaoEditora {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Editora editora) throws SQLException {
        connOracle.conectar();
        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_EDITORA(UPPER(?),?,?); END;");
        cs.setString(1, editora.getNome());
        cs.setString(2, editora.getCnpj());
        cs.setString(3, editora.getCnpj());
        cs.execute();

        connOracle.desconectar();
    }

    public void excluir(Editora editora) throws SQLException {
        connOracle.conectar();
        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN DLT_EDITORA(?,?); END;");
        cs.setInt(1, editora.getIdEditora());
        cs.setString(2, editora.getNome());

        cs.execute();

        connOracle.desconectar();
    }

    public void editar(Editora editora, int idPar) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN UPDT_EDITORA(UPPER(?),?,?,?); END;");
        cs.setString(1, editora.getNome());
        cs.setString(2, editora.getCnpj());
        cs.setString(3, editora.getEmail());
        cs.setInt(4, idPar);
        cs.execute();

        connOracle.desconectar();
    }

    public ArrayList<Editora> listar() throws SQLException {
        ArrayList<Editora> lista = new ArrayList<>();
        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDEditora, nome, email ,cnpj ");
        sql.append("FROM EDITORA ");
        sql.append("ORDER BY IDEditora ASC");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();
        while (resultado.next()) {
            Editora editora = new Editora();
            editora.setIdEditora(resultado.getInt("IDEditora"));
            editora.setNome(resultado.getString("nome"));
            editora.setEmail(resultado.getString("email"));
            editora.setCnpj(resultado.getString("cnpj"));

            lista.add(editora);
        }
        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Editora> listarPorNome(Editora editora) throws SQLException {
        connOracle.conectar();
        ArrayList<Editora> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDEditora, nome, email ,cnpj ");
        sql.append("FROM EDITORA ");
        sql.append("WHERE Nome LIKE UPPER(?) ");
        sql.append("ORDER BY IDEditora ASC ");
        
        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + editora.getNome() + "%");
        ResultSet resultado = pst.executeQuery();
        
        while (resultado.next()) {
            Editora e = new Editora();
            e.setIdEditora(resultado.getInt("IDEditora"));
            e.setNome(resultado.getString("nome"));
            e.setEmail(resultado.getString("email"));
            e.setCnpj(resultado.getString("cnpj"));

            lista.add(e);
        }
        connOracle.desconectar();
        return lista;
    }

    public void listarComboBox(JComboBox comboBox) throws SQLException {

        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDEditora, nome, email ,cnpj ");
        sql.append("FROM EDITORA ");
        sql.append("ORDER BY IDEditora ASC");

        comboBox.removeAllItems();
        comboBox.addItem("Escolha uma Editora");
        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();
        while (resultado.next()) {

            comboBox.addItem(resultado.getString("nome"));
            comboBox.updateUI();
        }
    }

}
