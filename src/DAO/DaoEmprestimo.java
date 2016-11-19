/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Cliente;
import Modelo.Emprestimo;
import Modelo.Funcionario;
import Modelo.Livro;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import java.util.ArrayList;

/**
 *
 * @author aluno6814
 */
public class DaoEmprestimo {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Emprestimo emprestimo) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_EMP(?,?,?,TO_TIMESTAMP(?,'DD/MM/YYYY'),TO_TIMESTAMP(?,'DD/MM/YYYY'),?); END;");
        cs.setInt(1, emprestimo.getLivro().getIdLivro());
        cs.setInt(2, emprestimo.getCliente().getIdCliente());
        cs.setInt(3, emprestimo.getFuncionario().getIdFuncionario());
        cs.setString(4, emprestimo.getDt_retirada());
        cs.setString(5, emprestimo.getDt_devolucao());
        cs.setString(6, String.valueOf(emprestimo.getDevolvido()));
        cs.execute();
        connOracle.desconectar();
    }

    public void editar(Emprestimo emprestimo, int idPar) throws SQLException {
        connOracle.conectar();
        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN UPDT_EMP(?,?,TO_TIMESTAMP(?,'DD/MM/YYYY')); END;");
        cs.setInt(1, idPar);
        cs.setString(2, String.valueOf(emprestimo.getDevolvido()));
        cs.setString(3, emprestimo.getDt_devolucao());
        cs.execute();
        connOracle.desconectar();
    }
    

    public ArrayList<Emprestimo> listar() throws SQLException {

        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e.idemprestimo, l.idlivro, l.titulo, c.idcliente, c.nome, f.idfunc,f.nome, TO_CHAR(e.dt_ret,'dd/MM/YYYY') as dt_ret, TO_CHAR(e.dt_dev,'dd/MM/YYYY') as dt_dev,e.devolvido ");
        sql.append("FROM emprestimo e ");
        sql.append("JOIN livro l ON l.idlivro = e.idlivro ");
        sql.append("JOIN cliente c on c.idcliente = e.idcliente ");
        sql.append("JOIN funcionario f on f.idfunc = e.idfunc ORDER BY e.idemprestimo DESC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>();

        while (resultado.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultado.getInt("idcliente"));
            cliente.setNome(resultado.getString(5));

            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("idlivro"));
            livro.setTitulo(resultado.getString("titulo"));

            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(resultado.getInt("idfunc"));
            funcionario.setNome(resultado.getString(7));

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setIdEmprestimo(resultado.getInt("idemprestimo"));
            emprestimo.setDevolvido(resultado.getString("devolvido").charAt(0));
            emprestimo.setDt_devolucao(resultado.getString("dt_dev"));
            emprestimo.setDt_retirada(resultado.getString("dt_ret"));
            emprestimo.setCliente(cliente);
            emprestimo.setFuncionario(funcionario);
            emprestimo.setLivro(livro);
            lista.add(emprestimo);

        }
        connOracle.desconectar();
        return lista;

    }

    public ArrayList<Emprestimo> listarPorCliente(Cliente c) throws SQLException {

        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e.idemprestimo, l.idlivro, l.titulo, c.idcliente, c.nome, f.idfunc,f.nome, TO_CHAR(e.dt_ret,'dd/MM/YYYY') as dt_ret, TO_CHAR(e.dt_dev,'dd/MM/YYYY') as dt_dev,e.devolvido ");
        sql.append("FROM emprestimo e ");
        sql.append("JOIN livro l ON l.idlivro = e.idlivro ");
        sql.append("JOIN cliente c on c.idcliente = e.idcliente ");
        sql.append("JOIN funcionario f on f.idfunc = e.idfunc ");
        sql.append("WHERE c.nome LIKE UPPER(?) ORDER BY e.idemprestimo ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + c.getNome() + "%");
        ResultSet resultado = pst.executeQuery();

        ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>();

        while (resultado.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultado.getInt("idcliente"));
            cliente.setNome(resultado.getString(5));

            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("idlivro"));
            livro.setTitulo(resultado.getString("titulo"));

            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(resultado.getInt("idfunc"));
            funcionario.setNome(resultado.getString(7));

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setIdEmprestimo(resultado.getInt("idemprestimo"));
            emprestimo.setDevolvido(resultado.getString("devolvido").charAt(0));
            emprestimo.setDt_devolucao(resultado.getString("dt_dev"));
            emprestimo.setDt_retirada(resultado.getString("dt_ret"));
            emprestimo.setCliente(cliente);
            emprestimo.setFuncionario(funcionario);
            emprestimo.setLivro(livro);
            lista.add(emprestimo);

        }
        connOracle.desconectar();

        return lista;
    }

    public ArrayList<Emprestimo> listarAtrasados() throws SQLException {

        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e.idemprestimo, l.idlivro, l.titulo, c.idcliente, c.nome, f.idfunc,f.nome, TO_CHAR(e.dt_ret,'dd/MM/YYYY') as dt_ret, TO_CHAR(e.dt_dev,'dd/MM/YYYY') as dt_dev,e.devolvido ");
        sql.append("FROM emprestimo e ");
        sql.append("JOIN livro l ON l.idlivro = e.idlivro ");
        sql.append("JOIN cliente c on c.idcliente = e.idcliente ");
        sql.append("JOIN funcionario f on f.idfunc = e.idfunc ");
        sql.append("WHERE e.dt_dev < to_char(sysdate,'dd/MM/yyyy') AND e.devolvido LIKE 'N' ORDER BY e.dt_dev asc ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>();

        while (resultado.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultado.getInt("idcliente"));
            cliente.setNome(resultado.getString(5));

            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("idlivro"));
            livro.setTitulo(resultado.getString("titulo"));

            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(resultado.getInt("idfunc"));
            funcionario.setNome(resultado.getString(7));

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setIdEmprestimo(resultado.getInt("idemprestimo"));
            emprestimo.setDevolvido(resultado.getString("devolvido").charAt(0));
            emprestimo.setDt_devolucao(resultado.getString("dt_dev"));
            emprestimo.setDt_retirada(resultado.getString("dt_ret"));
            emprestimo.setCliente(cliente);
            emprestimo.setFuncionario(funcionario);
            emprestimo.setLivro(livro);
            lista.add(emprestimo);

        }
        connOracle.desconectar();

        return lista;
    }

}
