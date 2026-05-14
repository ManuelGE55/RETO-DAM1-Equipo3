/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author DAM212
 */
public class Inventario {
    private LinkedList<Material> materiales;
    public Inventario(LinkedList<Material> materiales){
        this.materiales = materiales;
    }

    public LinkedList<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(LinkedList<Material> materiales) {
        this.materiales = materiales;
    }
    public void añadirMaterial(Material m){
        this.materiales.add(m);
    }
    
    

    @Override
    public String toString() {
        return "Inventario{" + "materiales=" + materiales + '}';
    }
    
    
}