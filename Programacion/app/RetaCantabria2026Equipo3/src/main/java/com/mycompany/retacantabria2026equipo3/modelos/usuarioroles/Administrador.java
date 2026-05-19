/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.usuarioroles;

/**
 * Clase que representa a un administrador del sistema.
 *
 * Hereda de la clase Usuario y dispone de permisos avanzados para la gestión
 * completa de la app.
 *
 * @author Hugo Fernández
 */
public class Administrador extends Usuario {

    /**
     * Constructor por defecto de la clase Administrador.
     *
     * @author Hugo Fernández
     */
    public Administrador() {
        super();
    }
    /**
     * Constructo de la clase Administrador.
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
    public Administrador(String email, boolean activo, String nombre, String apellidos, String rol, String contraseña) {
        super(nombre, apellidos, email, contraseña);
    }
    /**
     * Devuelve la información del administrador.
     * 
     * @return 
     * 
     * @author Hugo Fernández
     */
    @Override
    public String toString() {
        return "Administrador("
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
