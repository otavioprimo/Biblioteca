/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.DaoCliente;
import Modelo.Cidade;
import Modelo.Cliente;
import Modelo.Tabelas.tabelaCliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class CtrlCliente {

    public static void salvar(String nome, String rg, String cpf, String telefone, String endereco, String numero, int idCidade, String email, char ativo, String bairro) {
        Cliente cliente = new Cliente();
        Cidade cidade = new Cidade();

        try {
            cidade.setIdCidade(idCidade);

            cliente.setNome(nome);
            cliente.setRg(rg);
            cliente.setCpf(cpf);
            cliente.setTelefone(telefone);
            cliente.setEndereco(endereco);
            cliente.setNumero(numero);
            cliente.setCidade(cidade);
            cliente.setEmail(email);
            cliente.setBairro(bairro);
            cliente.setAtivo(ativo);

            DaoCliente dao = new DaoCliente();
            dao.salvar(cliente);
            JOptionPane.showMessageDialog(null, "Novo cliente adicionado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar novo cliente \n" + e.getMessage());
        }
    }

    public static void excluir(int idCliente, String nomeCliente) {
        Cliente cliente = new Cliente();

        try {
            cliente.setIdCliente(idCliente);
            cliente.setNome(nomeCliente);

            DaoCliente dao = new DaoCliente();
            dao.excluir(cliente);
            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente \n" + e.getMessage());
        }
    }

    public static void editar(String nome, String rg, String cpf, String telefone, String endereco, String numero, int idCidade, String email,char ativo, String bairro, String nomeAnterior, int idPar) {
        Cliente cliente = new Cliente();
        Cidade cidade = new Cidade();
        try {
            cidade.setIdCidade(idCidade);

            cliente.setNome(nome);
            cliente.setRg(rg);
            cliente.setCpf(cpf);
            cliente.setTelefone(telefone);
            cliente.setEndereco(endereco);
            cliente.setNumero(numero);
            cliente.setCidade(cidade);
            cliente.setEmail(email);
            cliente.setBairro(bairro);
            cliente.setAtivo(ativo);

            DaoCliente dao = new DaoCliente();
            dao.editar(cliente, nomeAnterior, idPar);
            JOptionPane.showMessageDialog(null, "Cliente editado com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao editar cliente \n" + e.getMessage());
        }
    }

    public static void listarClientes(JTable table) {
        Cliente cliente = new Cliente();
        DaoCliente dao = new DaoCliente();

        try {
            tabelaCliente tm = new tabelaCliente(dao, 1, cliente);
            table.setModel(tm);
        } catch (SQLException e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }

    public static void listarClienteNome(JTable table, String nome) {
        Cliente cliente = new Cliente();
        DaoCliente dao = new DaoCliente();

        try {
            cliente.setNome(nome);
            tabelaCliente tm = new tabelaCliente(dao, 2, cliente);
            table.setModel(tm);
        } catch (SQLException e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }

    public static void listarClienteEmail(JTable table, String email) {
        Cliente cliente = new Cliente();
        DaoCliente dao = new DaoCliente();

        try {
            cliente.setEmail(email);
            tabelaCliente tm = new tabelaCliente(dao, 3, cliente);
            table.setModel(tm);
        } catch (SQLException e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }

    public static void listarClienteAtivos(JTable table, char ativo) {
        Cliente cliente = new Cliente();
        DaoCliente dao = new DaoCliente();

        try {
            cliente.setAtivo(ativo);
            tabelaCliente tm = new tabelaCliente(dao, 4, cliente);
            table.setModel(tm); 
        } catch (SQLException e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }
    
    public static void listarClienteRG(JTable table, String rg){
       Cliente cliente = new Cliente();
       DaoCliente dao = new DaoCliente();

        try {
            cliente.setRg(rg);
            tabelaCliente tm = new tabelaCliente(dao, 5, cliente);
            table.setModel(tm); 
        } catch (SQLException e) {
            Logger.getLogger(CtrlLog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes  \n" + e.getMessage());
        }
    }

}
