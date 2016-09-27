/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Tabelas;

import DAO.DaoLog;
import Modelo.Log;
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
public class tabelaLog extends AbstractTableModel implements TableModelListener {

    private final List<Log> logs;
    private final DaoLog dao;
    private final List<String> colunas;

    //Filtro = 1 'Lista todo o log'
    //Filtro = 2 'Lista determinada data'
    //Filtro = 3 'Lista todo o log antes de determinada data'
    //Filtro = 4 'Lista todo o log depois de determinada data'
    //Filtro = 5 'Lista log por nome da tabela'
    public tabelaLog(DaoLog daoLog, int tipoFiltro, Log l) throws SQLException {
        this.dao = daoLog;
        colunas = Arrays.asList("Id", "Tabela", "Motivo", "Conteudo", "Mudanca", "Data do Log");
        this.addTableModelListener(this);
        switch (tipoFiltro) {
            case 1:
                this.logs = daoLog.listar();
                break;
            case 2:
                this.logs = daoLog.listarDataEspecifica(l);
                break;
            case 3:
                this.logs = daoLog.listarDataMaior(l);
                break;
            case 4:
                this.logs = daoLog.listarDataMenor(l);
                break;
            case 5:
                this.logs = daoLog.listarNomeTabela(l);
                break;
            default:
                this.logs = daoLog.listar();
                break;
        }
    }

    @Override
    public int getRowCount() {
        return logs.size();
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
        Log log = logs.get(row);
        switch (column) {
            case 0:
                return log.getIdLog();
            case 1:
                return log.getTabela();
            case 2:
                return log.getMotivo();
            case 3:
                return log.getConteudo();
            case 4:
                return log.getMudanca();
            case 5:
                return log.getDt_log();
        }
        return null;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
