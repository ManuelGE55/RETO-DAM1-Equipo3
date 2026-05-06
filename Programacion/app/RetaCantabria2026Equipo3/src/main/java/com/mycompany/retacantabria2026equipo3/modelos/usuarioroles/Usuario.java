/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.usuarioroles;

/**
 *
 * @author DAM212
 */
public abstract class Usuario {
    
    protected String email;
    
    protected String nombre;
    
    protected String password;

    public Usuario() {
    }

    public Usuario(String email, String nombre, String password) {
        this.email = email;
        this.nombre = nombre;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public abstract String toString();
    
    // metodos para Frontend
    
//    public abstract String consultarInventario();
//    
//    public abstract String localizarMaterial();
//    
//    public abstract boolean registarPrestamo();
//    
//    public abstract boolean login(String password);
    
}
