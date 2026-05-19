/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.ubicaciones;

import com.mycompany.retacantabria2026equipo3.ubicaciones.Ubicacion;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una balda dentro del sistema de almacenamiento.
 *
 * Hereda de la clase abstracta de Ubicacion.
 *
 * @author Ciro Galán
 */
public class Balda extends Ubicacion {

    /**
     * Constructor de la clase Balda.
     *
     * @param materiales
     * @param descripcion
     *
     * @author Ciro Galán
     */
    public Balda(ArrayList materiales, String descripcion) {
        super(materiales, descripcion);
    }

    /**
     * Devuelve la información de la balda en formato texto.
     *
     * @return
     *
     * @author Ciro Galán
     */
    @Override
    public String toString() {
        return "Cajon{" + "descripcion=" + descripcion + '}';
    }
}
