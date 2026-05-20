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
        
        //Este ArrayList contendrá los materiales que devuelva la consulta en la base de datos
        ArrayList<Material>listaMateriales=new ArrayList<>();
        
        //Este string contiene la consulta que se ejecutará en la base de datos
        //La consulta leerá todos los materiales (y su información) registrados en la base de datos
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
        
        //Aquí se empieza la conexión con la base de datos. Se abre un bloque try-catch para capturar la posible excepción de SQL.
        try(Connection conn = AccesoBaseDatos.getInstance().getConn();PreparedStatement ps=conn.prepareStatement(select);ResultSet resultado=ps.executeQuery();){
            
            //Bucle que lee todos los datos que devuelve la consulta
            while(resultado.next()){
            //Los resultados devueltos se insertan, por órden, dentro de un nuevo objeto Material
            Material m=new Material(resultado.getString(2),resultado.getString(3),Categoria.valueOf(resultado.getString(4).toUpperCase()),Estado.valueOf(resultado.getString(5).toUpperCase()),resultado.getString(6));
                //Se establece el id del material, y este se inserta en el arrayList.
                m.setId(resultado.getInt(1));
                listaMateriales.add(m);
            
            }
        }
        catch(SQLException e){  //Atrapar la excepción que podría saltar al conectarse a la base de datos
            e.getSQLState();
        }
        return listaMateriales; //Una vez se haya cerrado el bucle, se devuelve el arrayList con todos los materiales devueltos
    }
}
