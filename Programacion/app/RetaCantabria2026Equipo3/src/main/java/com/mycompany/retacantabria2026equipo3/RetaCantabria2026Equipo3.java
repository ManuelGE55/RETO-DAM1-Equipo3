/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.retacantabria2026equipo3;

import com.mycompany.retacantabria2026equipo3.DAOs.AccesoBaseDatos;
import com.mycompany.retacantabria2026equipo3.gestores.GestorLocalizaciones;
import com.mycompany.retacantabria2026equipo3.interfazgrafica.Pantalla;

/**
 * Clase principal de ejecución del proyecto.
 *
 * Esta clase se encarga de iniciar la aplicación, creando la interfaz gráfica
 * principal y estableciendo la conexión inicial con la base de datos.
 *
 * El punto de entrada de la aplicación se encuentra en el método main().
 *
 * @author Ciro Galán
 */
public class RetaCantabria2026Equipo3 {

    /**
     * Método principal de ejecución de la aplicación.
     *
     * Inicializa:
     *
     * - La ventana principal del sistema. 
     * - La conexión con la base de datos.
     *
     * @param args Argumentos de línea de comandos
     *
     * @author Ciro Galán
     */
    public static void main(String[] args) {
        Pantalla pantalla = new Pantalla();
        pantalla.setVisible(true);
        AccesoBaseDatos.getInstance();
    }
}
