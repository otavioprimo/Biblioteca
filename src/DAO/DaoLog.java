/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Log;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexao.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author aluno6814
 */
public class DaoLog {

    ConnectionFactory connOracle = new ConnectionFactory();

    public ArrayList<Log> listar() throws SQLException {

        ArrayList<Log> lista = new ArrayList<>();
        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDLog,Tabela,Motivo,Conteudo,Mudanca,TO_CHAR(Dt_Log,'DD/MM/YYYY hh24:MI:ss') as Dt_Log ");
        sql.append("FROM Log ");
        sql.append("ORDER BY IdLog DESC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Log log = new Log();
            log.setIdLog(resultado.getInt("IDLog"));
            log.setTabela(resultado.getString("Tabela"));
            log.setMotivo(resultado.getString("Motivo"));
            log.setConteudo(resultado.getString("Conteudo"));
            log.setMudanca(resultado.getString("Mudanca"));
            log.setDt_log(resultado.getString("Dt_Log"));

            lista.add(log);
        }

        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Log> listarDataEspecifica(Log log) throws SQLException {

        ArrayList<Log> lista = new ArrayList<>();
        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDLog,Tabela,Motivo,Conteudo,Mudanca,TO_CHAR(Dt_Log,'DD/MM/YYYY HH:MI:SS') as Dt_Log ");
        sql.append("FROM Log ");
        sql.append("WHERE TO_DATE(Dt_Log) = ?");
        sql.append("ORDER BY IdLog DESC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, log.getDt_log());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Log l = new Log();
            l.setIdLog(resultado.getInt("IDLog"));
            l.setTabela(resultado.getString("Tabela"));
            l.setMotivo(resultado.getString("Motivo"));
            l.setConteudo(resultado.getString("Conteudo"));
            l.setMudanca(resultado.getString("Mudanca"));
            l.setDt_log(resultado.getString("Dt_Log"));

            lista.add(l);
        }

        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Log> listarDataMaior(Log log) throws SQLException {

        ArrayList<Log> lista = new ArrayList<>();
        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDLog,Tabela,Motivo,Conteudo,Mudanca,TO_CHAR(Dt_Log,'DD/MM/YYYY HH:MI:SS') as Dt_Log ");
        sql.append("FROM Log ");
        sql.append("WHERE TO_DATE(Dt_Log) >= ?");
        sql.append("ORDER BY IdLog DESC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, log.getDt_log());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Log l = new Log();
            l.setIdLog(resultado.getInt("IDLog"));
            l.setTabela(resultado.getString("Tabela"));
            l.setMotivo(resultado.getString("Motivo"));
            l.setConteudo(resultado.getString("Conteudo"));
            l.setMudanca(resultado.getString("Mudanca"));
            l.setDt_log(resultado.getString("Dt_Log"));

            lista.add(l);
        }

        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Log> listarDataMenor(Log log) throws SQLException {

        ArrayList<Log> lista = new ArrayList<>();
        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDLog,Tabela,Motivo,Conteudo,Mudanca,TO_CHAR(Dt_Log,'DD/MM/YYYY HH:MI:SS') as Dt_Log ");
        sql.append("FROM Log ");
        sql.append("WHERE TO_DATE(Dt_Log) <= ?");
        sql.append("ORDER BY IdLog DESC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, log.getDt_log());
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Log l = new Log();
            l.setIdLog(resultado.getInt("IDLog"));
            l.setTabela(resultado.getString("Tabela"));
            l.setMotivo(resultado.getString("Motivo"));
            l.setConteudo(resultado.getString("Conteudo"));
            l.setMudanca(resultado.getString("Mudanca"));
            l.setDt_log(resultado.getString("Dt_Log"));

            lista.add(l);
        }

        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Log> listarNomeTabela(Log log) throws SQLException {

        ArrayList<Log> lista = new ArrayList<>();
        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT IDLog,Tabela,Motivo,Conteudo,Mudanca,TO_CHAR(Dt_Log,'DD/MM/YYYY HH:MI:SS') as Dt_Log ");
        sql.append("FROM Log ");
        sql.append("WHERE Tabela LIKE ? ");
        sql.append("ORDER BY IdLog DESC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + log.getTabela() + "%");
        ResultSet resultado = pst.executeQuery();

        while (resultado.next()) {
            Log l = new Log();
            l.setIdLog(resultado.getInt("IDLog"));
            l.setTabela(resultado.getString("Tabela"));
            l.setMotivo(resultado.getString("Motivo"));
            l.setConteudo(resultado.getString("Conteudo"));
            l.setMudanca(resultado.getString("Mudanca"));
            l.setDt_log(resultado.getString("Dt_Log"));

            lista.add(l);
        }

        connOracle.desconectar();
        return lista;
    }

}
