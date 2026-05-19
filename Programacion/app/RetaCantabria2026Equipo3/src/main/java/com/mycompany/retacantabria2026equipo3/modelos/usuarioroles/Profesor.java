/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.usuarioroles;

/**
 *Clase que representa a un profesor del sistema.
 * 
 * Hereda de la clase Usuario y posee permisos
 * especificos relacionados con la gestión
 * del inventario.
 * 
 * @author Hugo Fernández
 */
public class Profesor extends Usuario {
    /**
     * Constructor por defecto de la clase profesor.
     * 
     * @author Hugo Fernández
     */
    public Profesor() {
        super();
    }
    /**
     * Constructor de la clase profesor
     * 
     * @param email
     * @param activo
     * @param nombre
     * @param apellidos
     * @param rol
     * @param contraseña 
     * 
     * @author Hugo Fernández
     */
    public Profesor(String email, boolean activo, String nombre, String apellidos, String rol, String contraseña) {
        super(nombre,apellidos,email,contraseña);
    }
    /**
     * Devuelve la información del profesor en formato texto.
     * 
     * @return 
     * 
     * @author Hugo Fernández
     */
    @Override
    public String toString() {
        return "Profesor("
                + "Nombre: " + this.nombre + " | "
                + "Email: " + this.email + ""
                + ")";
    }
    
    // metodos para Frontend

//    @Override
//    public String consultarInventario() {
//        
//    }
//
//    @Override
//    public String localizarMaterial() {
//        
//    }
//
//    @Override
//    public boolean registarPrestamo() {
//        
//    }
//
//    @Override
//    public boolean login(String passwordIntroducida) {
//        boolean accesoPermitido = false;
//        if (passwordIntroducida.equals(this.password)) {
//            accesoPermitido = true;
//        }
//        return accesoPermitido;
//    }
}
