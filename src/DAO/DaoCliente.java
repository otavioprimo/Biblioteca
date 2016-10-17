/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Cidade;
import Modelo.Cliente;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aluno6814
 */
public class DaoCliente {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Cliente cliente) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_CLI(UPPER(?),UPPER(?),UPPER(?),?,UPPER(?),UPPER(?),UPPER(?),?,UPPER(?),UPPER(?)); END;");
        cs.setString(1, cliente.getNome());
        cs.setString(2, cliente.getRg());
        cs.setString(3, cliente.getCpf());
        cs.setString(4, cliente.getTelefone());
        cs.setString(5, cliente.getEndereco());
        cs.setString(6, cliente.getBairro());
        cs.setString(7, cliente.getNumero());
        cs.setInt(8, cliente.getCidade().getIdCidade());
        cs.setString(9, cliente.getEmail());
        cs.setString(10, String.valueOf(cliente.getAtivo()));
        cs.execute();
        connOracle.desconectar();
    }

    public void excluir(Cliente cliente) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;
        cs = connOracle.conn.prepareCall("BEGIN DLT_CLI(?,UPPER(?)); END;");
        cs.setInt(1, cliente.getIdCliente());
        cs.setString(2, cliente.getNome());
        cs.execute();
        connOracle.desconectar();
    }

    public void editar(Cliente cliente, String nomeAnterior, int idPar) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;
        cs = connOracle.conn.prepareCall("BEGIN UPDT_CLI(UPPER(?),UPPER(?),?,?,UPPER(?),UPPER(?),UPPER(?),?,UPPER(?),UPPER(?),?,?); END;");
        cs.setString(1, cliente.getNome());
        cs.setString(2, cliente.getRg());
        cs.setString(3, cliente.getCpf());
        cs.setString(4, cliente.getTelefone());
        cs.setString(5, cliente.getEndereco());
        cs.setString(6, cliente.getBairro());
        cs.setString(7, cliente.getNumero());
        cs.setInt(8, cliente.getCidade().getIdCidade());
        cs.setString(9, cliente.getEmail());
        cs.setString(10, String.valueOf(cliente.getAtivo()));
        cs.setInt(11, idPar);
        cs.setString(12, nomeAnterior);
        cs.execute();
        connOracle.desconectar();
    }

    public ArrayList<Cliente> listar() throws SQLException {

        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT C.IDCliente, C.Nome, C.Rg, C.Cpf, C.Tel, C.Endereco, C.Bairro, CID.Nome_Cidade, cid.IDCidade, C.Numero, C.Email, C.Ativo ");
        sql.append("FROM CLIENTE C ");
        sql.append("INNER JOIN Cidade CID ON C.IDCidade = CID.IDCidade ");
        sql.append("ORDER BY IDCliente ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        while (resultado.next()) {
            Cidade cidade = new Cidade();
            cidade.setIdCidade(resultado.getInt("IDCidade"));
            cidade.setNome(resultado.getString("Nome_cidade"));

            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultado.getInt("IDCliente"));
            cliente.setNome(resultado.getString("Nome"));
            cliente.setRg(resultado.getString("Rg"));
            cliente.setCpf(resultado.getString("Cpf"));
            cliente.setTelefone(resultado.getString("Tel"));
            cliente.setEndereco(resultado.getString("Endereco"));
            cliente.setBairro(resultado.getString("Bairro"));
            cliente.setNumero(resultado.getString("Numero"));
            cliente.setCidade(cidade);
            cliente.setEmail(resultado.getString("Email"));
            cliente.setAtivo(resultado.getString("Ativo").charAt(0));

            lista.add(cliente);

        }
        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Cliente> listarPorNome(Cliente c) throws SQLException {

        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT C.IDCliente, C.Nome, C.Rg, C.Cpf, C.Tel, C.Endereco, C.Bairro, CID.Nome_Cidade, cid.IDCidade, C.Numero, C.Email, C.Ativo ");
        sql.append("FROM CLIENTE C ");
        sql.append("INNER JOIN Cidade CID ON C.IDCidade = CID.IDCIDADE ");
        sql.append("WHERE C.Nome LIKE UPPER(?) ");
        sql.append("ORDER BY Nome ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + c.getNome() + "%");
        ResultSet resultado = pst.executeQuery();

        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        while (resultado.next()) {

            Cidade cidade = new Cidade();
            cidade.setIdCidade(resultado.getInt("IDCidade"));
            cidade.setNome(resultado.getString("Nome_cidade"));

            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultado.getInt("IDCliente"));
            cliente.setNome(resultado.getString("Nome"));
            cliente.setRg(resultado.getString("Rg"));
            cliente.setCpf(resultado.getString("Cpf"));
            cliente.setTelefone(resultado.getString("Tel"));
            cliente.setEndereco(resultado.getString("Endereco"));
            cliente.setBairro(resultado.getString("Bairro"));
            cliente.setNumero(resultado.getString("Numero"));
            cliente.setCidade(cidade);
            cliente.setEmail(resultado.getString("Email"));
            cliente.setAtivo(resultado.getString("Ativo").charAt(0));

            lista.add(cliente);
        }
        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Cliente> listarPorEmail(Cliente c) throws SQLException {

        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT C.IDCliente, C.Nome, C.Rg, C.Cpf, C.Tel, C.Endereco, C.Bairro, CID.Nome_Cidade, cid.IDCidade, C.Numero, C.Email, C.Ativo ");
        sql.append("FROM CLIENTE C ");
        sql.append("INNER JOIN Cidade CID ON C.IDCidade = CID.IDCIDADE ");
        sql.append("WHERE C.Email LIKE UPPER(?) ");
        sql.append("ORDER BY Nome ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + c.getEmail() + "%");
        ResultSet resultado = pst.executeQuery();

        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        while (resultado.next()) {

            Cidade cidade = new Cidade();
            cidade.setIdCidade(resultado.getInt("IDCidade"));
            cidade.setNome(resultado.getString("Nome_cidade"));

            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultado.getInt("IDCliente"));
            cliente.setNome(resultado.getString("Nome"));
            cliente.setRg(resultado.getString("Rg"));
            cliente.setCpf(resultado.getString("Cpf"));
            cliente.setTelefone(resultado.getString("Tel"));
            cliente.setEndereco(resultado.getString("Endereco"));
            cliente.setBairro(resultado.getString("Bairro"));
            cliente.setNumero(resultado.getString("Numero"));
            cliente.setCidade(cidade);
            cliente.setEmail(resultado.getString("Email"));
            cliente.setAtivo(resultado.getString("Ativo").charAt(0));

            lista.add(cliente);
        }
        connOracle.desconectar();
        return lista;
    }

    public ArrayList<Cliente> listarPorAtivo(Cliente c) throws SQLException {

        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT C.IDCliente, C.Nome, C.Rg, C.Cpf, C.Tel, C.Endereco, C.Bairro, CID.Nome_Cidade, cid.IDCidade, C.Numero, C.Email, C.Ativo ");
        sql.append("FROM CLIENTE C ");
        sql.append("INNER JOIN Cidade CID ON C.IDCidade = CID.IDCIDADE ");
        sql.append("WHERE C.Ativo LIKE UPPER(?) ");
        sql.append("ORDER BY IDCliente ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + c.getAtivo() + "%");
        ResultSet resultado = pst.executeQuery();

        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        while (resultado.next()) {

            Cidade cidade = new Cidade();
            cidade.setIdCidade(resultado.getInt("IDCidade"));
            cidade.setNome(resultado.getString("Nome_cidade"));

            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultado.getInt("IDCliente"));
            cliente.setNome(resultado.getString("Nome"));
            cliente.setRg(resultado.getString("Rg"));
            cliente.setCpf(resultado.getString("Cpf"));
            cliente.setTelefone(resultado.getString("Tel"));
            cliente.setEndereco(resultado.getString("Endereco"));
            cliente.setBairro(resultado.getString("Bairro"));
            cliente.setNumero(resultado.getString("Numero"));
            cliente.setCidade(cidade);
            cliente.setEmail(resultado.getString("Email"));
            cliente.setAtivo(resultado.getString("Ativo").charAt(0));

            lista.add(cliente);
        }
        connOracle.desconectar();
        return lista;
    }
    
    public ArrayList<Cliente> listarPorRG(Cliente c) throws SQLException {

        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT C.IDCliente, C.Nome, C.Rg, C.Cpf, C.Tel, C.Endereco, C.Bairro, CID.Nome_Cidade, cid.IDCidade, C.Numero, C.Email, C.Ativo ");
        sql.append("FROM CLIENTE C ");
        sql.append("INNER JOIN Cidade CID ON C.IDCidade = CID.IDCIDADE ");
        sql.append("WHERE C.Rg LIKE UPPER(?) ");
        sql.append("ORDER BY IDCliente ASC ");

        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        pst.setString(1, "%" + c.getRg() + "%");
        ResultSet resultado = pst.executeQuery();

        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        while (resultado.next()) {

            Cidade cidade = new Cidade();
            cidade.setIdCidade(resultado.getInt("IDCidade"));
            cidade.setNome(resultado.getString("Nome_cidade"));

            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultado.getInt("IDCliente"));
            cliente.setNome(resultado.getString("Nome"));
            cliente.setRg(resultado.getString("Rg"));
            cliente.setCpf(resultado.getString("Cpf"));
            cliente.setTelefone(resultado.getString("Tel"));
            cliente.setEndereco(resultado.getString("Endereco"));
            cliente.setBairro(resultado.getString("Bairro"));
            cliente.setNumero(resultado.getString("Numero"));
            cliente.setCidade(cidade);
            cliente.setEmail(resultado.getString("Email"));
            cliente.setAtivo(resultado.getString("Ativo").charAt(0));

            lista.add(cliente);
        }
        connOracle.desconectar();
        return lista;
    }
}
