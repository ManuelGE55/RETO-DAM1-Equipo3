package com.mycompany.retacantabria2026equipo3.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
/**
 * Clase encargada de gestionar la conexión con la base de datos MySQL del
 * inventario.
 *
 * Utiliza el patrón Singleton para mantener una única instancia de conexión
 * reutilizable durante la ejecución de la aplicación.
 *
 * @author Ciro Galán
 */
public class AccesoBaseDatos {

    private Connection conn = null; //atributo conexión
    // constantes a definir
    private static final String BD = "inventario"; //Nombre de la base de datos
    private static final String USUARIO = "root"; //Nombre de usuario
    private static final String CLAVE = "root"; //Contraseña de la instancia
    private static final String URL = "jdbc:mysql://44.217.68.114/" + BD; //URL de la base de datos
//    private static final String URL = "jdbc:mysql://127.0.0.1:3306/" + BD; //URL de la base de datos

    /**
     * Contructor privado de la clase.
     *
     * Inicializa la conexión con la base de datos utilizando las credenciales y
     * la URL definidos como constantes.
     *
     * @author Ciro Galán
     */
    private AccesoBaseDatos() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", USUARIO);
            properties.setProperty("password", CLAVE);
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");
            conn = (Connection) DriverManager.getConnection(URL, properties);
            if (conn == null) {
                System.out.println("Error en conexion");
            } else {
                System.out.println("Conexion correcta a: " + URL);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     * Devuelve la única instancia de acceso a la base de datos.
     *
     * @return
     *
     * @author Ciro Galán
     */
    public static AccesoBaseDatos getInstance() {
        return AccesoBaseDatosHolder.INSTANCE;
    }

    /**
     * Clase interna utilizada para implementar el patrón Singleton de forma
     * segura.
     *
     * @author Ciro Galán
     */
    private static class AccesoBaseDatosHolder {

        private static final AccesoBaseDatos INSTANCE = new AccesoBaseDatos();
    }

    /**
     * Devuelve la conexión activa con la base de datos.
     *
     * Si la conexión está cerrada o no existe, se vuelve a crear
     * automáticamente.
     *
     * @return
     * @throws SQLException
     *
     * @author Ciro Galán
     */
    public Connection getConn() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
        }
        return conn;
    }
}
