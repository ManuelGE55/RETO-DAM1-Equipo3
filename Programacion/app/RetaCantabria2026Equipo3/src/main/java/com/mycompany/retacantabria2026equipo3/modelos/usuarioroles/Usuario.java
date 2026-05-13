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
    protected boolean activo;
    protected String nombre;
    protected String apellidos;
    protected String rol;
    protected String contraseña;

    public Usuario() {
    }

    public Usuario(String email, boolean activo, String nombre, String apellidos, String rol, String contraseña) {
        this.email = email;
        this.activo = activo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.contraseña = contraseña;
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

    public boolean isActivo() {
        return activo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getRol() {
        return rol;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
