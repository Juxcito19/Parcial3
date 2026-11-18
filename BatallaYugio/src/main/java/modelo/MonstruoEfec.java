/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class MonstruoEfec extends Monstruo {

    private String efecto;
    private int bonusAtq;

    public MonstruoEfec(String nombre, int atq, int def, String posicion,
                        String efecto, int bonusAtq) {
        super(nombre, atq, def, posicion);
        this.efecto = efecto;
        this.bonusAtq = bonusAtq;
    }

    public String getEfecto() {
        return efecto;
    }

    @Override
    public int getAtq() {
        return atq + bonusAtq;
    }

    @Override
    public String descripcion() {
        return "Monstruo de efecto " + nombre + ": " + efecto;
    }

    @Override
    public String toString() {
        return nombre + " [ATQ " + getAtq() + " / DEF " + def + " / " + posicion + "] Efecto: " + efecto;
    }

}