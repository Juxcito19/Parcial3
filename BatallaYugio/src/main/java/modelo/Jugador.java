/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Jugador {

    public static final int LP_INICIAL = 8000;

    private String nombre;
    private int lp;
    private ArrayList<Monstruo> campo;
    private ArrayList<Monstruo> cementerio;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.lp = LP_INICIAL;
        this.campo = new ArrayList<>();
        this.cementerio = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = Math.max(lp, 0);
    }

    public ArrayList<Monstruo> getCampo() {
        return campo;
    }

    public ArrayList<Monstruo> getCementerio() {
        return cementerio;
    }

    public void agregarMonstruo(Monstruo m) {
        campo.add(m);
    }

    public void restarLp(int cantidad) {
        setLp(lp - cantidad);
    }

    public boolean estaDerrotado() {
        return lp <= 0;
    }

    /**
     * Solo los monstruos en posición de ATAQUE pueden atacar.
     */
    public boolean tieneMonstruosEnAtaque() {
        for (Monstruo m : campo) {
            if (m.getPosicion().equals(Monstruo.ATAQUE)) {
                return true;
            }
        }
        return false;
    }

    public void destruirMonstruo(Monstruo m) {
        if (campo.remove(m)) {
            m.setDestruido(true);
            cementerio.add(m);
        }
    }

    @Override
    public String toString() {
        return nombre + " (LP: " + lp + ", monstruos en campo: " + campo.size() + ")";
    }

}