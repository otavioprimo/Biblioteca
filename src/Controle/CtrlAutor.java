/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.DaoAutor;
import Modelo.Autor;
import Modelo.Tabelas.tabelaAutor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class CtrlAutor {

    public static void salvar(String nome) {
        Autor autor = new Autor();

        try {
            autor.setNome(nome);

            DaoAutor dao = new DaoAutor();

            dao.salvar(autor);
            JOptionPane.showMessageDialog(null, "Autor salvo com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar novo Autor\n Verifique se o Autor já existe!\n" + e.getMessage());
        }
    }

    public static void excluir(int id, String nomeAutor) {
        Autor autor = new Autor();

        try {
            autor.setIdAutor(id);
            autor.setNome(nomeAutor);

            DaoAutor dao = new DaoAutor();

            dao.excluir(autor);

            JOptionPane.showMessageDialog(null, "Autor excluido com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir Autor \n" + e.getMessage());
        }
    }

    public static void editar(String nomeNovo, String nomeAtual, int idAtual) {
        Autor autor = new Autor();

        try {
            autor.setNome(nomeNovo);

            DaoAutor dao = new DaoAutor();
            dao.editar(autor, nomeAtual, idAtual);

            JOptionPane.showMessageDialog(null, "Autor editado com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao editar Autor \n Verifique se o Autor já existe!\n" + e.getMessage());
        }
    }

    public static void listarAutor(JTable table) {

        try {
            DaoAutor dao = new DaoAutor();
            Autor autor = new Autor();
            tabelaAutor tm = new tabelaAutor(dao, false, autor);
            table.setModel(tm);
        } catch (SQLException e) {
            Logger.getLogger(CtrlAutor.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar autores  \n" + e.getMessage());
        }
    }

    public static void listarAutorNome(JTable table, String nomeAutor) {

        try {
            DaoAutor dao = new DaoAutor();
            Autor autor = new Autor();
            autor.setNome(nomeAutor);

            tabelaAutor tm = new tabelaAutor(dao, true, autor);
            table.setModel(tm);

        } catch (SQLException e) {
            Logger.getLogger(CtrlAutor.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar autores  \n" + e.getMessage());
        }
    }

    public static int adicionarAutorCB(JComboBox comboBox) {
        int id = 0;

        try {
            Autor autor = new Autor();
            DaoAutor dao = new DaoAutor();

            dao.listarComboBox(comboBox);
            id = autor.getIdAutor();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar ComboBox com Autores \n" + e.getMessage());
        }
        return id;
    }
}
