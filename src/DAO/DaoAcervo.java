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

        cs = connOracle.conn.prepareCall("BEGIN ADD_ACERVO(SQ_ACERVO.NEXTVAL,?,?,sysdate); END;");
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
        cs = connOracle.conn.prepareCall("BEGIN UPDT_ACERVO(?,?,?,?,?); END;");
        cs.setInt(1, acervo.getIdItem());
        cs.setInt(2, acervo.getLivro().getIdLivro());
        cs.setInt(3, acervo.getQuantidade());
        //cs.setString(4, acervo.getDt_entrada());
        cs.setString(4, nomeAtual);
        cs.setInt(5, idPar);
        cs.execute();

        

        connOracle.desconectar();
    }

    public ArrayList<Acervo> listar() throws SQLException {

        connOracle.conectar();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.IDItem, L.IDLivro, L.Titulo, A.Qtd,to_char(A.Dt_Entrada,'DD/MM/YYYY') as Dt_Entrada ");
        sql.append("FROM Acervo A ");
        sql.append("INNER JOIN LIVRO L ");
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

}
