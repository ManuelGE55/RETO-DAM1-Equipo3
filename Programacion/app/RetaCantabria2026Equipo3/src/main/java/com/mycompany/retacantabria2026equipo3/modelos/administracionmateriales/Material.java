/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales;

import com.mycompany.retacantabria2026equipo3.enums.Categoria;
import com.mycompany.retacantabria2026equipo3.enums.Estado;

/**
 *
 * @author DAM212
 */
public class Material {
    private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private int stockMinimo;
    private Categoria categoria;
    private Estado estado;
    private String idUbicacion;

    public Material(String nombre, String descripcion, int cantidad, int stockMinimo, Categoria categoria, Estado estado, String idUbicacion) {
        
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
        this.categoria = categoria;
        this.estado = estado;
        this.idUbicacion = idUbicacion;
    }


    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public int getCantidad() {
        return cantidad;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Estado getEstado() {
        return estado;
    }


    public String getIdUbicacion() {
        return idUbicacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    

}
