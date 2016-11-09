/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Tabelas;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.sql.SQLException;
import java.util.Arrays;
import Modelo.Livro;
import DAO.DaoLivro;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author Usuário
 */
public class tabelaLivro extends AbstractTableModel implements TableModelListener {

    private final List<Livro> livros;
    private final DaoLivro dao;
    private final List<String> colunas;

    //Filtro 1 = lista todos os livros
    //Filtro 2 = lista livros por titulo
    //Filtro 3 = lista livros por genero
    //Filtro 4 = lista livros por autor
    public tabelaLivro(DaoLivro daoLivro, int tipoFiltro, Livro l) throws SQLException {
        this.dao = daoLivro;
        colunas = Arrays.asList("Id","Titulo","Autor","Editora","Genero","Num_Paginas","Dt_Lançamento","Edição","ISBN","Pais","Estante");
        this.addTableModelListener(this);
        switch (tipoFiltro) {
            case 1:
                this.livros = daoLivro.listar();
                break;
            case 2:
                this.livros = daoLivro.listarPorTitulo(l);
                break;
            case 3:
                this.livros = daoLivro.listarPorGenero(l);
                break;
            case 4:
                this.livros = daoLivro.listarPorAutor(l);
                break;
            default:
                this.livros = daoLivro.listar();
                break;
        }

    }

   @Override
    public int getRowCount() {
        return livros.size();
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
        Livro livro = livros.get(row);
        switch (column) {
            case 0:
                return livro.getIdLivro();
            case 1:
                return livro.getTitulo();
            case 2:
                return livro.getAutor().getNome();
            case 3:
                return livro.getEditora().getNome();
            case 4:
                return livro.getGenero().getTipo();
            case 5:
                return livro.getNum_pag();
            case 6:
                return livro.getDt_lanc();
            case 7:
                return livro.getEdicao();
            case 8:
                return livro.getIsbn();
            case 9:
                return livro.getPais();
            case 10:
                return livro.getEstante();

        }
        return null;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
