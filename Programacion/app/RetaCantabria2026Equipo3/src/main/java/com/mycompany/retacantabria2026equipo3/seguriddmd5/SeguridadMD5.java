/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.seguriddmd5;

import java.security.MessageDigest;

/**
 *
 * @author DAM127
 */
public class SeguridadMD5 {

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
