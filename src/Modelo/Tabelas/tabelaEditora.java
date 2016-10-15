/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Tabelas;

import Modelo.Editora;
import DAO.DaoEditora;
import java.util.List;
import java.util.Arrays;
import java.sql.SQLException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuário
 */
public class tabelaEditora extends AbstractTableModel implements TableModelListener {

    private final List<Editora> editoras;
    private final DaoEditora dao;
    private final List<String> colunas;

    public tabelaEditora(DaoEditora daoEditora, boolean filtro, Editora e) throws SQLException {
        this.dao = daoEditora;
        colunas = Arrays.asList("Id", "Nome", "CNPJ", "Email");
        this.addTableModelListener(this);
        if (!filtro) {
            this.editoras = daoEditora.listar();
        } else {
            this.editoras = daoEditora.listarPorNome(e);
        }
    }

    @Override
    public int getRowCount() {
        return editoras.size();
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
        Editora editora = editoras.get(row);
        switch (column) {
            case 0:
                return editora.getIdEditora();
            case 1:
                return editora.getNome();
            case 2:
                return editora.getEmail();
            case 3:
                return editora.getCnpj();
        }
        return null;
    }

     @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
