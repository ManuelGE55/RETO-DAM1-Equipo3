/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales;

import com.mycompany.retacantabria2026equipo3.enums.Categoria;
import com.mycompany.retacantabria2026equipo3.enums.Estado;

/**
 * Clase que representa un material almacenado dentro del inventario del
 * sistema.
 *
 * Cada material posee información como su nombre, cantidad disponible y
 * ubicación asignada
 *
 * @author Hugo Fernández
 */
public class Material {

    private int id;
    private String nombre;
    private String descripcion;
    private static int cantidad;
    private int stockMinimo;
    private Categoria categoria;
    private Estado estado;
    private String idUbicacion;

    /**
     * Contructor de la clase Material.
     *
     * @param nombre
     * @param descripcion
     * @param categoria
     * @param estado
     * @param idUbicacion
     *
     * @author Hugo Fernández
     */
    public Material(String nombre, String descripcion, Categoria categoria, Estado estado, String idUbicacion) {

        this.nombre = nombre;
        this.descripcion = descripcion;
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

    /**
     * Devuelve la información del material en formato texto.
     *
     * @return
     *
     * @author Hugo Fernández
     */
    @Override
    public String toString() {
        return "Material("
                + "ID: " + this.id + " | "
                + "Nombre: " + this.nombre + " | "
                + "Descripción: " + this.descripcion + " | "
                + "Cantidad: " + this.cantidad + " | "
                + "Stock mínimo: " + this.stockMinimo + " | "
                + "Categoría: " + this.cantidad + " | "
                + "Estado: " + this.estado + " | "
                + "ID Ubicación: " + this.idUbicacion + ""
                + ")";
    }
}
