/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.Cliente;
import Modelo.Livro;
import Modelo.Funcionario;
import DAO.DaoEmprestimo;
import Modelo.Emprestimo;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.SQLException;
import Modelo.Tabelas.tabelaEmprestimo;
import Visao2.FuncionarioTeste;

/**
 *
 * @author Usuário
 */
public class CtrlEmprestimo {

    public static void salvar(int idLivro, int idCliente, int idFunc, String dt_ret, String dt_dev, char devolvido) {
        Funcionario func = new Funcionario();
        Livro livro = new Livro();
        Cliente cliente = new Cliente();
        Emprestimo emprestimo = new Emprestimo();

        try {
            func.setIdFuncionario(idFunc);
            livro.setIdLivro(idLivro);
            cliente.setIdCliente(idCliente);

            emprestimo.setCliente(cliente);
            emprestimo.setFuncionario(func);
            emprestimo.setLivro(livro);
            emprestimo.setDt_devolucao(dt_dev);
            emprestimo.setDt_retirada(dt_ret);
            emprestimo.setDevolvido(devolvido);

            DaoEmprestimo dao = new DaoEmprestimo();
            dao.salvar(emprestimo);
            JOptionPane.showMessageDialog(null, "Emprestimo de Livro Feito!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar o Empréstimo \n" + e.getMessage());
        }
    }

    public static void editar(int idPar, char devolvido, String dt_dev) {
        Emprestimo emprestimo = new Emprestimo();

        try {
            emprestimo.setDt_devolucao(dt_dev);
            emprestimo.setDevolvido(devolvido);

            DaoEmprestimo dao = new DaoEmprestimo();
            dao.editar(emprestimo, idPar);
            JOptionPane.showMessageDialog(null, "Emprestimo Atualizado!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o Empréstimo \n" + e.getMessage());
        }
    }

    public static void listar(JTable table) {
        Cliente c = new Cliente();
        DaoEmprestimo dao = new DaoEmprestimo();

        try {
            tabelaEmprestimo tm = new tabelaEmprestimo(dao, 1, c);
            table.setModel(tm);    
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }
    
        
    public static void listarClienteNome(JTable table, String nome) {
        Cliente c = new Cliente();
        DaoEmprestimo dao = new DaoEmprestimo();
        c.setNome(nome);

        try {
            tabelaEmprestimo tm = new tabelaEmprestimo(dao, 2, c);
            table.setModel(tm);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }
    
     public static void listarAtrasados(JTable table) {
        Cliente c = new Cliente();
        DaoEmprestimo dao = new DaoEmprestimo();

        try {
            tabelaEmprestimo tm = new tabelaEmprestimo(dao, 3, c);
            table.setModel(tm);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }
}
