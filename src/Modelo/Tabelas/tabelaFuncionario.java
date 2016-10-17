/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Tabelas;

import DAO.DaoFuncionario;
import Modelo.Funcionario;
import java.sql.SQLException;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Arrays;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author Usuário
 */
public class tabelaFuncionario extends AbstractTableModel implements TableModelListener {

    private final List<Funcionario> funcionarios;
    private final DaoFuncionario dao;
    private final List<String> colunas;

    //Filtro 1 = listar todos funcionários
    //Filtro 2 = listar Funcionarios por nome
    //Filtro 3 = listar funcionários por RG
    public tabelaFuncionario(DaoFuncionario daoFuncionario, int tipoFiltro, Funcionario f) throws SQLException {
        this.dao = daoFuncionario;
        colunas = Arrays.asList("Id", "Nome", "Email", "RG", "CPF", "Telefone", "Login", "Cargo", "Ativo");
        switch (tipoFiltro) {
            case 1:
                this.funcionarios = daoFuncionario.listar();
                break;
            case 2:
                this.funcionarios = daoFuncionario.listarPorNome(f);
                break;
            case 3:
                this.funcionarios = daoFuncionario.listarPorRG(f);
                break;
            default:
                this.funcionarios = daoFuncionario.listar();
                break;
        }
    }

    @Override
    public int getRowCount() {
        return funcionarios.size();
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
        Funcionario funcionario = funcionarios.get(row);
        switch (column) {
            case 0:
                return funcionario.getIdFuncionario();
            case 1:
                return funcionario.getNome();
            case 2:
                return funcionario.getEmail();
            case 3:
                return funcionario.getRg();
            case 4:
                return funcionario.getCpf();
            case 5:
                return funcionario.getTelefone();
            case 6:
                return funcionario.getLogin();
            case 7:
                return funcionario.getCargo();
            case 8:
                return funcionario.getAtivo();
            

        }
        return null;
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
