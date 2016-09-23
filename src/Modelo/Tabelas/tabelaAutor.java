/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Tabelas;

import DAO.DaoAutor;
import Modelo.Autor;
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
public class tabelaAutor extends AbstractTableModel implements TableModelListener {

    private final List<Autor> autores;
    private final DaoAutor dao;
    private final List<String> colunas;

    public tabelaAutor(DaoAutor daoAutor) throws SQLException {
        this.dao = daoAutor;
        this.autores = daoAutor.listar();
        colunas = Arrays.asList("Id", "Nome");
        this.addTableModelListener(this);
    }

    @Override
    public int getRowCount() {
        return autores.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    public String getColumnName(int i) {
        return colunas.get(i);
    }

    @Override
    public Object getValueAt(int row, int column) {
       Autor autor = autores.get(row);
        switch (column) {
            case 0:
                return autor.getIdAutor();
            case 1:
                return autor.getNome();
        }
        return null;             
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
