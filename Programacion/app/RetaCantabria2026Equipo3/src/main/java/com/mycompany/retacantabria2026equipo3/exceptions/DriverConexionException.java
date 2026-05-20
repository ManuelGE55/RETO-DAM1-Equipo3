/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.exceptions;

/**
 * Excepción personalizada lanzada cuando un argumento o identificador no existe
 * dentro del sistema.
 *
 * Se utiliza principalmente para indicar que una ubicación solicitada no ha
 * sido encontrada.
 *
 *
 * @author Hugo Fernández Calzado
 */
public class DriverConexionException extends Exception {

    public DriverConexionException(String message) {
        super(message);
    }
}
