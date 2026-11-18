/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class RegistroCombate {

    public static final String ARCHIVO = "historial_combate.txt";

    private String ruta;

    public RegistroCombate() {
        this.ruta = ARCHIVO;
    }

    public RegistroCombate(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public boolean registrar(String evento) {
        String hora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta, true))) {
            pw.println("[" + hora + "] " + evento);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public ArrayList<String> leerHistorial() {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            // Archivo inexistente o no legible: se devuelve lista vacía
        }
        return lineas;
    }

}