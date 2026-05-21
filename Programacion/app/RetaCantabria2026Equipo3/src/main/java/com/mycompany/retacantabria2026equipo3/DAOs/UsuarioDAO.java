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
 * Clase DAO encargada de gestionar las operaciones relacionadas con los
 * usuarios en la base de datos.
 *
 * Permite:
 *
 * - Insertar usuarios. 
 * - Comprobar credenciales de acceso. 
 * - Verificar si un usuario existe. 
 * - Eliminar usuarios. 
 * - Asociar usuarios a operaciones realizadas.
 *
 * Esta clase actúa como intermediaria entre la aplicación y la tabla usuario de
 * MySQL.
 *
 * @author Saúl Valdunciel
 */
public class UsuarioDAO {

    /**
     * Ejecuta el procedimiento almacenado encargado de definir el identificador
     * del usuario activo dentro de la sesión de base de datos.
     *
     * Este procedimiento se utiliza para relacionar operaciones realizadas con
     * el usuario correspondiente.
     *
     * @param id
     * @param con
     *
     * @author Saúl Valdunciel
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

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * Antes de realizar la inserción, se comprueba que el usuario no exista
     * previamente mediante su email.
     *
     * La contraseña se almacena utilizando MD5.
     *
     * @param usuario
     * @return
     * @throws SQLException
     *
     * @author Saúl Valdunciel
     */
    public static int insertarUsuario(Usuario usuario) throws SQLException {
        int resultado = -1;
        PreparedStatement ps = null;
        String s = "INSERT INTO usuario (nombre, apellidos, email, contraseña, rol, activo, fecha_creacion) VALUES (?,?,?,MD5(?),?,?,?)";

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

    /**
     * Comprueba las credenciales de un usuario dentro de la base de datos.
     *
     * Si las credenciales son correctas y el usuario está activo, se crea
     * automáticamente un objeto Profesor o Administrador dependiendo del rol.
     *
     * @param email
     * @param contraseña
     * @return
     * @throws SQLException
     *
     * @author Saúl Valdunciel
     */
    public static Usuario comprobarUsuario(String email, String contraseña) throws SQLException {
        Usuario usuario = null;
        String s = "SELECT nombre,apellidos,contraseña,email,activo,rol,id_usuario FROM usuario WHERE email = ? AND contraseña = MD5(?)";

        try (Connection con = AccesoBaseDatos.getInstance().getConn(); PreparedStatement ps = con.prepareStatement(s)) {

            ps.setString(1, email);
            ps.setString(2, contraseña);

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
     * Comprueba si existe un usuario registrado con el email indicado.
     *
     * @param email
     * @return
     *
     * @author Saúl Valdunciel
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
     * Elimina un usuario de la base de datos utilizando su email.
     *
     * @param con
     * @param email
     * @return
     *
     * @author Saúl Valdunciel
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
