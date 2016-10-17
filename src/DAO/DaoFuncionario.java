/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Funcionario;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aluno6814
 */
public class DaoFuncionario {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Funcionario funcionario) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_FUNC(UPPER(?),UPPER(?),?,?,?,?,?,?,?); END;");
        cs.setString(1, funcionario.getNome());
        cs.setString(2, funcionario.getRg());
        cs.setString(3, funcionario.getCpf());
        cs.setString(4, funcionario.getTelefone());
        cs.setString(5, funcionario.getEmail());
        cs.setString(6, funcionario.getLogin());
        cs.setString(7, funcionario.getSenha());
        cs.setString(8, String.valueOf(funcionario.getCargo()));
        cs.setString(9, String.valueOf(funcionario.getAtivo()));
        cs.execute();
        connOracle.desconectar();
    }

    public void excluir(Funcionario funcionario) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;
        cs = connOracle.conn.prepareCall("BEGIN DLT_FUNC(?,UPPER(?)); END;");
        cs.setInt(1, funcionario.getIdFuncionario());
        cs.setString(2, funcionario.getNome());
        cs.execute();
        connOracle.desconectar();
    }

    public void editar(Funcionario funcionario, String nomeAnterior, int idPar) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN UPDT_FUNC(UPPER(?),UPPER(?),?,?,?,?,?,?,?,?); END;");
        cs.setString(1, funcionario.getNome());
        cs.setString(2, funcionario.getRg());
        cs.setString(3, funcionario.getCpf());
        cs.setString(4, funcionario.getTelefone());
        cs.setString(5, funcionario.getEmail());
        cs.setString(6, funcionario.getLogin());        
        cs.setString(7, String.valueOf(funcionario.getCargo()));
        cs.setString(8, String.valueOf(funcionario.getAtivo()));
        cs.setInt(9, idPar);
        cs.setString(10, nomeAnterior);

        cs.execute();
        connOracle.desconectar();
    }

    public ArrayList<Funcionario> listar() throws SQLException {
        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDFunc, Nome, RG, Cpf, Tel, Email, Login, Cargo, Ativo ");
        sql.append("FROM Funcionario ");
        sql.append("ORDER BY IDFunc ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();

        while (resultado.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(resultado.getInt("IDFunc"));
            funcionario.setNome(resultado.getString("Nome"));
            funcionario.setRg(resultado.getString("RG"));
            funcionario.setCpf(resultado.getString("Cpf"));
            funcionario.setTelefone(resultado.getString("Tel"));
            funcionario.setEmail(resultado.getString("Email"));
            funcionario.setLogin(resultado.getString("Login"));
            funcionario.setCargo(resultado.getString("Cargo").charAt(0));
            funcionario.setAtivo(resultado.getString("Ativo").charAt(0));

            lista.add(funcionario);
        }
        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Funcionario> listarPorNome(Funcionario f) throws SQLException {
        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDFunc, Nome, RG, Cpf, Tel, Email, Login, Cargo, Ativo ");
        sql.append("FROM Funcionario ");
        sql.append("WHERE Nome LIKE UPPER(?) ");
        sql.append("ORDER BY Nome ASC ");

        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + f.getNome() + "%");
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(resultado.getInt("IDFunc"));
            funcionario.setNome(resultado.getString("Nome"));
            funcionario.setRg(resultado.getString("RG"));
            funcionario.setCpf(resultado.getString("Cpf"));
            funcionario.setTelefone(resultado.getString("Tel"));
            funcionario.setEmail(resultado.getString("Email"));
            funcionario.setLogin(resultado.getString("Login"));
            funcionario.setCargo(resultado.getString("Cargo").charAt(0));
            funcionario.setAtivo(resultado.getString("Ativo").charAt(0));

            lista.add(funcionario);
        }

        return lista;
    }
    
    public ArrayList<Funcionario> listarPorRG(Funcionario f) throws SQLException {
        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDFunc, Nome, RG, Cpf, Tel, Email, Login, Cargo, Ativo ");
        sql.append("FROM Funcionario ");
        sql.append("WHERE Rg LIKE UPPER(?) ");
        sql.append("ORDER BY IDFunc ASC ");

        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + f.getRg() + "%");
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(resultado.getInt("IDFunc"));
            funcionario.setNome(resultado.getString("Nome"));
            funcionario.setRg(resultado.getString("RG"));
            funcionario.setCpf(resultado.getString("Cpf"));
            funcionario.setTelefone(resultado.getString("Tel"));
            funcionario.setEmail(resultado.getString("Email"));
            funcionario.setLogin(resultado.getString("Login"));
            funcionario.setCargo(resultado.getString("Cargo").charAt(0));
            funcionario.setAtivo(resultado.getString("Ativo").charAt(0));

            lista.add(funcionario);
        }

        return lista;
    }

}
