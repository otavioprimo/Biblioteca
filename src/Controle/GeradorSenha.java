/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.util.Random;

/**
 *
 * @author Usuário
 */
public class GeradorSenha {

    public static String gerarSenha(){
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ1234567890";

        Random random = new Random();
        
        String senha = "";
        int index = -1;
        for (int i = 0; i < 6; i++) {
            index = random.nextInt(letras.length());
            senha += letras.substring(index, index + 1);
        }
        return senha;
    }
}
