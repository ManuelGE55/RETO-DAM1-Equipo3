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
    public static int insertarUsuario(Connection con, Usuario usuario) throws SQLException {
        int resultado = -1;
        PreparedStatement ps = null;
        String s = "INSERT INTO usuario (nombre, apellidos, email, contraseña, rol, activo) VALUES (?,?,?,?,?,?)";

        if (usuario != null && !existeUsuario(con, usuario.getEmail())) {
            try {
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

    public static boolean comprobarUsuario(Connection con, String email, String contraseña) throws SQLException {
        boolean resultado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String s = "SELECT contraseña FROM usuario WHERE email = ?";
        try {
            if (email != null && existeUsuario(con, email) && contraseña != null) {
                ps = con.prepareStatement(s);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String bd = rs.getString("contraseña");
                    if (contraseña == bd) {
                        resultado = true;
                    } else {
                        resultado = false;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    /**
     * Existe Usuario Comprueba si un usuario ya existe
     *
     * @param con
     * @param email
     * @return
     */
    public static boolean existeUsuario(Connection con, String email) {
        // Variables
        boolean resultado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String s = "SELECT * FROM usuario WHERE email = ?";
        try {
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
