package com.mycompany.retacantabria2026equipo3.DAOs;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import com.mycompany.retacantabria2026equipo3.enums.Categoria;
import com.mycompany.retacantabria2026equipo3.enums.Estado;
import java.util.HashSet;

/**
 * Clase DAO encargada de acceder a los datos del inventario almacenados en la
 * base de datos.
 *
 * Permite cargar los materiales registrados y convertirlos en objetos Material
 * para ser utilizados por la habitación.
 *
 *
 * @author Manuel González Encinas
 */
public class InventarioDAO {

    /**
     * Carga todos los materiales existentes en la base de datos.
     *
     * Realiza una consulta entre las tablas material y datos_naterial para
     * obtener la información completa de cada material.
     *
     * @return
     *
     * @author Manuel González
     */
    public static ArrayList<Material> cargarInventario() {

        //Variables
        ArrayList<Material> listaMateriales = new ArrayList<>();

        String select = """
                      SELECT 
                          id_material,
                          datos_material.nombre,
                          descripcion,
                          datos_material.categoria,
                          estado,
                          id_ubicacion
                      FROM material
                      INNER JOIN datos_material
                          ON material.nombre = datos_material.nombre;
                      """;

        try (Connection conn = AccesoBaseDatos.getInstance().getConn(); PreparedStatement ps = conn.prepareStatement(select); ResultSet resultado = ps.executeQuery();) {

            while (resultado.next()) {
                Material m = new Material(resultado.getString(2), resultado.getString(3), Categoria.valueOf(resultado.getString(4).toUpperCase()), Estado.valueOf(resultado.getString(5).toUpperCase()), resultado.getString(6));
                m.setId(resultado.getInt(1));
                listaMateriales.add(m);

            }
        } catch (SQLException e) {
            e.getSQLState();
        }
        return listaMateriales;
    }
}
