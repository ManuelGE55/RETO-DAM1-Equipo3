/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retacantabria2026equipo3.gestores;

import com.mycompany.retacantabria2026equipo3.exceptions.ArgumentoNoEncontradoException;
import com.mycompany.retacantabria2026equipo3.exceptions.DriverConexionException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Clase encargada de gestionar la visualización de ubicaciones físicas del
 * inventario mediante una página web interactiva.
 *
 * Utiliza Selenium WebDriver y JavaScriptExecutor para controlar
 * automáticamente la web de localizaciones del proyecto.
 *
 * Permite mostrar:
 *
 * - Armarios 
 * - Baldas 
 * - Cajones
 *
 * en función de la ubicación del material.
 *
 * @author Hugo Fernández
 */
public class GestorLocalizaciones {

    /**
     * Atributo estático que manejará el driver de la web y dará acceso
     */
    private static WebDriver driver;

    /**
     * Atributo estático que el driver ayudará a entender y ejecutar las
     * funciones de JavaScript de la web
     */
    private static JavascriptExecutor js;

    /**
     * Atributo estático final que representa la url de la web
     */
    private static final String URL_WEB = "http://52.44.197.21/Mapa.html";

    static {
        try {
            String rutaProyecto = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", rutaProyecto + "\\src\\main\\resources\\dependencias\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            js = (JavascriptExecutor) driver;

            driver.get(URL_WEB);
        } catch (Exception e) {
            js = null;
            e.printStackTrace();
        }
    }

