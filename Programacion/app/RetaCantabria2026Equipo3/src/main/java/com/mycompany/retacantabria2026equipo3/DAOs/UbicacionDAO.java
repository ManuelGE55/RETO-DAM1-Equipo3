/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enumeración que representa los disintos
 * estados posibles de un material
 * dentro del inventario.
 * 
 * Los materiales pueden encontrarse:
 * 
 * - Disponibles para su uso.
 * - Prestados.
 * - En reparación.
 * - Retirados del sistema.
 * 
 * @author Ciro Galán
 */
public class UbicacionDAO {

    public static boolean existeId(int id) throws SQLException {
        // Variables
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String s = "SELECT * FROM ubicacion WHERE id_ubicacion = ?";
        Connection con = AccesoBaseDatos.getInstance().getConn();
        try {
            // Preparamos la sentencia con los datos del vehiculo
            ps = con.prepareStatement(s);
            ps.setInt(1, id);
            // Ejecutamos la sentencia.
            rs = ps.executeQuery();
            // Si la sentencia se ejecuta correctamente, devolvemos true
            resultado = rs.next();
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UbicacionDAO.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
}
