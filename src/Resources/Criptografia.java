package Resources;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
/*
    public static String criptografar(String texto) {

        String alfabeto = " <abcdefghijklmnopqrstuvwxyzçéáíúóãõABCDEFGHIJKLMNOPQRSTUVWXYZÇÁÉÓÍÚÃÕ1234567890.;:?,º]}§[{ª!@#$%&*()_+-=\\/|\'\">";

        char[] t = texto.toCharArray();
        String palavra = "";

        for (int i = 0; i < t.length; i++) {
            int posicao = alfabeto.indexOf(t[i]) + 12;
            if (alfabeto.length() <= posicao) {
                posicao = posicao - alfabeto.length();
            }
            palavra = palavra + alfabeto.charAt(posicao);
        }
        return palavra;
    }
*/
    
    private static MessageDigest md = null;
 
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
 
  private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;
 
        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 2,
                                	hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
    }
 
public static String criptografar(String pwd) {
        if (md != null) {
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
    }

}
