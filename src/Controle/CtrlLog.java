/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.DaoLog;
import Modelo.Log;
import Modelo.Tabelas.tabelaLog;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class CtrlLog {

    public static void listarLog(JTable table) {
        try {
            DaoLog dao = new DaoLog();
            Log log = new Log();
            tabelaLog tm = new tabelaLog(dao, 1, log);
            table.setModel(tm);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar log  \n" + e.getMessage());
        }
    }

    public static void listarDataLog(JTable table, String data) {
        try {
            DaoLog dao = new DaoLog();
            Log log = new Log();
            log.setDt_log(data);
            tabelaLog tm = new tabelaLog(dao, 2, log);
            table.setModel(tm);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar log  \n" + e.getMessage());
        }
    }

    public static void listarDataLogMaior(JTable table, String data) {
        try {
            DaoLog dao = new DaoLog();
            Log log = new Log();
            log.setDt_log(data);
            tabelaLog tm = new tabelaLog(dao, 3, log);
            table.setModel(tm);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar log  \n" + e.getMessage());
        }
    }

    public static void listarDataLogMenor(JTable table, String data) {
        try {
            DaoLog dao = new DaoLog();
            Log log = new Log();
            log.setDt_log(data);
            tabelaLog tm = new tabelaLog(dao, 4, log);
            table.setModel(tm);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar log  \n" + e.getMessage());
        }
    }

    public static void listarNomeTabela(JTable table, String nomeTabela) {
        try {
            DaoLog dao = new DaoLog();
            Log log = new Log();
            log.setTabela(nomeTabela);
            tabelaLog tm = new tabelaLog(dao, 5, log);
            table.setModel(tm);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar log  \n" + e.getMessage());
        }
    }

}
