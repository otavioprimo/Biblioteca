/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.DaoGenero;
import Modelo.Genero;
import Modelo.Tabelas.tabelaGenero;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class CtrlGenero {

    public static void salvar(String tipo) {
        Genero genero = new Genero();

        try {
            genero.setTipo(tipo);

            DaoGenero dao = new DaoGenero();

            dao.salvar(genero);

            JOptionPane.showMessageDialog(null, "Genero salvo com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar novo Genero \n Verifique se o Genero já existe \n" + e.getMessage());
        }
    }

    public static void excluir(int id, String tipoGenero) {
        Genero genero = new Genero();

        try {
            genero.setIdGenero(id);
            genero.setTipo(tipoGenero);

            DaoGenero dao = new DaoGenero();

            dao.excluir(genero);
            JOptionPane.showMessageDialog(null, "Genero excluido com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir novo Genero \n" + e.getMessage());

        }
    }

    public static void editar(int idNovo, String tipoNovo, String tipoAtual, int idAtual) {
        Genero genero = new Genero();

        try {
            genero.setIdGenero(idNovo);
            genero.setTipo(tipoNovo);

            DaoGenero dao = new DaoGenero();
            dao.editar(genero, tipoAtual, idAtual);
            JOptionPane.showMessageDialog(null, "Genero editado com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao editar novo Genero \n Verifique se o Genero já existe \n" + e.getMessage());

        }
    }

    public static void listarGenero(JTable table) {

        try {
            DaoGenero dao = new DaoGenero();
            Genero genero = new Genero();
            tabelaGenero tm = new tabelaGenero(dao, false, genero);
            table.setModel(tm);
        } catch (SQLException e) {
            Logger.getLogger(CtrlGenero.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar  Genero \n" + e.getMessage());

        }
    }

    public static void listarGeneroTipo(JTable table, String tipo) {

        try {
            DaoGenero dao = new DaoGenero();
            Genero genero = new Genero();
            genero.setTipo(tipo);

            tabelaGenero tm = new tabelaGenero(dao, true, genero);
            table.setModel(tm);

        } catch (SQLException e) {
            Logger.getLogger(CtrlGenero.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar  Genero \n" + e.getMessage());

        }
    }

}
