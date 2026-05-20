/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.DAOs;

import com.mycompany.retacantabria2026equipo3.enums.Categoria;
import com.mycompany.retacantabria2026equipo3.enums.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
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
    public static void InsertarMaterial(String nombre,String descripcion,String estado,String IdUbicacion){
        String sql="INSERT INTO material(nombre, descripcion, estado,id_ubicacion) VALUES(?,?,?,?)";
        try(Connection conn = AccesoBaseDatos.getInstance().getConn();PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setString(2,descripcion);
            ps.setString(3,estado);
            ps.setString(4,IdUbicacion);
            int resultado = ps.executeUpdate();
            if(resultado!=1){
                //NO SE PUDO AÑADIR EL MATERIAL
                System.out.println("no se pudo añadir el material");
            }
            else{
                //SE AÑADIO CORRECTAMENTE
                System.out.println("Se añadio correctamente");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void InsertarTipoMaterial(String nombre,String descripcion,String estado,String IdUbicacion,Categoria categoria){
        String sql="INSERT INTO datos_material (nombre, cantidad, stock_minimo, categoria) VALUES(?,?,?,?)";
        try(Connection conn = AccesoBaseDatos.getInstance().getConn();PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setInt(2,1);
            ps.setInt(3,1);
            ps.setString(4,categoria.name());
            int resultado = ps.executeUpdate();
            if(resultado!=1){
                //NO SE PUDO AÑADIR EL MATERIAL
                System.out.println("no se pudo añadir el tipo material");
                
            }
            else{
                //SE AÑADIO CORRECTAMENTE
                InsertarMaterial(nombre,descripcion,estado,IdUbicacion);
                System.out.println("Se añadio el tipo material correctamente");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //ActucalizarEstado
    //Permite actualizar el estado de un material
    public static int ActualizarEstado(String descr, String est, int ubi, int id, int id_usu) throws SQLException {
        int resultado = -1;
        PreparedStatement ps = null;
        String s = "UPDATE material SET descripcion = ? , estado = ?, id_ubicacion = ?  WHERE id_material = ?";
        Connection con = AccesoBaseDatos.getInstance().getConn();
        try {
            boolean uno = existeId(id);
            boolean dos = UbicacionDAO.existeId(ubi);
            System.out.println(uno);
            System.out.println(dos);
            if (uno && dos) {
                ps = con.prepareStatement(s);
                ps.setString(1, descr);
                ps.setString(2, est);
                ps.setInt(3, ubi);
                ps.setInt(4, id);
                UsuarioDAO.pasarUsuario(id_usu, con);
                
                int valor = ps.executeUpdate();
                if (valor == 0) {
                    resultado = -1;
                } else {
                    resultado = 0;
                }
                if (ps != null) ps.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    public static boolean existeId(int id) throws SQLException {
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String s = "SELECT * FROM material WHERE id_material = ?";
        Connection con = AccesoBaseDatos.getInstance().getConn();
        try {
            ps = con.prepareStatement(s);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            resultado = rs.next();
            if (ps != null) ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    public static boolean existeMaterial(String nombre,String categoria) throws SQLException {
        // Variables
        boolean resultado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String s = "SELECT * FROM datos_material WHERE nombre = ? AND categoria = ? ";
        Connection con = AccesoBaseDatos.getInstance().getConn();
        try {
            // Preparamos la sentencia con los datos del vehiculo
            ps = con.prepareStatement(s);
            ps.setString(1,nombre);
            ps.setString(2,categoria);
            // Ejecutamos la sentencia.
            rs = ps.executeQuery();
            // Si la sentencia se ejecuta correctamente, devolvemos true
            resultado = rs.next();
            if (ps != null) ps.close();
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
