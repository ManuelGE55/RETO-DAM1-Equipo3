package com.mycompany.retacantabria2026equipo3.DAOs;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import com.mycompany.retacantabria2026equipo3.enums.Categoria;
import com.mycompany.retacantabria2026equipo3.enums.Estado;
import java.util.HashSet;

/**
 *
 * @author Manuel González Encinas
 */
public class InventarioDAO {
    
    //==========================================================================
    //ESTE DAO ESTA ASIGNADO A : MANUEL
    //==========================================================================
    
    
    //Este método crea un arrayList con todos los materiales de la base de datos
    //Después devuelve el arrayList
    public static ArrayList<Material> cargarInventario(){
        
        //Variables
        ArrayList<Material>listaMateriales=new ArrayList<>();
        
        
        String select="""
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
        
        try(Connection conn = AccesoBaseDatos.getInstance().getConn();PreparedStatement ps=conn.prepareStatement(select);ResultSet resultado=ps.executeQuery();){
            
            while(resultado.next()){
            Material m=new Material(resultado.getString(2),resultado.getString(3),Categoria.valueOf(resultado.getString(4).toUpperCase()),Estado.valueOf(resultado.getString(5).toUpperCase()),resultado.getString(6));
                m.setId(resultado.getInt(1));
                listaMateriales.add(m);
            
            }
        }
        catch(SQLException e){
            e.getSQLState();
        }
        return listaMateriales;
    }
}
