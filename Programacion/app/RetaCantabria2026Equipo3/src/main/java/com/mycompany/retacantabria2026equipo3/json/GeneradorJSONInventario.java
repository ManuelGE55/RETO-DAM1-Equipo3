package com.mycompany.retacantabria2026equipo3.json;

import com.mycompany.retacantabria2026equipo3.DAOs.AccesoBaseDatos;
import com.mycompany.retacantabria2026equipo3.DAOs.MaterialDAO;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneradorJSONInventario {

    public void generarJSONInventario() {
        String rutaArchivo = "inventario.json";

        try (Connection con = AccesoBaseDatos.getInstance().getConn(); ResultSet rs = MaterialDAO.obtenerMaterialesParaJSON(con); FileWriter writer = new FileWriter(rutaArchivo)) {

            writer.write("[\n");

            boolean primero = true;

            while (rs.next()) {
                if (!primero) {
                    writer.write(",\n");
                }

                int armarioBD = rs.getInt("armario");
                int baldaBD = rs.getInt("balda");
                int cajonBD = rs.getInt("cajon");

                String armario = convertirArmario(armarioBD);
                String balda = convertirBalda(armarioBD, baldaBD);
                String cajon = convertirCajon(armarioBD, baldaBD, cajonBD);

                writer.write("  {\n");
                writer.write("    \"idMaterial\": " + rs.getInt("id_material") + ",\n");
                writer.write("    \"nombre\": \"" + escaparJSON(rs.getString("nombre")) + "\",\n");
                writer.write("    \"cantidad\": " + rs.getInt("cantidad") + ",\n");
                writer.write("    \"armario\": \"" + armario + "\",\n");
                writer.write("    \"balda\": \"" + balda + "\",\n");
                writer.write("    \"cajon\": \"" + cajon + "\",\n");
                writer.write("    \"descripcion\": \"" + escaparJSON(rs.getString("descripcion")) + "\"\n");
                writer.write("  }");

                primero = false;
            }

            writer.write("\n]");

            System.out.println("inventario.json generado correctamente.");

        } catch (SQLException | IOException e) {
            System.out.println("Error generando JSON: " + e.getMessage());
        }
    }

    private String convertirArmario(int armarioBD) {
        if (armarioBD == 0) {
            return "GENERAL";
        }

        // En la BD los armarios tienen id_ubicacion 11, 12, 13, 14, 15
        // 11 -> A1, 12 -> A2, etc.
        if (armarioBD >= 11 && armarioBD <= 15) {
            return "A" + (armarioBD - 10);
        }

        return "A" + armarioBD;
    }

    private String convertirBalda(int armarioBD, int baldaBD) {
        return String.format("B%03d", baldaBD);
    }

    private String convertirCajon(int armarioBD, int baldaBD, int cajonBD) {
        if (cajonBD == 0) {
            return "";
        }

        return String.format("C%05d", cajonBD);
    }

    private String escaparJSON(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    public static void main(String[] args) {

        GeneradorJSONInventario generador = new GeneradorJSONInventario();

        generador.generarJSONInventario();

    }
}
