/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.gestores;

import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Inventario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author DAM212
 */
public class GestorTrafico {
    
    private static final File inventarioCSV = new File("/src/main/CSVs/inventarioCSV.xlsx");
    
    public static String cargarInventario(Inventario inventario) {
        String devolverDatos = "";
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(inventarioCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(csvSplitBy);
                for (String dato: datos) {
                    devolverDatos += dato + " ";
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return devolverDatos;
    }
    
    
}
