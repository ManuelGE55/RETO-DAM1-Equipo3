/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.gestores;

import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Hugo Fernández Calzado
 */
public class GestorInformes {
    
    // atributos que contará los archivos para ir creando archivos cada vez que se exporte uno nuevo
    private static final File carpetaFicheros = new File("src/main/Informes");
    private static File[] listaFicheros = carpetaFicheros!=null?carpetaFicheros.listFiles():null;
    private static int contFicheros = listaFicheros==null?0:listaFicheros.length;
    
    public static void exportarInforme(List<Material> materiales) {
        if (!carpetaFicheros.exists()) {
            carpetaFicheros.mkdirs();
        }
        File materialesInforme = new File("src/main/Informes/inventarioInforme" + (++contFicheros) + ".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(materialesInforme))) {
            bw.write("--- Informe materiales " + (contFicheros + 1) + " ---");
            bw.newLine();
            bw.newLine();
            for (Material material : materiales) {
                bw.write(material.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
