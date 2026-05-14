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

        try (
                Connection con = AccesoBaseDatos.getInstance().getConn(); ResultSet rs = MaterialDAO.obtenerMaterialesParaJSON(con); FileWriter writer = new FileWriter(rutaArchivo)) {

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

            writer.flush();

            System.out.println("inventario.json generado correctamente.");

        } catch (SQLException | IOException e) {
            System.out.println("Error generando JSON: " + e.getMessage());
            return;
        }

        subirJSONServidor();
    }

    private String convertirArmario(int armarioBD) {

        if (armarioBD == 0) {
            return "GENERAL";
        }

        // 11 -> A1
        // 12 -> A2
        // etc.
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

    private void subirJSONServidor() {

        try {

            ProcessBuilder pb = new ProcessBuilder(
                    "scp",
                    "-i",
                    "D:\\Usuarios\\DAM127\\Downloads\\Reto.pem",
                    "D:\\Usuarios\\DAM127\\Documents\\GitHub\\RETO-DAM1-Equipo3\\Programacion\\app\\RetaCantabria2026Equipo3\\inventario.json",
                    "ubuntu@52.44.197.21:/home/ubuntu/"
            );

            pb.inheritIO();

            Process proceso = pb.start();

            int resultado = proceso.waitFor();

            if (resultado == 0) {

                System.out.println("inventario.json subido correctamente.");

                copiarJSONApache();

            } else {

                System.out.println("Error al subir inventario.json.");
            }

        } catch (Exception e) {

            System.out.println("Error ejecutando SCP: " + e.getMessage());
        }
    }

    private void copiarJSONApache() {

        try {

            ProcessBuilder pb = new ProcessBuilder(
                    "ssh",
                    "-i",
                    "D:\\Usuarios\\DAM127\\Downloads\\Reto.pem",
                    "ubuntu@52.44.197.21",
                    "sudo cp /home/ubuntu/inventario.json /var/www/html/"
            );

            pb.inheritIO();

            Process proceso = pb.start();

            int resultado = proceso.waitFor();

            if (resultado == 0) {

                System.out.println("inventario.json copiado a Apache correctamente.");

            } else {

                System.out.println("Error copiando inventario.json a Apache.");
            }

        } catch (Exception e) {

            System.out.println("Error ejecutando SSH: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        GeneradorJSONInventario generador = new GeneradorJSONInventario();

        generador.generarJSONInventario();
    }
}
