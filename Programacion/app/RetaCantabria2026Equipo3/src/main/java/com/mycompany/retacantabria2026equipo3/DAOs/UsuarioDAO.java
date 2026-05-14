/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.DAOs;

import com.mycompany.retacantabria2026equipo3.modelos.usuarioroles.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM121
 */
public class UsuarioDAO {

    //==========================================================================
    //ESTE DAO ESTA ASIGNADO A : SAUL
    //==========================================================================
    //
    /**
     * Insertar Usuario Permite insertar un usuario en la base de datos
     *
     * @param con
     * @param usuario
     * @return
     * @throws SQLException
     */
    public static int insertarUsuario(Usuario usuario) throws SQLException {
        int resultado = -1;
        PreparedStatement ps = null;
        String s = "INSERT INTO usuario (nombre, apellidos, email, contraseña, rol, activo) VALUES (?,?,?,?,?,?)";

        if (usuario != null && !existeUsuario(usuario.getEmail())) {
            try (Connection con = AccesoBaseDatos.getInstance().getConn()) {
                ps = con.prepareStatement(s);
                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getApellidos());
                ps.setString(3, usuario.getEmail());
                ps.setString(4, usuario.getContraseña());
                ps.setString(5, usuario.getRol());
                ps.setBoolean(6, usuario.isActivo());
                int valor = ps.executeUpdate();
                // Si la sentencia se ejecuta correctamente, devolvemos 0
                if (valor == 0) {
                    resultado = -1;
                } else {
                    resultado = 0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    public static boolean comprobarUsuario(String email, String contraseña) throws SQLException {
        String s = "SELECT contraseña FROM usuario WHERE email = ?";
        
        try (Connection con = AccesoBaseDatos.getInstance().getConn(); PreparedStatement ps = con.prepareStatement(s)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String bd = rs.getString("contraseña");
                    System.out.println("Usuario encontrado");
                    return contraseña.equals(bd);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return false;
    }

    /**
     * Existe Usuario Comprueba si un usuario ya existe
     *
     * @param con
     * @param email
     * @return
     */
    public static boolean existeUsuario(String email) {
        // Variables
        boolean resultado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String s = "SELECT * FROM usuario WHERE email = ?";
        try (Connection con = AccesoBaseDatos.getInstance().getConn()) {
            // Preparamos la sentencia con los datos del propietario
            ps = con.prepareStatement(s);
            ps.setString(1, email);
            // Ejecutamos la sentencia.
            rs = ps.executeQuery();
            // Si la sentencia se ejecuta correctamente, devolvemos true
            resultado = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    /**
     * Borrar Usuario Permite borrar un usuario en la base de datos
     *
     * @param con
     * @param idUsuario
     * @return
     */
    public static int borrarUsuario(Connection con, int idUsuario) {
        int resultado = -1;
        PreparedStatement ps = null;

        String s = "DELETE FROM usuario WHERE id_usuario = ?";
        try {
            ps = con.prepareStatement(s);
            ps.setInt(1, idUsuario);
            int valor = ps.executeUpdate();
            // Si la sentencia se ejecuta correctamente, devolvemos 0
            if (valor == 0) {
                resultado = -1;
            } else {
                resultado = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
