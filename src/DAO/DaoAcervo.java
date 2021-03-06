/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Acervo;
import Modelo.Livro;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aluno6814
 */
public class DaoAcervo {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Acervo acervo) throws SQLException {
        connOracle.conectar();
        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_ACERVO(?,?,sysdate); END;");
        cs.setInt(1, acervo.getLivro().getIdLivro());
        cs.setInt(2, acervo.getQuantidade());
        //cs.setString(3, acervo.getDt_entrada());
        cs.execute();
        

        connOracle.desconectar();
    }

    public void excluir(Acervo acervo) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN DLT_ACERVO(?,?); END;");
        cs.setInt(1, acervo.getIdItem());
        cs.setString(2, acervo.getLivro().getTitulo());
        cs.execute();
        

        connOracle.desconectar();
    }

    public void editar(Acervo acervo, String nomeAtual, int idPar) throws SQLException {

        connOracle.conectar();

        CallableStatement cs;
        cs = connOracle.conn.prepareCall("BEGIN UPDT_ACERVO(?,?,sysdate,?,?); END;");        
        cs.setInt(1, acervo.getLivro().getIdLivro());
        cs.setInt(2, acervo.getQuantidade());
        //cs.setString(4, acervo.getDt_entrada());
        cs.setString(3, nomeAtual);
        cs.setInt(4, idPar);
        cs.execute();        

        connOracle.desconectar();
    }
    
    public void devolucaoLivro(Acervo acervo) throws SQLException {
        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE acervo SET qtd = qtd + 1 WHERE idLivro = ?");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setInt(1, acervo.getLivro().getIdLivro());
        pst.execute();
        connOracle.desconectar();
    }

    public ArrayList<Acervo> listar() throws SQLException {

        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.IDItem, L.IDLivro, L.Titulo, A.Qtd,to_char(A.Dt_Entrada,'DD/MM/YYYY') as Dt_Entrada,A.IDLivro ");
        sql.append("FROM Acervo A ");
        sql.append("JOIN LIVRO L ");
        sql.append("ON A.IDLivro = L.IDLivro ");
        sql.append("ORDER BY A.IDItem ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        ArrayList<Acervo> lista = new ArrayList<Acervo>();

        while (resultado.next()) {

            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("IDLivro"));
            livro.setTitulo(resultado.getString("Titulo"));

            Acervo acervo = new Acervo();
            acervo.setIdItem(resultado.getInt("IDItem"));
            acervo.setQuantidade(resultado.getInt("Qtd"));
            acervo.setLivro(livro);
            acervo.setDt_entrada(resultado.getString("Dt_Entrada"));

            lista.add(acervo);
        }

        return lista;
    }
    
    public ArrayList<Acervo> listarPeloTitulo(Acervo acervo) throws SQLException {

        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.IDItem, L.IDLivro, L.Titulo, A.Qtd,to_char(A.Dt_Entrada,'DD/MM/YYYY') as Dt_Entrada,A.IdLivro ");
        sql.append("FROM Acervo A ");
        sql.append("JOIN LIVRO L ");
        sql.append("ON A.IDLivro = L.IDLivro ");
        sql.append("WHERE L.Titulo LIKE UPPER(?) ");
        sql.append("ORDER BY A.IDItem DESC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + acervo.getLivro().getTitulo() + "%");
        ResultSet resultado = pst.executeQuery();

        ArrayList<Acervo> lista = new ArrayList<Acervo>();

        while (resultado.next()) {

            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("IDLivro"));
            livro.setTitulo(resultado.getString("Titulo"));

            Acervo a = new Acervo();
            a.setIdItem(resultado.getInt("IDItem"));
            a.setQuantidade(resultado.getInt("Qtd"));
            a.setLivro(livro);
            a.setDt_entrada(resultado.getString("Dt_Entrada"));

            lista.add(a);
        }

        return lista;
    }
    
    

}
