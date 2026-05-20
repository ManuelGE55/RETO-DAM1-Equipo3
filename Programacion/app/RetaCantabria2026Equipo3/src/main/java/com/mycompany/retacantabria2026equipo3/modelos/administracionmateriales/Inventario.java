/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar el inventario general del sistema.
 *
 * Permite almacenar y añadir materiales registrados en la app.
 *
 * @author Hugo Fernández
 */
public class Inventario {

    private ArrayList<Material> materiales;

    /**
     * Constructor de la clase Inventario
     *
     * @param materiales
     *
     * @author Hugo Fernández
     */
    public Inventario(ArrayList<Material> materiales) {
        this.materiales = materiales;
    }

    public ArrayList<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(ArrayList<Material> materiales) {
        this.materiales = materiales;
    }

    /**
     * Añade un material al inventario.
     *
     * @param m
     *
     * @author Hugo Fernández
     */
    public void anadirMaterial(Material m) {
        materiales.add(m);
    }

    /**
     * Devuelve la información del inventario en formato texto.
     *
     * @return
     *
     * @author Hugo Fernández
     */
    @Override
    public String toString() {
        return "Inventario{" + "materiales=" + materiales + '}';
    }

}
