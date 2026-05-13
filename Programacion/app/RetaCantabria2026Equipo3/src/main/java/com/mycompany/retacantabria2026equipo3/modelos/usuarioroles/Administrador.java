/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.usuarioroles;

/**
 *
 * @author Hugo Fernández Calzado
 */
public class Administrador extends Usuario {

    public Administrador() {
        super();
    }

    public Administrador(String email, boolean activo, String nombre, String apellidos, String rol, String contraseña) {
        super(email, activo, nombre, apellidos, rol, contraseña);
    }
    
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
