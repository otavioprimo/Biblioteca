/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Tabelas;

import DAO.DaoEmprestimo;
import Modelo.Cliente;
import Modelo.Emprestimo;
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
public class tabelaEmprestimo extends AbstractTableModel implements TableModelListener {

    private final List<Emprestimo> emprestimos;
    private final DaoEmprestimo dao;
    private final List<String> colunas;

    //Filtro 1 = listar todos emprestimos
    //Filtro 2 = listar Emprestimo por nome Cliente
    //Filtro 3 = listar livros atrasados
    public tabelaEmprestimo(DaoEmprestimo daoEmprestimo, int tipoFiltro, Cliente c) throws SQLException {
        this.dao = daoEmprestimo;
        colunas = Arrays.asList("IdEmprestimo", "idLivro", "Titulo", "Cliente", "Funcionário", "Dt_Ret", "Dt_Dev", "Devolvido");
        switch (tipoFiltro) {
            case 1:
                this.emprestimos = daoEmprestimo.listar();
                break;
            case 2:
                this.emprestimos = daoEmprestimo.listarPorCliente(c);
                break;
            case 3:
                this.emprestimos = daoEmprestimo.listarAtrasados();
                break;
            default:
                this.emprestimos = daoEmprestimo.listar();
                break;
        }
    }

    @Override
    public int getRowCount() {
        return emprestimos.size();
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
        Emprestimo emprestimo = emprestimos.get(row);
        switch (column) {
            case 0:
                return emprestimo.getIdEmprestimo();
            case 1:
                return emprestimo.getLivro().getIdLivro();
            case 2:
                return emprestimo.getLivro().getTitulo();
            case 3:
                return emprestimo.getCliente().getNome();
            case 4:
                return emprestimo.getFuncionario().getNome();
            case 5:
                return emprestimo.getDt_retirada();
            case 6:
                return emprestimo.getDt_devolucao();
            case 7:
                return emprestimo.getDevolvido();

        }
        return null;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
