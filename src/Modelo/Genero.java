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
public class Genero {
    private int idGenero;
    private String tipo;

    public Genero() {
    }

    public Genero(int idGenero, String tipo) {
        this.idGenero = idGenero;
        this.tipo = tipo;
    }
    
    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
    
    
}
