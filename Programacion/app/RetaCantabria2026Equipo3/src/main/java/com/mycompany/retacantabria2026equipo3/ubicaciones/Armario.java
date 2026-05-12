/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.ubicaciones;

import com.mycompany.retacantabria2026equipo3.ubicaciones.Ubicacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAM212
 */
public class Armario extends Ubicacion {

    public Armario(ArrayList materiales, String descripcion) {
        super(materiales, descripcion);
    }
    
     

    @Override
    public String toString() {
        return "Cajon{" + "descripcion=" + descripcion + '}';
    }
}
