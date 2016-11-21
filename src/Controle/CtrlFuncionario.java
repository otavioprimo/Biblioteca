/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.DaoFuncionario;
import Modelo.Funcionario;
import Modelo.Tabelas.tabelaFuncionario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class CtrlFuncionario {

    public static void salvar(String nome, String rg, String cpf, String telefone, String email, String login, String senha, char cargo, char ativo) {
        Funcionario funcionario = new Funcionario();
        DaoFuncionario dao = new DaoFuncionario();

        try {
            funcionario.setNome(nome);
            funcionario.setRg(rg);
            funcionario.setCpf(cpf);
            funcionario.setTelefone(telefone);
            funcionario.setEmail(email);
            funcionario.setLogin(login);
            funcionario.setSenha(senha);
            funcionario.setCargo(cargo);
            funcionario.setAtivo(ativo);

            dao.salvar(funcionario);
            JOptionPane.showMessageDialog(null, "Novo Funcionário adicionado \n Um Email foi enviado com a senha de login");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar novo Funcionário \n" + e.getMessage());
        }
    }

    public static void excluir(int idFuncionario, String nomeFuncionario) {
        Funcionario funcionario = new Funcionario();
        DaoFuncionario dao = new DaoFuncionario();

        try {
            funcionario.setIdFuncionario(idFuncionario);
            funcionario.setNome(nomeFuncionario);
            dao.excluir(funcionario);
            JOptionPane.showMessageDialog(null, "Funcionário excluido com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir Funcionário \n" + e.getMessage());
        }
    }

    public static void editar(String nome, String rg, String cpf, String telefone, String email, String login, char cargo, char ativo, String nomeAnterior, int idPar) {
        Funcionario funcionario = new Funcionario();
        DaoFuncionario dao = new DaoFuncionario();

        try {
            funcionario.setNome(nome);
            funcionario.setRg(rg);
            funcionario.setCpf(cpf);
            funcionario.setTelefone(telefone);
            funcionario.setEmail(email);
            funcionario.setLogin(login);
            funcionario.setCargo(cargo);
            funcionario.setAtivo(ativo);

            dao.editar(funcionario, nomeAnterior, idPar);
            JOptionPane.showMessageDialog(null, "Funcionário editado com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao editar Funcionário \n" + e.getMessage());
        }
    }

    public static void listarFuncionarios(JTable table) {
        Funcionario funcionario = new Funcionario();
        DaoFuncionario dao = new DaoFuncionario();

        try {
            tabelaFuncionario tm = new tabelaFuncionario(dao, 1, funcionario);
            table.setModel(tm);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }

    public static void listarFuncionariosRG(JTable table, String rg) {
        Funcionario funcionario = new Funcionario();
        DaoFuncionario dao = new DaoFuncionario();

        try {
            funcionario.setRg(rg);
            tabelaFuncionario tm = new tabelaFuncionario(dao, 3, funcionario);
            table.setModel(tm);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }

    public static void listarFuncionariosNome(JTable table, String nome) {
        Funcionario funcionario = new Funcionario();
        DaoFuncionario dao = new DaoFuncionario();

        try {
            funcionario.setNome(nome);
            tabelaFuncionario tm = new tabelaFuncionario(dao, 2, funcionario);
            table.setModel(tm);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }

    public static String buscarSenha(String usuario) {
        Funcionario funcionario = new Funcionario();
        DaoFuncionario dao = new DaoFuncionario();
        String senha = null;

        try {
            funcionario.setLogin(usuario);
            senha = dao.buscarSenha(funcionario);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao buscar Usuario/Senha  \n" + e.getMessage());
        }

        return senha;
    }
    
     public static int buscarId(String usuario) {
        Funcionario funcionario = new Funcionario();
        DaoFuncionario dao = new DaoFuncionario();
        int id = 0;

        try {
            funcionario.setLogin(usuario);
            id = dao.retornaId(funcionario);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao buscar id  \n" + e.getMessage());
        }

        return id;
    }

    public static boolean editarSenhaComEmail(String email, String senha) {
        
        Funcionario funcionario = new Funcionario();
        DaoFuncionario dao = new DaoFuncionario();

        try {
            funcionario.setEmail(email);
            funcionario.setSenha(senha);
            dao.editarSenhaComEmail(funcionario);
            return true;
            //System.out.println("senha editada com sucesso " + senha);
        } catch (Exception e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao tentar mudar a senha \nPor Favor tente novamente mais tarde \n" + e.getMessage());
            return false;
        }
    }
}
