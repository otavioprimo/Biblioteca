/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Livro;
import Modelo.Autor;
import Modelo.Editora;
import Modelo.Genero;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public void editar(Livro livro, int idPar) throws SQLException {
        connOracle.conectar();
        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN UPDT(UPPER(?),?,?,TO_TIMESTAMP(?,'DD/MM/YYYY'),UPPER(?),?,?,UPPER(?),?,UPPER(?),?); END;");
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
        cs.setInt(11, idPar);
        cs.execute();
        connOracle.desconectar();
    }

    public ArrayList<Livro> listar() throws SQLException {
        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT l.idlivro, l.titulo,l.num_pag,l.edicao,to_char(l.dt_lanc,'DD/MM/YYYY') as dt_lancamento,l.isbn,l.pais,l.estante,a.idautor,a.nome_autor,e.ideditora,e.nome_editora,g.idgenero,g.tipo ");
        sql.append("FROM Livro l ");
        sql.append("JOIN Autor a ON a.idautor = l.idautor ");
        sql.append("JOIN Editora e ON e.ideditora = l.ideditora ");
        sql.append("JOIN Genero g ON g.idgenero = l.idgenero ");
        sql.append("ORDER BY idlivro ASC");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        ArrayList<Livro> lista = new ArrayList<Livro>();
        while (resultado.next()) {
            Autor autor = new Autor();
            autor.setIdAutor(resultado.getInt("idautor"));
            autor.setNome(resultado.getString("nome_autor"));

            Genero genero = new Genero();
            genero.setIdGenero(resultado.getInt("idgenero"));
            genero.setTipo(resultado.getString("tipo"));

            Editora editora = new Editora();
            editora.setIdEditora(resultado.getInt("ideditora"));
            editora.setNome(resultado.getString("nome_editora"));

            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("idlivro"));
            livro.setTitulo(resultado.getString("titulo"));
            livro.setNum_pag(resultado.getInt("num_pag"));
            livro.setDt_lanc(resultado.getString("dt_lancamento"));
            livro.setIsbn(resultado.getString("isbn"));
            livro.setPais(resultado.getString("pais"));
            livro.setEstante(resultado.getString("estante"));
            livro.setEdicao(resultado.getString("edicao"));
            livro.setAutor(autor);
            livro.setEditora(editora);
            livro.setGenero(genero);

            lista.add(livro);
        }

        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Livro> listarPorTitulo(Livro l) throws SQLException {
        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT l.idlivro, l.titulo,l.num_pag,l.edicao,to_char(l.dt_lanc,'DD/MM/YYYY') as dt_lancamento,l.isbn,l.pais,l.estante,a.idautor,a.nome_autor,e.ideditora,e.nome_editora,g.idgenero,g.tipo ");
        sql.append("FROM Livro l ");
        sql.append("JOIN Autor a ON a.idautor = l.idautor ");
        sql.append("JOIN Editora e ON e.ideditora = l.ideditora ");
        sql.append("JOIN Genero g ON g.idgenero = l.idgenero ");
        sql.append("WHERE l.titulo LIKE UPPER(?) ");
        sql.append("ORDER BY titulo ASC");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + l.getTitulo() + "%");
        ResultSet resultado = pst.executeQuery();

        ArrayList<Livro> lista = new ArrayList<Livro>();
        while (resultado.next()) {
            Autor autor = new Autor();
            autor.setIdAutor(resultado.getInt("idautor"));
            autor.setNome(resultado.getString("nome_autor"));

            Genero genero = new Genero();
            genero.setIdGenero(resultado.getInt("idgenero"));
            genero.setTipo(resultado.getString("tipo"));

            Editora editora = new Editora();
            editora.setIdEditora(resultado.getInt("ideditora"));
            editora.setNome(resultado.getString("nome_editora"));

            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("idlivro"));
            livro.setTitulo(resultado.getString("titulo"));
            livro.setNum_pag(resultado.getInt("num_pag"));
            livro.setDt_lanc(resultado.getString("dt_lancamento"));
            livro.setIsbn(resultado.getString("isbn"));
            livro.setPais(resultado.getString("pais"));
            livro.setEstante(resultado.getString("estante"));
            livro.setEdicao(resultado.getString("edicao"));
            livro.setAutor(autor);
            livro.setEditora(editora);
            livro.setGenero(genero);

            lista.add(livro);
        }

        connOracle.desconectar();
        return lista;
    }
    
    public ArrayList<Livro> listarPorGenero(Livro l) throws SQLException {
        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT l.idlivro, l.titulo,l.num_pag,l.edicao,to_char(l.dt_lanc,'DD/MM/YYYY') as dt_lancamento,l.isbn,l.pais,l.estante,a.idautor,a.nome_autor,e.ideditora,e.nome_editora,g.idgenero,g.tipo ");
        sql.append("FROM Livro l ");
        sql.append("JOIN Autor a ON a.idautor = l.idautor ");
        sql.append("JOIN Editora e ON e.ideditora = l.ideditora ");
        sql.append("JOIN Genero g ON g.idgenero = l.idgenero ");
        sql.append("WHERE g.tipo LIKE UPPER(?) ");
        sql.append("ORDER BY titulo ASC");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + l.getGenero().getTipo() + "%");
        ResultSet resultado = pst.executeQuery();

        ArrayList<Livro> lista = new ArrayList<Livro>();
        while (resultado.next()) {
            Autor autor = new Autor();
            autor.setIdAutor(resultado.getInt("idautor"));
            autor.setNome(resultado.getString("nome_autor"));

            Genero genero = new Genero();
            genero.setIdGenero(resultado.getInt("idgenero"));
            genero.setTipo(resultado.getString("tipo"));

            Editora editora = new Editora();
            editora.setIdEditora(resultado.getInt("ideditora"));
            editora.setNome(resultado.getString("nome_editora"));

            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("idlivro"));
            livro.setTitulo(resultado.getString("titulo"));
            livro.setNum_pag(resultado.getInt("num_pag"));
            livro.setDt_lanc(resultado.getString("dt_lancamento"));
            livro.setIsbn(resultado.getString("isbn"));
            livro.setPais(resultado.getString("pais"));
            livro.setEstante(resultado.getString("estante"));
            livro.setEdicao(resultado.getString("edicao"));
            livro.setAutor(autor);
            livro.setEditora(editora);
            livro.setGenero(genero);

            lista.add(livro);
        }

        connOracle.desconectar();
        return lista;
    }
    
    public ArrayList<Livro> listarPorAutor(Livro l) throws SQLException {
        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT l.idlivro, l.titulo,l.num_pag,l.edicao,to_char(l.dt_lanc,'DD/MM/YYYY') as dt_lancamento,l.isbn,l.pais,l.estante,a.idautor,a.nome_autor,e.ideditora,e.nome_editora,g.idgenero,g.tipo ");
        sql.append("FROM Livro l ");
        sql.append("JOIN Autor a ON a.idautor = l.idautor ");
        sql.append("JOIN Editora e ON e.ideditora = l.ideditora ");
        sql.append("JOIN Genero g ON g.idgenero = l.idgenero ");
        sql.append("WHERE a.nome_autor LIKE UPPER(?) ");
        sql.append("ORDER BY titulo ASC");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + l.getAutor().getNome() + "%");
        ResultSet resultado = pst.executeQuery();

        ArrayList<Livro> lista = new ArrayList<Livro>();
        while (resultado.next()) {
            Autor autor = new Autor();
            autor.setIdAutor(resultado.getInt("idautor"));
            autor.setNome(resultado.getString("nome_autor"));

            Genero genero = new Genero();
            genero.setIdGenero(resultado.getInt("idgenero"));
            genero.setTipo(resultado.getString("tipo"));

            Editora editora = new Editora();
            editora.setIdEditora(resultado.getInt("ideditora"));
            editora.setNome(resultado.getString("nome_editora"));

            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("idlivro"));
            livro.setTitulo(resultado.getString("titulo"));
            livro.setNum_pag(resultado.getInt("num_pag"));
            livro.setDt_lanc(resultado.getString("dt_lancamento"));
            livro.setIsbn(resultado.getString("isbn"));
            livro.setPais(resultado.getString("pais"));
            livro.setEstante(resultado.getString("estante"));
            livro.setEdicao(resultado.getString("edicao"));
            livro.setAutor(autor);
            livro.setEditora(editora);
            livro.setGenero(genero);

            lista.add(livro);
        }

        connOracle.desconectar();
        return lista;
    }
}
