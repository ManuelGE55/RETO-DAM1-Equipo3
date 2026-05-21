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
import javax.swing.JOptionPane;

/**
 * Clase DAO encargada de gestionar las operaciones relacionadas con los
 * materiales en la base de datos.
 *
 * Permite:
 *
 * - Insertar materiales. 
 * - Eliminar materiales. 
 * - Insertar tipos de materiales.
 * - Actualizar materiales existentes. 
 * - Comprobar si un material existe. 
 * - Obtener materiales para generar el JSON. 
 * - Consultar alertas de stock.
 *
 * Esta clase actúa como intermediaria entre la aplicación y las tablas
 * relacionadas con los materiales del inventario.
 *
 * @author Naya Ruiz
 */
public class MaterialDAO {

    /**
     * Inserta un nuevo material en la base de datos.
     *
     * Guarda el nombre, descripción, estado y ubicación del material.
     *
     * @param nombre
     * @param descripcion
     * @param estado
     * @param IdUbicacion
     *
     * @author Naya Ruiz
     */
    public static void InsertarMaterial(String nombre, String descripcion, String estado, String IdUbicacion) {
        String sql = "INSERT INTO material(nombre, descripcion, estado,id_ubicacion) VALUES(?,?,?,?)";
        try (Connection conn = AccesoBaseDatos.getInstance().getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, estado);
            ps.setString(4, IdUbicacion);
            int resultado = ps.executeUpdate();
            if (resultado != 1) {
                //NO SE PUDO AÑADIR EL MATERIAL
                System.out.println("no se pudo añadir el material");
                JOptionPane.showMessageDialog(null,  "Argumento no encontrado","Insertar material", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //SE AÑADIO CORRECTAMENTE
                System.out.println("Se añadio correctamente");
                JOptionPane.showMessageDialog(null,  "Material añadido correctamente","Insertar material", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Elimina un material de la base de datos utilizando su identificador.
     *
     * @param id
     *
     * @author Naya Ruiz
     */
    public static void borrarMaterial(int id) {
        String sql = "DELETE FROM material WHERE id_material = ?";
        try (Connection conn = AccesoBaseDatos.getInstance().getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int resultado = ps.executeUpdate();
            if (resultado != 1) {
                //NO SE PUDO AÑADIR EL MATERIAL
                JOptionPane.showMessageDialog(null,  "No se pudo eliminar el material seleccionado","Borrado material", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,  "Material eliminado correctamente","Borrado material", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserta un nuevo tipo de material en la tabla datos_material.
     *
     * Si la inserción se realiza correctamente, también se crea automáticamente
     * el material asociado.
     *
     * @param nombre
     * @param descripcion
     * @param estado
     * @param IdUbicacion
     * @param categoria
     * @param stock
     *
     * @author Naya Ruiz
     */
    public static void InsertarTipoMaterial(String nombre, String descripcion, String estado, String IdUbicacion, Categoria categoria, int stock) {
        String sql = "INSERT INTO datos_material (nombre, cantidad, stock_minimo, categoria) VALUES(?,?,?,?)";
        try (Connection conn = AccesoBaseDatos.getInstance().getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setInt(2, 1);
            ps.setInt(3, stock);
            ps.setString(4, categoria.name());
            int resultado = ps.executeUpdate();
            if (resultado != 1) {
                //NO SE PUDO AÑADIR EL MATERIAL
                System.out.println("no se pudo añadir el tipo material");
                JOptionPane.showMessageDialog(null,  "Argumento no encontrado","Insertar tipo de material", JOptionPane.INFORMATION_MESSAGE);

            } else {
                //SE AÑADIO CORRECTAMENTE
                InsertarMaterial(nombre, descripcion, estado, IdUbicacion);
                System.out.println("Se añadio el tipo material correctamente");
                JOptionPane.showMessageDialog(null, "Tipo añadido correctamente", "Insertar tipo de material", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Comprueba si existe una alerta de stock activa para un material.
     *
     * Consulta la tabla alerta_stock para verificar si el material tiene
     * incidencias pendientes relacionadas con el stock mínimo.
     *
     * @param nombre
     * @return
     *
     * @throws SQLException
     *
     * @author Naya Ruiz
     */
    public static boolean trigger(String nombre) throws SQLException {
        boolean resultado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String s = "SELECT * FROM alerta_stock WHERE nombre_material = ? AND resuelta = false";
        Connection con = AccesoBaseDatos.getInstance().getConn();
        try {
            ps = con.prepareStatement(s);
            ps.setString(1, nombre);

            rs = ps.executeQuery();
            resultado = rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;

    }

    /**
     * Actualiza la información principal de un material existente.
     *
     * Permite modificar:
     *
     * - Descripción. 
     * - Estado. 
     * - Ubicación.
     *
     * Además registra el usuario responsable de la modificación.
     *
     * @param descr
     * @param est
     * @param ubi
     * @param id
     * @param id_usu
     *
     * @return
     *
     * @throws SQLException
     *
     * @author Naya Ruiz
     */
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
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    /**
     * Comprueba si existe un material en la base de datos a partir de su
     * identificador.
     *
     * @param id
     * @return
     *
     * @throws SQLException
     *
     * @author Naya Ruiz
     */
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
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    /**
     * Comprueba si existe un tipo de material registrado con el nombre
     * indicado.
     *
     * @param nombre
     * @return
     *
     * @throws SQLException
     *
     * @author Naya Ruiz
     */
    public static boolean existeMaterial(String nombre) throws SQLException {
        // Variables
        boolean resultado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String s = "SELECT * FROM datos_material WHERE nombre = ?";
        Connection con = AccesoBaseDatos.getInstance().getConn();
        try {
            // Preparamos la sentencia con los datos del vehiculo
            ps = con.prepareStatement(s);
            ps.setString(1, nombre.trim());
            // Ejecutamos la sentencia.
            rs = ps.executeQuery();
            // Si la sentencia se ejecuta correctamente, devolvemos true
            if (rs.next()) {
                resultado = true;
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    /**
     * Obtiene los materiales necesarios para generar el archivo JSON del
     * inventario.
     *
     * La consulta obtiene información relacionada con:
     *
     * - Materiales. 
     * - Cantidades. 
     * - Armarios. 
     * - Baldas. 
     * - Cajones.
     *
     * @param con
     * @return
     *
     * @throws SQLException
     *
     * @author Saúl Valdunciel
     */
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
