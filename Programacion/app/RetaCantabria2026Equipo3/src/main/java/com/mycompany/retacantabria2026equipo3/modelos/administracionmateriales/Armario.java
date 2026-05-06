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
public class Armario extends Ubicacion {
    private String descripcion;
     public Armario(String descripcion,ArrayList materiales) {
         super(descripcion,materiales);
        this.descripcion = descripcion;
        
    }

    @Override
    public String toString() {
        return "Cajon{" + "descripcion=" + descripcion + '}';
    }
}
