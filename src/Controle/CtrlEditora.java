/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.DaoEditora;
import Modelo.Editora;
import Modelo.Tabelas.tabelaEditora;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class CtrlEditora {

    public static void salvar(String nome, String email, String cnpj) {
        Editora editora = new Editora();

        try {
            editora.setNome(nome);
            editora.setEmail(email);
            editora.setCnpj(cnpj);

            DaoEditora dao = new DaoEditora();

            dao.salvar(editora);
            JOptionPane.showMessageDialog(null, "Editora salva com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar nova Editora\n Verifique se a Editora já existe!\n" + e.getMessage());
        }
    }

    public static void excluir(int id, String nomeEditora) {
        Editora editora = new Editora();

        try {
            editora.setIdEditora(id);
            editora.setNome(nomeEditora);

            DaoEditora dao = new DaoEditora();

            dao.excluir(editora);

            JOptionPane.showMessageDialog(null, "Editora excluida com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir Editora \n" + e.getMessage());
        }
    }
    
      public static void editar(String nomeNovo, String emailNovo,String cnpjNovo, int idAtual) {
       Editora editora = new Editora();

        try {            
            editora.setNome(nomeNovo);
            editora.setEmail(emailNovo);
            editora.setCnpj(cnpjNovo);

            DaoEditora dao = new DaoEditora();
            dao.editar(editora, idAtual);

            JOptionPane.showMessageDialog(null, "Editora editada com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao editar Editora \n Verifique se a Editora já existe!\n" + e.getMessage());
        }
    }
      
       public static void listarEditora(JTable table) {

        try {
            DaoEditora dao = new DaoEditora();
            Editora editora = new Editora();
            tabelaEditora tm = new tabelaEditora(dao, false, editora);
            table.setModel(tm);
        } catch (SQLException e) {
            Logger.getLogger(CtrlAutor.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar autores  \n" + e.getMessage());
        }
    }
       
       public static void listarEditoraNome(JTable table, String nomeEditora) {

        try {
           DaoEditora dao = new DaoEditora();
            Editora editora = new Editora();
            editora.setNome(nomeEditora);
            tabelaEditora tm = new tabelaEditora(dao, true, editora);
            table.setModel(tm);

        } catch (SQLException e) {
            Logger.getLogger(CtrlAutor.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar autores  \n" + e.getMessage());
        }
    }
}
