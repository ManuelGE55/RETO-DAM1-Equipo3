/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.gestores;

import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Inventario;
import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author DAM212
 */
public class GestorTrafico {
    
    public static String cargarInventario(File inventarioCSV) {
        String devolverDatos = "";
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(inventarioCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(csvSplitBy);
                for (String dato : datos) {
                    devolverDatos += dato + " ";
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return devolverDatos;
    }
    
    public static void exportarInventario(Inventario inventario) {
        File inventarioCSV = new File("src/main/CSVs/inventarioCSV.xlsx");
        List<Material> materiales = inventario.getMateriales();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inventarioCSV, true))) {
            bw.write("Nombre,Descripción,Categoría,Estado\n");
            for (Material material : materiales) {
                bw.write(material.getNombre() + "," + material.getDescripcion() + "," + material.getCategoria() + "," + material.getEstado() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
