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
 * Clase DAO encargada de consultar información
 * relacionada con las ubicaciones almacenadas
 * en la base de datos.
 * 
 * Permite comprobar si una ubicación existe
 * antes de asignarla a un material.
 * 
 * @author Naya Ruiz
 */
public class UbicacionDAO {
    /**
     * Comprueba si existe una ubicación en la base
     * de datos a partir de su identificador
     * 
     * @param id
     * @return
     * @throws SQLException 
     * 
     * @author Naya Ruiz
     */
    public static boolean existeId(int id) throws SQLException {        
        // Variables
        boolean resultado = true;
        PreparedStatement  ps = null;
        ResultSet rs = null;
        
        String s = "SELECT * FROM ubicacion WHERE id_ubicacion = ?";
        Connection con = AccesoBaseDatos.getInstance().getConn();
            try{
                // Preparamos la sentencia con los datos del vehiculo
                ps = con.prepareStatement(s);               
                ps.setInt(1, id);
                // Ejecutamos la sentencia.
                rs = ps.executeQuery();
                // Si la sentencia se ejecuta correctamente, devolvemos true
                resultado = rs.next();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UbicacionDAO.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        
        return resultado;
    }
}
