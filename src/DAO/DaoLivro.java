/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Modelo.Livro;
import Modelo.Autor;
import Modelo.Editora;
import Modelo.Genero;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author aluno6814
 */
public class DaoLivro {

    ConnectionFactory connOracle = new ConnectionFactory();

    public void salvar(Livro livro) throws SQLException {
        connOracle.conectar();

        CallableStatement cs;

        cs = connOracle.conn.prepareCall("BEGIN ADD_LIVRO(UPPER(?),?,?,TO_TIMESTAMP(?,'DD/MM/YYYY'),UPPER(?),?,?,UPPER(?),?,UPPER(?)); END;");
        cs.setString(1, livro.getTitulo());
        cs.setInt(2, livro.getAutor().getIdAutor());
        cs.setInt(3, livro.getNum_pag());
        cs.setString(4, livro.getDt_lanc());
        cs.setString(5, livro.getEdicao());
        cs.setString(6, livro.getIsbn());
        cs.setInt(7, livro.getEditora().getIdEditora());
        cs.setString(8, livro.getPais());
        cs.setInt(9, livro.getGenero().getIdGenero());
        cs.setString(10, livro.getEstante());
        cs.execute();
        connOracle.desconectar();
    }

    public void excluir(Livro livro) throws SQLException {
        connOracle.conectar();
        CallableStatement cs;
        
        cs = connOracle.conn.prepareCall("BEGIN DLT_LIVRO(?,UPPER(?)); END;");
        cs.setInt(1, livro.getIdLivro());
        cs.setString(2, livro.getTitulo());
        cs.execute();
        connOracle.desconectar();
    }

    public void editar(Livro livro, String nomeAnterior, int idPar) throws SQLException {
        connOracle.conectar();
        CallableStatement cs;
        
        cs = connOracle.conn.prepareCall("BEGIN UPDT(UPPER(?),?,?,TO_TIMESTAMP(?,'DD/MM/YYYY'),UPPER(?),?,?,UPPER(?),?,UPPER(?),?,?); END;");
        cs.setString(1, livro.getTitulo());
        cs.setInt(2, livro.getAutor().getIdAutor());
        cs.setInt(3, livro.getNum_pag());
        cs.setString(4, livro.getDt_lanc());
        cs.setString(5, livro.getEdicao());
        cs.setString(6, livro.getIsbn());
        cs.setInt(7, livro.getEditora().getIdEditora());
        cs.setString(8, livro.getPais());
        cs.setInt(9, livro.getGenero().getIdGenero());
        cs.setString(10, livro.getEstante());
        cs.setString(11,nomeAnterior);
        cs.setInt(12,idPar);                //Verificar se nome anterior é num 11 ou 12 se nao existir da alter na proc e botar nomeAnterior
        cs.execute();
        connOracle.desconectar();
    }
    
    public ArrayList<Livro> listar() throws SQLException{
        connOracle.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ..."); //Alterar na tabela livro para Nome_Autor, Nome_Editora
        
        PreparedStatement pst = connOracle.conn.prepareStatement(sql.toString());
        ResultSet resultado = pst.executeQuery();

        ArrayList<Livro> lista = new ArrayList<Cliente>();
        while (resultado.next()) {
            Autor autor = new Autor();
            autor.setIdAutor(resultado.getInt("IDAutor"));
            autor.setNome(resultado.getString("Nome_Autor"));
            
            Genero genero = new Genero();
            genero.setIdGenero(resultado.getInt("IDGenero"));
            genero.setNome(resultado.getString("Tipo"));
            
            Editora editora = new Editora();
            editora.setIdEditora(resultad.getInt("IDEditora"));
            editora.setTipo(resultado.getString("Nome_Editora"));
            
            Livro livro = new Livro();
            livro.setIdLivro(resultado.getInt("IDLivro"));
            livro.setTitulo(resultado.getString("Titulo"));
            livro.setAutor(autor);
            livro.setNum_pag(resultado.getInt("Num_Pag"));
            livro.setDt_lanc(resultado.getString("Dt_Lanc"));
            livro.setEdicao(resultado.getString("Edicao"));
            livro.setIsbn(resultado.getString("ISBN"));
            livro.setEditora(editora);
            livro.setPais(resultado.getString("Pais"));
            livro.setGenero(genero);
            livro.setEstante(resultado.getString("Estante"));
            
            lista.add(Livro);
        }
        
        connOracle.desconectar();
        return lista;
    }
}
