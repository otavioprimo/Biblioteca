/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.DaoLivro;
import Modelo.Autor;
import Modelo.Editora;
import Modelo.Genero;
import Modelo.Livro;
import Modelo.Tabelas.tabelaLivro;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class CtrlLivro {

    public static void salvar(String titulo, int idautor, int num_pag, String dt_lanc, String edicao, String isbn, int ideditora, String pais, int idgenero, String estante) {
        Genero genero = new Genero();
        Autor autor = new Autor();
        Editora editora = new Editora();
        Livro livro = new Livro();

        try {
            genero.setIdGenero(idgenero);
            autor.setIdAutor(idautor);
            editora.setIdEditora(ideditora);

            livro.setTitulo(titulo);
            livro.setNum_pag(num_pag);
            livro.setDt_lanc(dt_lanc);
            livro.setEdicao(edicao);
            livro.setIsbn(isbn);
            livro.setPais(pais);
            livro.setEstante(estante);
            livro.setAutor(autor);
            livro.setGenero(genero);
            livro.setEditora(editora);

            DaoLivro dao = new DaoLivro();
            dao.salvar(livro);
            JOptionPane.showMessageDialog(null, "Novo livro adicionado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar novo livro \n" + e.getMessage());
        }
    }

    public static void excluir(int idLivro, String titulo) {
        Livro livro = new Livro();

        try {
            livro.setIdLivro(idLivro);
            livro.setTitulo(titulo);

            DaoLivro dao = new DaoLivro();
            dao.excluir(livro);
            JOptionPane.showMessageDialog(null, "Livro excluido com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente \n" + e.getMessage());
        }
    }

    public static void editar(String titulo, int idautor, int num_pag, String dt_lanc, String edicao, String isbn, int ideditora, String pais, int idgenero, String estante, int idPar) {
        Genero genero = new Genero();
        Autor autor = new Autor();
        Editora editora = new Editora();
        Livro livro = new Livro();

        try {
            genero.setIdGenero(idgenero);
            autor.setIdAutor(idautor);
            editora.setIdEditora(ideditora);

            livro.setTitulo(titulo);
            livro.setNum_pag(num_pag);
            livro.setDt_lanc(dt_lanc);
            livro.setEdicao(edicao);
            livro.setIsbn(isbn);
            livro.setPais(pais);
            livro.setEstante(estante);
            livro.setAutor(autor);
            livro.setGenero(genero);
            livro.setEditora(editora);

            DaoLivro dao = new DaoLivro();
            dao.editar(livro, idPar);
            JOptionPane.showMessageDialog(null, "Livro editado com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao ditar livro \n" + e.getMessage());
        }
    }

    public static void listarLivros(JTable table) {
        Livro livro = new Livro();
        DaoLivro dao = new DaoLivro();
        try {
            tabelaLivro tm = new tabelaLivro(dao, 1, livro);
            table.setModel(tm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar Livros  \n" + e.getMessage());
        }
    }

    public static void listarLivrosTitulo(JTable table, String titulo) {
        Livro livro = new Livro();
        DaoLivro dao = new DaoLivro();
        try {
            livro.setTitulo(titulo);
            tabelaLivro tm = new tabelaLivro(dao, 2, livro);
            table.setModel(tm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar Livros  \n" + e.getMessage());
        }
    }

    public static void listarLivrosGenero(JTable table, String genero) {
        Livro livro = new Livro();
        Genero g = new Genero();
        DaoLivro dao = new DaoLivro();
        try {
            g.setTipo(genero);
            livro.setGenero(g);
            tabelaLivro tm = new tabelaLivro(dao, 3, livro);
            table.setModel(tm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar Livros  \n" + e.getMessage());
        }
    }

    public static void listarLivrosAutor(JTable table, String autor) {
        Livro livro = new Livro();
        Autor a = new Autor();
        DaoLivro dao = new DaoLivro();
        try {
            a.setNome(autor);
            livro.setAutor(a);
            tabelaLivro tm = new tabelaLivro(dao, 4, livro);
            table.setModel(tm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar Livros  \n" + e.getMessage());
        }
    }
}
