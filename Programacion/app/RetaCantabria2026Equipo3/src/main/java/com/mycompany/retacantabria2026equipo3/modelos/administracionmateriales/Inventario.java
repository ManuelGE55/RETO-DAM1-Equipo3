/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAM212
 */
public class Inventario {
    private ArrayList<Material> materiales;
    public Inventario(ArrayList<Material> materiales){
        this.materiales = materiales;
    }

    public ArrayList<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(ArrayList<Material> materiales) {
        this.materiales = materiales;
    }
    public void anadirMaterial(Material m){
        materiales.add(m);
    }
    
    

    @Override
    public String toString() {
        return "Inventario{" + "materiales=" + materiales + '}';
    }
    
    
}