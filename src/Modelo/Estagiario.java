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
public class Estagiario {
    private int idEstag;
    private String instEnsino;
    private Funcionario funcionario = new Funcionario();

    public int getIdEstag() {
        return idEstag;
    }

    public void setIdEstag(int idEstag) {
        this.idEstag = idEstag;
    }

    public String getInstEnsino() {
        return instEnsino;
    }

    public void setInstEnsino(String instEnsino) {
        this.instEnsino = instEnsino;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
}
