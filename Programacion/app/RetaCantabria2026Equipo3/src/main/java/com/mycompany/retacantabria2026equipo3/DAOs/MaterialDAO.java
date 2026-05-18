/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM121
 */
public class MaterialDAO {

    //==========================================================================
    //ESTE DAO ESTA ASIGNADO A : NAYA
    //==========================================================================
    //ActucalizarEstado
    //Permite actualizar el estado de un material
    public static int ActualizarEstado(String descr, String est, int ubi, int id) {
        int resultado = -1;
        PreparedStatement ps = null;
        String s = "UPDATE material SET descripción = ? , estado = ?, id_ubicacion = ?  WHERE id_material = ?";
        try (Connection con = AccesoBaseDatos.getInstance().getConn()){
            if (existeId(id) && UbicacionDAO.existeId(id)) {
                System.out.println(existeId(id));
                System.out.println(UbicacionDAO.existeId(id));
                ps = con.prepareStatement(s);
                ps.setString(1,descr);
                ps.setString(2, est);
                ps.setInt(3,ubi);
                ps.setInt(4,id);
                
                int valor = ps.executeUpdate();
                if (valor == 0) {
                    resultado = -1;
                } else {
                    resultado = 0;
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("no se pudo actualizar");;
        }
        return resultado;
    }

    public static boolean existeId(int id) {
        // Variables
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String s = "SELECT * FROM material WHERE id_material = ?";
        try (Connection con = AccesoBaseDatos.getInstance().getConn()){
            // Preparamos la sentencia con los datos del vehiculo
            ps = con.prepareStatement(s);
            ps.setInt(1, id);
            // Ejecutamos la sentencia.
            rs = ps.executeQuery();
            // Si la sentencia se ejecuta correctamente, devolvemos true
            resultado = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    
    public static ResultSet obtenerMaterialesParaJSON(Connection con) throws SQLException {

        String sql = """
        SELECT 
            m.id_material,
            m.nombre,
            m.descripcion,
            dm.cantidad AS cantidad,
            u.armario,
            u.balda,
            u.cajon
        FROM material m
        INNER JOIN datos_material dm ON m.nombre = dm.nombre
        LEFT JOIN ubicacion u ON m.id_ubicacion = u.id_ubicacion
    """;

        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
}
