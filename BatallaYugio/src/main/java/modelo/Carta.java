/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public abstract class Carta {

    protected String nombre;
    public static int totalCartas = 0;

    public Carta(String nombre) {
        this.nombre = nombre;
        totalCartas++;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String descripcion();

}