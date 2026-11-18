/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Comparator;

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

    public void destruirMonstruo(Monstruo m) {
        if (campo.remove(m)) {
            m.setDestruido(true);
            cementerio.add(m);
        }
    }

    public ArrayList<Monstruo> reporteMonstruosPorATQ() {
        ArrayList<Monstruo> copia = new ArrayList<>(campo);
        copia.sort(Comparator.comparingInt(Monstruo::getAtq).reversed());
        return copia;
    }

    /**
     * Reporte 2: monstruos destruidos (cementerio).
     */
    public ArrayList<Monstruo> reporteMonstruosDestruidos() {
        ArrayList<Monstruo> destruidos = new ArrayList<>();
        for (Monstruo m : cementerio) {
            if (m.isDestruido()) {
                destruidos.add(m);
            }
        }
        return destruidos;
    }

    @Override
    public String toString() {
        return nombre + " (LP: " + lp + ", monstruos en campo: " + campo.size() + ")";
    }

}