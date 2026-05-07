/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.ubicaciones;

import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author DAM212
 */
public abstract class Ubicacion {
    
    protected List<Material> materiales;
    protected String descripcion;
    
    
    
    public Ubicacion(ArrayList materiales,String descripcion) {
        this.materiales = materiales;
        this.descripcion = descripcion;
    }
    
    public boolean addMaterial(Material material) {
        return materiales.add(material);
    }
    
    @Override
    public abstract String toString();
}
