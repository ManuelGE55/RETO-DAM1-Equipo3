package com.mycompany.retacantabria2026equipo3.DAOs;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

import com.mycompany.retacantabria2026equipo3.modelos.administracionmateriales.Material;
import com.mycompany.retacantabria2026equipo3.enums.Categoria;
import com.mycompany.retacantabria2026equipo3.enums.Estado;

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
        
        PreparedStatement ps=null;
        ResultSet resultado=null;
        
        String select="SELECT id_material,nombre,descripcion,cantidad,stock_minimo,categoria,estado FROM material";
        
        try(Connection conn = AccesoBaseDatos.getInstance().getConn();){
            ps=conn.prepareStatement(select);
            resultado=ps.executeQuery();
            
            while(resultado.next()){
            
                listaMateriales.add(new Material(
                        
                    resultado.getString(1),                     //Nombre
                    resultado.getString(2),                     //Descripción
                    resultado.getInt(3),                        //Cantidad
                    resultado.getInt(4),                        //StockMinimo
                    Categoria.valueOf(resultado.getString(5)),  //Categoria
                    Estado.valueOf(resultado.getString(6)),     //Estado
                    resultado.getString(7)                      //IdUbicacion
                        
                ));
            }
        }
        catch(SQLException e){}
        
        return listaMateriales;
    }
}
