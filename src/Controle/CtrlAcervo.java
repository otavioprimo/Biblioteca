/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.Acervo;
import Modelo.Livro;
import DAO.DaoAcervo;
import Modelo.Tabelas.tabelaAcervo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class CtrlAcervo {

    public static void salvar(int livroIdPar, int qtd) {
        Acervo acervo = new Acervo();
        Livro livro = new Livro();

        try {
            //acervo.setDt_entrada(dt_entrada);
            acervo.setQuantidade(qtd);
            livro.setIdLivro(livroIdPar);
            acervo.setLivro(livro);

            DaoAcervo dao = new DaoAcervo();
            dao.salvar(acervo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar novo item \n" + e.getMessage());
        }
    }

    public static void excluir(int idAcervo, String tituloLivro) {
        Acervo acervo = new Acervo();
        Livro livro = new Livro();

        try {
            acervo.setIdItem(idAcervo);
            livro.setTitulo(tituloLivro);

            DaoAcervo dao = new DaoAcervo();
            dao.excluir(acervo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir novo item \n" + e.getMessage());
        }
    }

    public static void editar(int novoIdAcervo, int novoIdLivro, int novaQtd,String novaDataEntrada, int idEditar, String nomeAtual) {
        Acervo acervo = new Acervo();
        Livro livro = new Livro();

        try {
            livro.setIdLivro(novoIdLivro);

            acervo.setIdItem(novoIdAcervo);
            acervo.setLivro(livro);
            acervo.setQuantidade(novaQtd);
            acervo.setDt_entrada(novaDataEntrada);

            DaoAcervo dao = new DaoAcervo();
            dao.editar(acervo, nomeAtual, idEditar);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao editar novo item \n" + e.getMessage());
        }
    }

    public static void listarAcervo(JTable table) {
        DaoAcervo dao = new DaoAcervo();
        /*try {
            ArrayList<Acervo> lista = dao.listar();
            for (Acervo a : lista) {
                System.out.println("IdItem: " + a.getIdItem());
                System.out.println("Titulo: " + a.getLivro().getTitulo());
                System.out.println("Quantidade: " + a.getQuantidade());
                System.out.println("Data De Entrada: " + a.getDt_entrada());
                System.out.println("--------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println(e);
            //Logger.getLogger(CtrlAutor.class.getName()).log(Level.SEVERE, null, e);
        }*/

        try {
            tabelaAcervo tm = new tabelaAcervo(dao);            
            table.setModel(tm);
        } catch (SQLException e) {
            Logger.getLogger(CtrlAcervo.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar item \n" + e.getMessage());
        }

    }

}
