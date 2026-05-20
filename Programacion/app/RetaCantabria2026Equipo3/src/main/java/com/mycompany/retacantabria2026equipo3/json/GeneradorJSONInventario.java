package com.mycompany.retacantabria2026equipo3.json;

import com.mycompany.retacantabria2026equipo3.DAOs.AccesoBaseDatos;
import com.mycompany.retacantabria2026equipo3.DAOs.MaterialDAO;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase encargada de generar el archivo JSON del inventario a partir de los
 * datos almacenados en la base de datos.
 *
 * Además de generar el JSON, también se encarga de:
 *
 * - Obtener los materiales desde la base de datos. - Convertir los
 * identificadores internos de armarios, baldas y cajones a un formato legible.
 * - Escapar caracteres especiales para evitar errores JSON. - Subir
 * automáticamente el archivo al servidor mediante SCP. - Copiar el JSON al
 * directorio de Apache del servidor web.
 *
 * El archivo generado es utilizado posteriormente por la página web del
 * inventario.
 *
 * @author Saúl Valdunciel
 */
public class GeneradorJSONInventario {

    /**
     * Genera el archivo inventario.json utilizando los materiales obtenidos
     * desde la base de datos.
     *
     * El método recorre todos los materiales y construye manualmente la
     * estructura JSON que será utilizada por la página web del proyecto.
     *
     * Una vez generado el archivo:
     *
     * - Se sube automáticamente al servidor EC2. - Se copia al directorio de
     * Apache para su publicación.
     *
     * @author Saúl Valdunciel
     */
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

    /**
     * Convierte el identificador numérico del armario almacenado en la base de
     * datos a un formato legible.
     *
     * Ejemplos:
     *
     * 11 -> A1 12 -> A2 0 -> GENERAL
     *
     * @param armarioBD
     * @return
     *
     * @author Saúl Valdunciel
     */
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

    /**
     * Convierte el identificador numérico de la balda a un formato legible para
     * el JSON.
     *
     * Ejemplos:
     *
     * 2001 -> B001 2201 -> B201
     *
     * @param armarioBD
     * @param baldaBD
     * @return
     *
     * @author Saúl Valdunciel
     */
    private String convertirBalda(int armarioBD, int baldaBD) {

        if (armarioBD == 0) {
            // GENERAL: 2001 -> B001, 2016 -> B016
            if (baldaBD >= 2000 && baldaBD < 3000) {
                baldaBD = baldaBD - 2000;
            }

            return String.format("B%03d", baldaBD);
        }

        // A2: 2201 -> B201, 2202 -> B202
        if (baldaBD >= 2000) {
            baldaBD = baldaBD - 2000;
        }

        return String.format("B%03d", baldaBD);
    }

    /**
     * Convierte el identificador numérico del cajón a un formato legible para
     * el JSON.
     *
     * Ejemplo:
     *
     * 301603 -> C01603
     *
     * @param armarioBD
     * @param baldaBD
     * @param cajonBD
     * @return
     *
     * @author Saúl Valdunciel
     */
    private String convertirCajon(int armarioBD, int baldaBD, int cajonBD) {

        if (cajonBD == 0) {
            return "";
        }

        // 301603 -> 1603
        if (cajonBD >= 300000) {
            cajonBD = cajonBD - 300000;
        }

        return String.format("C%05d", cajonBD);
    }

    /**
     * Escapa caracteres especales para evitar errores de formato dentro del
     * archivo JSON.
     *
     * Sustituye caracteres como barras y comillas dobles por sus equivalentes
     * escapados.
     *
     * @param texto
     * @return
     *
     * @author Saúl Valdunciel
     */
    private String escaparJSON(String texto) {

        if (texto == null) {
            return "";
        }

        return texto.replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    /**
     * Sube el archivo inventario json al servidor EC2 mediante el protocolo
     * SCP.
     *
     * Una vez subido correctamente, llama automáticamente al método encargado
     * de copiar el archivo al directorio de Apache.
     *
     * @author Saúl Valdunciel
     */
    private void subirJSONServidor() {

        try {

            String rutaPem = "Reto.pem";
            String rutaJson = "inventario.json";

            ProcessBuilder pb = new ProcessBuilder(
                    "scp",
                    "-i",
                    rutaPem,
                    rutaJson,
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

    /**
     * Copia el archivo inventario.json al directorio utilizado por Apache para
     * servir la página web.
     *
     * La copia se realiza remotamente mediante SSH.
     *
     * @author Saúl Valdunciel
     */
    private void copiarJSONApache() {

        try {

            String rutaPem = "Reto.pem";

            ProcessBuilder pb = new ProcessBuilder(
                    "ssh",
                    "-i",
                    rutaPem,
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

}
