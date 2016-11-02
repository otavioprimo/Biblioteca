/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Usuário
 */
public class ThreadAtualiza implements Runnable {

    Thread thread;
    public ThreadAtualiza(String nome) {
        thread = new Thread(this,nome);
        
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Atualizando Tabela...");
        try {
            for (int i = 0; i < 30; i++) {
                
            }
                  
        } catch (Exception e) {
        }
    }

}
