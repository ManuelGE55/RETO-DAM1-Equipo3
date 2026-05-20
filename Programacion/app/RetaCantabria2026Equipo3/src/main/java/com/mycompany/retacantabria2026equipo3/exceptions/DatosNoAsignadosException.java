/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.exceptions;

/**
 *Excepción personalizada lanzada cuando existen
 * datos obligatorios sin asignar dentro del sistema.
 * 
 * Se utiliza para controlar errores relacionados con
 * información incompleta o no definida.
 * 
 * @author Hugo Fernández Calzado
 */
public class DatosNoAsignadosException extends Exception {
    /**
     * Constructor de la excepción.
     * 
     * @author Hugo Fernández Calzado
     */
    public DatosNoAsignadosException(String message) {
        super(message);
    }
}