    /**
     * Muestra en la página web la ubicación correspondiente a un material.
     *
     * Dependiente del identificador recibido, se abrirá automáticamente:
     *
     * - Un armario. 
     * - Una balda. 
     * - Un cajón.
     *
     * utilizando funciones JavaScript ejecutadas desde Selenium.
     *
     * @author Hugo Fernández
     */
    public static void mostrarUbicacionWeb(String idUbicacion) throws ArgumentoNoEncontradoException, DriverConexionException {

        if (js == null) {
            throw new DriverConexionException("Error: JavaScriptExecutor es nulo.");
        }

        switch (idUbicacion) {

            // ARMARIOS
            case "11" -> {
                js.executeScript("verArmario('A1');");
            }

            case "12" -> {
                js.executeScript("verArmario('A2');");
            }

            case "13" -> {
                js.executeScript("verArmario('A3');");
            }

            case "14" -> {
                js.executeScript("verArmario('A4');");
            }

            case "15" -> {
                js.executeScript("verArmario('A5');");
            }

            // BALDAS DEL GENERAL
            case "2001" -> {
                js.executeScript("verBalda('GENERAL', 'B001');");
            }

            case "2002" -> {
                js.executeScript("verBalda('GENERAL', 'B002');");
            }

            case "2003" -> {
                js.executeScript("verBalda('GENERAL', 'B003');");
            }

            case "2004" -> {
                js.executeScript("verBalda('GENERAL', 'B004');");
            }

            case "2005" -> {
                js.executeScript("verBalda('GENERAL', 'B005');");
            }

            case "2006" -> {
                js.executeScript("verBalda('GENERAL', 'B006');");
            }

            case "2007" -> {
                js.executeScript("verBalda('GENERAL', 'B007');");
            }

            case "2008" -> {
                js.executeScript("verBalda('GENERAL', 'B008');");
            }

            case "2009" -> {
                js.executeScript("verBalda('GENERAL', 'B009');");
            }

            case "2010" -> {
                js.executeScript("verBalda('GENERAL', 'B010');");
            }

            case "2011" -> {
                js.executeScript("verBalda('GENERAL', 'B011');");
            }

            case "2012" -> {
                js.executeScript("verBalda('GENERAL', 'B012');");
            }

            case "2013" -> {
                js.executeScript("verBalda('GENERAL', 'B013');");
            }

            case "2014" -> {
                js.executeScript("verBalda('GENERAL', 'B014');");
            }

            case "2015" -> {
                js.executeScript("verBalda('GENERAL', 'B015');");
            }

            case "2016" -> {
                js.executeScript("verBalda('GENERAL', 'B016');");
            }

            case "2017" -> {
                js.executeScript("verBalda('GENERAL', 'B017');");
            }

            case "2018" -> {
                js.executeScript("verBalda('GENERAL', 'B018');");
            }

            // BALDAS DE ARMARIO A1
            case "2101" -> {
                js.executeScript("verBalda('A1', 'B101');");
            }

            case "2102" -> {
                js.executeScript("verBalda('A1', 'B102');");
            }

            case "2103" -> {
                js.executeScript("verBalda('A1', 'B103');");
            }

            case "2104" -> {
                js.executeScript("verBalda('A1', 'B104');");
            }

            case "2105" -> {
                js.executeScript("verBalda('A1', 'B105');");
            }

            case "2106" -> {
                js.executeScript("verBalda('A1', 'B106');");
            }

            case "2107" -> {
                js.executeScript("verBalda('A1', 'B107');");
            }

            case "2108" -> {
                js.executeScript("verBalda('A1', 'B108');");
            }

            case "2109" -> {
                js.executeScript("verBalda('A1', 'B109');");
            }

            case "2110" -> {
                js.executeScript("verBalda('A1', 'B110');");
            }

            case "2111" -> {
                js.executeScript("verBalda('A1', 'B111');");
            }

            case "2112" -> {
                js.executeScript("verBalda('A1', 'B112');");
            }

            // BALDAS DE ARMARIO A2
            case "2201" -> {
                js.executeScript("verBalda('A2', 'B201');");
            }

            case "2202" -> {
                js.executeScript("verBalda('A2', 'B202');");
            }

            // BALDAS ARMARIO A3
            case "2301" -> {
                js.executeScript("verBalda('A3', 'B301');");
            }

            case "2302" -> {
                js.executeScript("verBalda('A3', 'B302');");
            }

            // BALDAS ARMARIO A4
            case "2401" -> {
                js.executeScript("verBalda('A4', 'B401');");
            }

            case "2402" -> {
                js.executeScript("verBalda('A4', 'B402');");
            }

            // BALDAS ARMARIO A5
            case "2501" -> {
                js.executeScript("verBalda('A5', 'B501');");
            }

            case "2502" -> {
                js.executeScript("verBalda('A5', 'B502');");
            }

            case "2503" -> {
                js.executeScript("verBalda('A5', 'B503');");
            }

            case "2504" -> {
                js.executeScript("verBalda('A5', 'B504');");
            }

            case "2505" -> {
                js.executeScript("verBalda('A5', 'B505');");
            }

            case "2506" -> {
                js.executeScript("verBalda('A5', 'B506');");
            }

            case "2507" -> {
                js.executeScript("verBalda('A5', 'B507');");
            }

            case "2508" -> {
                js.executeScript("verBalda('A5', 'B508');");
            }

            case "2509" -> {
                js.executeScript("verBalda('A5', 'B509');");
            }

            case "2510" -> {
                js.executeScript("verBalda('A5', 'B510');");
            }

            case "2511" -> {
                js.executeScript("verBalda('A5', 'B511');");
            }

            case "2512" -> {
                js.executeScript("verBalda('A5', 'B512');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B002
            case "300201" -> {
                js.executeScript("verCajon('GENERAL', 'B002', 'C00201');");
            }

            case "300202" -> {
                js.executeScript("verCajon('GENERAL', 'B002', 'C00202');");
            }

            case "300203" -> {
                js.executeScript("verCajon('GENERAL', 'B002', 'C00203');");
            }

            case "300204" -> {
                js.executeScript("verCajon('GENERAL', 'B002', 'C00204');");
            }

            case "300205" -> {
                js.executeScript("verCajon('GENERAL', 'B002', 'C00205');");
            }

            case "300206" -> {
                js.executeScript("verCajon('GENERAL', 'B002', 'C00206');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B003
            case "300301" -> {
                js.executeScript("verCajon('GENERAL', 'B003', 'C00301');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B006
            case "300601" -> {
                js.executeScript("verCajon('GENERAL', 'B006', 'C00601');");
            }

            case "300602" -> {
                js.executeScript("verCajon('GENERAL', 'B006', 'C00602');");
            }

            case "300603" -> {
                js.executeScript("verCajon('GENERAL', 'B006', 'C00603');");
            }

            case "300604" -> {
                js.executeScript("verCajon('GENERAL', 'B006', 'C00604');");
            }

            case "300605" -> {
                js.executeScript("verCajon('GENERAL', 'B006', 'C00605');");
            }

            case "300606" -> {
                js.executeScript("verCajon('GENERAL', 'B006', 'C00606');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B007
            case "300701" -> {
                js.executeScript("verCajon('GENERAL', 'B007', 'C00701');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B008
            case "300801" -> {
                js.executeScript("verCajon('GENERAL', 'B008', 'C00801');");
            }

            case "300802" -> {
                js.executeScript("verCajon('GENERAL', 'B008', 'C00802');");
            }

            case "300803" -> {
                js.executeScript("verCajon('GENERAL', 'B008', 'C00803');");
            }

            case "300804" -> {
                js.executeScript("verCajon('GENERAL', 'B008', 'C00804');");
            }

            case "300805" -> {
                js.executeScript("verCajon('GENERAL', 'B008', 'C00805');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B009
            case "300901" -> {
                js.executeScript("verCajon('GENERAL', 'B009', 'C00901');");
            }

            case "300902" -> {
                js.executeScript("verCajon('GENERAL', 'B009', 'C00902');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B010
            case "301001" -> {
                js.executeScript("verCajon('GENERAL', 'B010', 'C01001');");
            }

            case "301002" -> {
                js.executeScript("verCajon('GENERAL', 'B010', 'C01002');");
            }

            case "301003" -> {
                js.executeScript("verCajon('GENERAL', 'B010', 'C01003');");
            }

            case "301004" -> {
                js.executeScript("verCajon('GENERAL', 'B010', 'C01004');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B013
            case "301301" -> {
                js.executeScript("verCajon('GENERAL', 'B013', 'C01301');");
            }

            case "301302" -> {
                js.executeScript("verCajon('GENERAL', 'B013', 'C01302');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B014
            case "301401" -> {
                js.executeScript("verCajon('GENERAL', 'B014', 'C01401');");
            }

            case "301402" -> {
                js.executeScript("verCajon('GENERAL', 'B014', 'C01402');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B016
            case "301601" -> {
                js.executeScript("verCajon('GENERAL', 'B016', 'C01601');");
            }

            case "301602" -> {
                js.executeScript("verCajon('GENERAL', 'B016', 'C01602');");
            }

            case "301603" -> {
                js.executeScript("verCajon('GENERAL', 'B016', 'C01603');");
            }

            case "301604" -> {
                js.executeScript("verCajon('GENERAL', 'B016', 'C01604');");
            }

            case "301605" -> {
                js.executeScript("verCajon('GENERAL', 'B016', 'C01605');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B017
            case "301701" -> {
                js.executeScript("verCajon('GENERAL', 'B017', 'C01701');");
            }

            // CAJONES EN ARMARIO GENERAL BALDA B018
            case "301801" -> {
                js.executeScript("verCajon('GENERAL', 'B018', 'C01801');");
            }

            case "301802" -> {
                js.executeScript("verCajon('GENERAL', 'B018', 'C01802');");
            }

            default -> {
                throw new ArgumentoNoEncontradoException("Error: No existe la ubicación de la id insertada.");
            }
        }
    }

    /**
     * Cierra el navegador controlado por Selenium.
     *
     * @return
     *
     * @author Hugo Fernández
     */
    public static boolean cerrarWebDriver() {
        boolean cerrado = false;
        if (driver != null) {
            driver.quit();
            cerrado = true;
        }
        return cerrado;
    }
}
