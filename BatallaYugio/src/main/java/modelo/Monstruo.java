/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Monstruo extends Carta implements Atacable {

    public static final String ATAQUE = "ATAQUE";
    public static final String DEFENSA = "DEFENSA";

    protected int atq;
    protected int def;
    protected String posicion;
    protected boolean destruido;

    public Monstruo(String nombre, int atq, int def, String posicion) {
        super(nombre);
        this.atq = atq;
        this.def = def;
        this.posicion = posicion;
        this.destruido = false;
    }

    public int getAtq() {
        return atq;
    }

    public int getDef() {
        return def;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public boolean isDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    @Override
    public int calcularDaño(Monstruo objetivo) {
        int valorObjetivo = objetivo.getPosicion().equals(ATAQUE)
                ? objetivo.getAtq()
                : objetivo.getDef();
        return getAtq() - valorObjetivo;
    }

    @Override
    public String descripcion() {
        return "Monstruo " + nombre + " (ATQ " + atq + " / DEF " + def + ")";
    }

    @Override
    public String toString() {
        return nombre + " [ATQ " + atq + " / DEF " + def + " / " + posicion + "]";
    }

}