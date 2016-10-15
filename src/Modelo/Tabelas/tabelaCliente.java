/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Tabelas;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import DAO.DaoCliente;
import Modelo.Cliente;
import java.util.List;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author Usuário
 */
public class tabelaCliente extends AbstractTableModel implements TableModelListener {

    private final List<Cliente> clientes;
    private final DaoCliente dao;
    private final List<String> colunas;

    //Filtro 1 = listar todos os clientes
    //Filtro 2 = listar clientes pelo nome
    //Filtro 3 = listar clientes pelo email
    //Filtro 4 = listar clientes por Atividade
    public tabelaCliente(DaoCliente daoCliente, int tipoFiltro, Cliente c) throws SQLException {
        this.dao = daoCliente;
        colunas = Arrays.asList("Id", "Nome", "Email", "Rg", "Cpf", "Telefone", "Endereço", "Bairro", "Número", "Cidade", "Ativo");
        this.addTableModelListener(this);
        switch (tipoFiltro) {
            case 1:
                this.clientes = daoCliente.listar();
                break;
            case 2:
                this.clientes = daoCliente.listarPorNome(c);
                break;
            case 3:
                this.clientes = daoCliente.listarPorEmail(c);
                break;
            case 4:
                this.clientes = daoCliente.listarPorAtivo(c);
                break;
            default:
                this.clientes = daoCliente.listar();
                break;
        }
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    @Override
    public String getColumnName(int i) {
        return colunas.get(i);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Cliente cliente = clientes.get(row);
        switch (column) {
            case 0:
                return cliente.getIdCliente();
            case 1:
                return cliente.getNome();
            case 2:
                return cliente.getEmail();
            case 3:
                return cliente.getRg();
            case 4:
                return cliente.getCpf();
            case 5:
                return cliente.getTelefone();
            case 6:
                return cliente.getEndereco();
            case 7:
                return cliente.getBairro();
            case 8:
                return cliente.getNumero();
            case 9:
                return cliente.getCidade().getNome();
            case 10:
                return cliente.getAtivo();
            
        }
        return null;
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
