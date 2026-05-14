package com.mycompany.retacantabria2026equipo3.DAOs;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import com.mycompany.retacantabria2026equipo3.enums.Categoria;
import com.mycompany.retacantabria2026equipo3.enums.Estado;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

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
    public static LinkedList<Material> cargarInventario(){
        
        //Variables
        LinkedList<Material>listaMateriales=new LinkedList<>();
        
        
        String select="SELECT id_material,nombre,descripcion,cantidad,stock_minimo,categoria,estado,id_Ubicacion FROM material";
        
        try(Connection conn = AccesoBaseDatos.getInstance().getConn();PreparedStatement ps=conn.prepareStatement(select);ResultSet resultado=ps.executeQuery();){
            
            while(resultado.next()){
            Material m=new Material( resultado.getString(2),resultado.getString(3),resultado.getInt(4),resultado.getInt(5),Categoria.valueOf(resultado.getString(6).toUpperCase()),Estado.valueOf(resultado.getString(7).toUpperCase()),resultado.getString(8));
                m.setId(resultado.getInt(1));
                listaMateriales.add(m);
            
            }
        }
        catch(SQLException e){}
        return listaMateriales;
    }
}
