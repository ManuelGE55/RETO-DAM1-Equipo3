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
    private List<Material> materiales;
    private Inventario(ArrayList materiales){
        this.materiales = materiales;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    @Override
    public String toString() {
        return "Inventario{" + "materiales=" + materiales + '}';
    }
    
    
}
