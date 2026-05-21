/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.DAOs;

import com.mycompany.retacantabria2026equipo3.modelos.usuarioroles.Administrador;
import com.mycompany.retacantabria2026equipo3.modelos.usuarioroles.Profesor;
import com.mycompany.retacantabria2026equipo3.modelos.usuarioroles.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    
    
    
    public static void pasarUsuario(int id, Connection con) {
        String s = "{CALL definirIdUsuario(?)}";
        try {
            PreparedStatement ps = con.prepareStatement(s);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int insertarUsuario(Usuario usuario) throws SQLException {
        int resultado = -1;
        PreparedStatement ps = null;
        String s = "INSERT INTO usuario (nombre, apellidos, email, contraseña, rol, activo, fecha_creacion) VALUES (?,?,?,md5(?),?,?,?)";

        if (usuario != null && !existeUsuario(usuario.getEmail())) {
            try (Connection con = AccesoBaseDatos.getInstance().getConn()) {
                ps = con.prepareStatement(s);
                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getApellidos());
                ps.setString(3, usuario.getEmail());
                ps.setString(4, usuario.getContraseña());
                ps.setString(5, usuario.getRol().toString().toLowerCase());
                ps.setBoolean(6, true);
                ps.setDate(7, Date.valueOf(LocalDate.now()));
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

    public static Usuario comprobarUsuario(String email, String contraseña) throws SQLException {
        Usuario usuario = null;
        String s = "SELECT nombre,apellidos,contraseña,email,activo,rol,id_usuario FROM usuario WHERE email = ? AND contraseña = MD5(?)";

        try (Connection con = AccesoBaseDatos.getInstance().getConn(); PreparedStatement ps = con.prepareStatement(s)) {

            ps.setString(1, email);
            ps.setString(2,contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    if (rs.getBoolean(5)) {
                        usuario = rs.getString(6).equals("profesor") ? new Profesor() : new Administrador();
                        usuario.setNombre(rs.getString(1));
                        usuario.setApellidos(rs.getString(2));
                        usuario.setEmail(rs.getString(4));
                        usuario.setContraseña(rs.getString(3));
                        usuario.setId(rs.getInt(7));
                    } else {
                        //USUARIO INACTIVO
                        JOptionPane.showMessageDialog(null, "Comprobar usuario", "El usuario está inactivo", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return usuario;
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
            ps = con.prepareStatement(s);
            ps.setString(1, email);
            rs = ps.executeQuery();
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
    public static int borrarUsuario(Connection con, String email) {
        int resultado = -1;
        PreparedStatement ps = null;

        String s = "DELETE FROM usuario WHERE email = ?";
        try {
            ps = con.prepareStatement(s);
            ps.setString(1, email);
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
