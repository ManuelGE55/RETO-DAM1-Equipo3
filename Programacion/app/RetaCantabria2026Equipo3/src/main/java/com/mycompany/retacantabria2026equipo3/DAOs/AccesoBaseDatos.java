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
 *
 * @author dam121
 */
public class AccesoBaseDatos {
    
    private Connection conn = null; //atributo conexión
    // constantes a definir
    private static final String BD = "inventario"; //Nombre de la base de datos
    private static final String USUARIO = "root"; //Nombre de usuario
    private static final String CLAVE = "root"; //Contraseña de la instancia
    private static final String URL = "jdbc:mysql://untu@44.217.68.114:3306/" + BD; //URL de la base de datos
    
    
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
    
    public static AccesoBaseDatos getInstance() {
        return AccesoBaseDatosHolder.INSTANCE;
    }
    
    private static class AccesoBaseDatosHolder {
        private static final AccesoBaseDatos INSTANCE = new AccesoBaseDatos();
    }

    public Connection getConn() {
        return conn;
    }
}
