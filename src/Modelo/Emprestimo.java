/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author aluno6814
 */
public class Emprestimo {
    private int idEmprestimo;
    private Livro livro = new Livro();
    private Cliente cliente = new Cliente();
    private Funcionario funcionario = new Funcionario();
    private String dt_retirada;
    private String dt_devolucao;

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getDt_retirada() {
        return dt_retirada;
    }

    public void setDt_retirada(String dt_retirada) {
        this.dt_retirada = dt_retirada;
    }

    public String getDt_devolucao() {
        return dt_devolucao;
    }

    public void setDt_devolucao(String dt_devolucao) {
        this.dt_devolucao = dt_devolucao;
    }
    
}
