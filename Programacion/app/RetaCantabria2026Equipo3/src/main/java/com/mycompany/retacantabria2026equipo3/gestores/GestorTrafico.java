/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.gestores;

import com.mycompany.retacantabria2026equipo3.enums.Categoria;
import com.mycompany.retacantabria2026equipo3.enums.Estado;
import static com.mycompany.retacantabria2026equipo3.interfazgrafica.Pantalla.inventario;
import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Inventario;
import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAM212
 */
public class GestorTrafico {
    
    // atributos que contará los archivos para ir creando archivos cada vez que se exporte uno nuevo
    private static final File carpetaFicheros = new File("src/main/CSVs");
    private static File[] listaFicheros = carpetaFicheros.listFiles();
    private static int contFicheros = listaFicheros.length;
    
public static void cargarInventario(File inventarioCSV) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(inventarioCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
               String[] datos = linea.split(",");
                   inventario.anadirMaterial(new Material(datos[0],datos[1],Integer.parseInt(datos[2]),Integer.parseInt(datos[3]),Categoria.valueOf(datos[4]),Estado.valueOf(datos[5]),datos[6]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void exportarInventario(List<Material> materiales) {     
        File inventarioCSV = new File("src/main/CSVs/inventarioCSV" + (contFicheros + 1) + ".xlsx");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inventarioCSV, true))) {
            bw.write("Id,Nombre,Descripción,Cantidad,StockMinimo,Categoría,Estado,IdUbicacion\n");
            for (Material material : materiales) {
                bw.write(material.getId() + "," + material.getNombre() + "," + material.getDescripcion() + ","
                        + "" + material.getCantidad() + "," + material.getStockMinimo() + "," + material.getCategoria() + "," + material.getEstado() + ","
                                + material.getIdUbicacion() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
