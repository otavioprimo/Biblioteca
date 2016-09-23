/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Tabelas;

import DAO.DaoGenero;
import Modelo.Genero;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuário
 */
public class tabelaGenero extends AbstractTableModel implements TableModelListener {

    private final List<Genero> generos;
    private final DaoGenero dao;
    private final List<String> colunas;

    public tabelaGenero(DaoGenero daoGenero) throws SQLException {
        this.dao = daoGenero;
        this.generos = daoGenero.listar();
        colunas = Arrays.asList("Id", "Tipo");
        this.addTableModelListener(this);
    }

    @Override
    public int getRowCount() {
        return generos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    public String getColumnName(int i) {
        return colunas.get(i);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Genero genero = generos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return genero.getIdGenero();
            case 1:
                return genero.getTipo();
        }
        return null;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
