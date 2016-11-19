/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DAO.DaoFuncionario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuário
 */
public class BloqueioUsuario {

    public int CodFunc;
    int Nivel;

    private void setNivel() {
        DaoFuncionario f = new DaoFuncionario();
        try {
            Nivel = f.getNivel(CodFunc);
        } catch (SQLException ex) {
            Logger.getLogger(BloqueioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNivel() {
        setNivel();
        return Nivel;
    }
}
