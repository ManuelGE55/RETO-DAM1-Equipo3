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
 * Clase encargada de generar informes de materiales
 * del inventario en formato texto.
 * 
 * Los informes se almacenan automáticamente
 * dentro de la carpeta Informes del proyecto.
 * 
 * Cada exportación crea un nuevo archivo
 * numerado de forma automática para evitar
 * sobrescribir informes anteriores.
 * 
 * @author Hugo Fernández
 */
public class GestorInformes {
    
    // atributos que contará los archivos para ir creando archivos cada vez que se exporte uno nuevo
    private static final File carpetaFicheros = new File("src/main/Informes");
    private static File[] listaFicheros = carpetaFicheros!=null?carpetaFicheros.listFiles():null;
    private static int contFicheros = listaFicheros==null?0:listaFicheros.length;
    
    /**
     * Exporta un informe con todos los materiales
     * recibidos por parámetro.
     * 
     * El informe generado contiene la información
     * textual de cada material almacenado
     * 
     * @param materiales
     * 
     * @author Hugo Fernández
     */
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
