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
public class Balda extends Ubicacion{
   private String descripcion;
     public Balda(String descripcion,ArrayList materiales) {
        super(descripcion,materiales);
    }

    @Override
    public String toString() {
        return "Cajon{" + "descripcion=" + descripcion + '}';
    }
}
