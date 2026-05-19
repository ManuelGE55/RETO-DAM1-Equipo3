/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.ubicaciones;

import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase abstracta que representa una ubicación donde se almacenan los
 * materiales dentro del sistema.
 *
 * Una ubicación puede ser un cajón, una balda o un armario. Cada ubicación
 * contiene una lista de materiales y una descripción.
 *
 * @author Ciro Galán
 */
public abstract class Ubicacion {

    protected List<Material> materiales;
    protected String descripcion;

    /**
     * Constructor de la clase Ubicacion.
     *
     * @param materiales
     * @param descripcion
     *
     * @author Ciro Galán
     */
    public Ubicacion(ArrayList materiales, String descripcion) {
        this.materiales = materiales;
        this.descripcion = descripcion;
    }

    /**
     * Añade un material a la ubicación.
     *
     * @param material
     * @return
     *
     * @author Ciro Galán
     */
    public boolean addMaterial(Material material) {
        return materiales.add(material);
    }

    /**
     * Devuelve la información de la ubicación en formato texto-
     *
     * @return
     *
     * @author Ciro Gálan
     */
    @Override
    public abstract String toString();
}
