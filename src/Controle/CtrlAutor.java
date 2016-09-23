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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void excluir(int id, String nomeAutor) {
        Autor autor = new Autor();

        try {
            autor.setIdAutor(id);
            autor.setNome(nomeAutor);

            DaoAutor dao = new DaoAutor();

            dao.excluir(autor);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editar(int idNovo, String nomeNovo, String nomeAtual, int idAtual) {
        Autor autor = new Autor();

        try {
            autor.setIdAutor(idNovo);
            autor.setNome(nomeNovo);

            DaoAutor dao = new DaoAutor();
            dao.editar(autor, nomeAtual, idAtual);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscarAutor(JTable table) {
        /*DaoAutor dao = new DaoAutor();
        Autor autor = new Autor();
        try {
            ArrayList<Autor> lista = dao.listar();

            for (Autor a : lista) {
                System.out.println("Código: " + a.getIdAutor());
                System.out.println("Nome: " + a.getNome());
                System.out.println();                

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        try {
            DaoAutor dao = new DaoAutor();
            tabelaAutor tm = new tabelaAutor(dao);
            table.setModel(tm);
        } catch (SQLException ex) {
            Logger.getLogger(CtrlAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void adicionarAutorCB(JComboBox comboBox) {

        try {
            Autor autor = new Autor();
            DaoAutor dao = new DaoAutor();

            dao.listarComboBox(comboBox);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
