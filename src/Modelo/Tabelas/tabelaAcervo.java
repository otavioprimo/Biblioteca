/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Tabelas;

import DAO.DaoAcervo;
import Modelo.Acervo;

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
public class tabelaAcervo extends AbstractTableModel implements TableModelListener {

    private final List<Acervo> acervos;
    private final DaoAcervo dao;
    private final List<String> colunas;

    public tabelaAcervo(DaoAcervo daoAcervo, Acervo a, int tipoFiltro) throws SQLException {
        this.dao = daoAcervo;
        colunas = Arrays.asList("IdItem", "Titulo", "Quantidade", "Data de Entrada","IdLivro");
        this.addTableModelListener(this);
        switch (tipoFiltro) {
            case 1:
                this.acervos = daoAcervo.listar();
                break;
            case 2:
                this.acervos = daoAcervo.listarPeloTitulo(a);
                break;
            default:
                this.acervos = daoAcervo.listar();
                break;
        }
    }

    @Override
    public int getRowCount() {
        return acervos.size();
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
        Acervo acervo = acervos.get(row);
        switch (column) {
            case 0:
                return acervo.getIdItem();
            case 1:
                return acervo.getLivro().getTitulo();
            case 2:
                return acervo.getQuantidade();
            case 3:
                return acervo.getDt_entrada();
            case 4:
                return acervo.getLivro().getIdLivro();
        }
        return null;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
