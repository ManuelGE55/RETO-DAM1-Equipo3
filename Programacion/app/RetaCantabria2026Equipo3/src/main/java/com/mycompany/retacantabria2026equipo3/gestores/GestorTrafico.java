/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.gestores;

import com.mycompany.retacantabria2026equipo3.DAOs.MaterialDAO;
import com.mycompany.retacantabria2026equipo3.enums.Categoria;
import com.mycompany.retacantabria2026equipo3.enums.Estado;
import static com.mycompany.retacantabria2026equipo3.interfazgrafica.Pantalla.inventario;
import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de gestionar la importación y exportación de inventarios en
 * formato CSV.
 *
 * Permite:
 *
 * - Exportar materiales del inventario. - Importar materiales desde archivos
 * CSV. - Generar automáticamente nuevos archivos numerados para evitar
 * sobrescrituras.
 *
 * @author Hugo Fernández
 */
public class GestorTrafico {

    // atributos que contará los archivos para ir creando archivos cada vez que se exporte uno nuevo
    private static final File carpetaFicheros = new File("src/main/CSVs");
    private static File[] listaFicheros = carpetaFicheros != null ? carpetaFicheros.listFiles() : null;
    private static int contFicheros = listaFicheros == null ? 0 : listaFicheros.length;

    /**
     * Carga materiales desde un archivo CSV y los añade al inventario del
     * sistema.
     *
     * Cada línea del archivo es interpretada como un material independiente
     *
     * @param inventarioCSV
     *
     * @author Hugo Fernández
     */
    public static void cargarInventario(File inventarioCSV) {

        try (BufferedReader br = new BufferedReader(new FileReader(inventarioCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                inventario.anadirMaterial(new Material(datos[0], datos[1], Categoria.valueOf(datos[2]), Estado.valueOf(datos[3]), datos[4]));
                if (MaterialDAO.existeMaterial(datos[0])) {

                    MaterialDAO.InsertarMaterial(datos[0], datos[1],datos[2], datos[3]);
                } else {
                    MaterialDAO.InsertarTipoMaterial(datos[0], datos[1],datos[2], datos[3],Categoria.valueOf(datos[4]),Integer.parseInt(datos[6]));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(GestorTrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Exporta los materiales del inventario a un archivo CSV.
     *
     * El archivo generado contiene toda la información necesaria de cada
     * material:
     *
     * - ID 
     * - Nombre 
     * - Descripción 
     * - Cantidad 
     * - Categoría 
     * - Estado 
     * - Ubicación
     *
     * @param materiales
     *
     * @author Hugo Fernández
     */
    public static void exportarInventario(List<Material> materiales) {
        if (!carpetaFicheros.exists()) {
            carpetaFicheros.mkdirs();
        }
        File inventarioCSV = new File("src/main/CSVs/inventarioCSV" + (++contFicheros) + ".xlsx");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inventarioCSV, true))) {
            bw.write("Id,Nombre,Descripción,Cantidad,StockMinimo,Categoría,Estado,IdUbicacion\n");
            for (Material material : materiales) {
                bw.write(material.getNombre() + "," + material.getDescripcion() + ","+ material.getEstado().name() + "," + material.getCategoria().name() + "," + material.getIdUbicacion() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
