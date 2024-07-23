/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.util;

import java.io.Serializable;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public class Generador implements Serializable {

    public static String NUMEROS = "0123456789";

    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    public static String generarPassword() {
        return generarLetrasNumeros(10);
    }

    public static String generarLetrasNumeros(int lenght) {
        return generar(MAYUSCULAS + MINUSCULAS + NUMEROS, lenght);
    }

    public static String generar(String key, int length) {
        String pswd = "";
        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }
        return pswd;
    }

}
