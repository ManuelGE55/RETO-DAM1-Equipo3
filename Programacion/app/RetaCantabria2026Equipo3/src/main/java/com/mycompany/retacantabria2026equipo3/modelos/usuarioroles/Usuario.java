/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.usuarioroles;

import com.mycompany.retacantabria2026equipo3.enums.Rol;

/**
 *
 * @author DAM212
 */
public abstract class Usuario {
    
    protected String nombre,apellidos,email,contraseña;
    protected Rol rol;
    protected int id;

    public Usuario() {
    }

    public Usuario(String nombre,String apellidos,String email,String contraseña, Rol rol) {
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contraseña = contraseña;
        this.rol = rol;
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

    public String getApellidos() {
        return apellidos;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public int getId(){
        return id;
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
