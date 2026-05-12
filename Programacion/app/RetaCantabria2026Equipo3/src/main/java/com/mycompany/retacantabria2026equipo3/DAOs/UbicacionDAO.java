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
 *
 * @author DAM101
 */
public class UbicacionDAO {
    
    public static boolean existeId(Connection con, int id) {        
        // Variables
        boolean resultado = true;
        PreparedStatement  ps = null;
        ResultSet rs = null;
        
        String s = "SELECT * FROM ubicacion WHERE id_ubicacion = ?";
            try {
                // Preparamos la sentencia con los datos del vehiculo
                ps = con.prepareStatement(s);               
                ps.setInt(1, id);
                // Ejecutamos la sentencia.
                rs = ps.executeQuery();
                // Si la sentencia se ejecuta correctamente, devolvemos true
                resultado = rs.next();
            } catch (SQLException ex) {
                Logger.getLogger(UbicacionDAO.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        
        return resultado;
    }
}
