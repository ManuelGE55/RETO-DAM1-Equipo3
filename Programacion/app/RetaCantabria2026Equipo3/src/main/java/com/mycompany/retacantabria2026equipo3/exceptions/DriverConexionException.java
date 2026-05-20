/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.exceptions;

/**
 * Excepción personalizada lanzada cuando
 * ocurre un error de conexión con el driver
 * del navegador utilizado por Selenium.
 * 
 * Esta excepción se utiliza cuando el sistema
 * no puede ejecutar correctamente acciones
 * sobre la página web de localizaciones.
 * 
 * @author Hugo Fernández
 */
public class DriverConexionException extends Exception {
    
    public DriverConexionException(String message) {
        super(message);
    }
}
