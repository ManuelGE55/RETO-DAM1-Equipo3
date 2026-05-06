/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.usuarioroles;

/**
 *
 * @author DAM212
 */
public class Profesor extends Usuario {

    public Profesor() {
        super();
    }

    public Profesor(String email, String nombre, String password) {
        super(email, nombre, password);
    }
    
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
