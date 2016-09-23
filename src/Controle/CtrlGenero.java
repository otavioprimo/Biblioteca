/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.DaoAutor;
import DAO.DaoGenero;
import Modelo.Genero;
import Modelo.Tabelas.tabelaAutor;
import Modelo.Tabelas.tabelaGenero;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
     public static void excluir(int id,String tipoGenero) {
        Genero genero = new Genero();

        try {
            genero.setIdGenero(id);
            genero.setTipo(tipoGenero);

            DaoGenero dao = new DaoGenero();

            dao.excluir(genero);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     
     public static void editar(int idNovo, String tipoNovo, String tipoAtual,int idAtual) {
        Genero genero = new Genero();

        try {
            genero.setIdGenero(idNovo);
            genero.setTipo(tipoNovo);
            
            DaoGenero dao = new DaoGenero();
            dao.editar(genero, tipoAtual, idAtual);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
     public static void buscarGenero(JTable table){
         
         try {
            DaoGenero dao = new DaoGenero();            
            tabelaGenero tm = new tabelaGenero(dao);
            table.setModel(tm);
        } catch (SQLException ex) {
            Logger.getLogger(CtrlGenero.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }    
}
