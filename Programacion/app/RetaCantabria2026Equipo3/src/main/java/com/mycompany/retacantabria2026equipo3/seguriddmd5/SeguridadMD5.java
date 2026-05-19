/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.seguriddmd5;

import java.security.MessageDigest;

/**
 *Clase encargada de generar hashes MD5
 * para el almacenamiento seguro de contraseñas.
 * 
 * Permite convertir un texto plano en una cadena 
 * cifrada mediante el algoritmo MD5.
 * 
 * @author Saúl Valdunciel
 */
public class SeguridadMD5 {
    /**
     * Genera el hash MD5 de un texto.
     * 
     * El método transforma una cadena de texto
     * en su representación cifrada utilizando
     * el algoritmo MD5.
     * 
     * @param texto
     * @return 
     * 
     * @author Saúl Valdunciel
     */
    public static String generarMD5(String texto) {

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] array = md.digest(texto.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : array) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
