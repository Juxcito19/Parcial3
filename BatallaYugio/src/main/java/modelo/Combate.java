/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Combate {

    private Jugador atacante;
    private Jugador defensor;

    public Combate(Jugador atacante, Jugador defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
    }

    public Jugador getAtacante() {
        return atacante;
    }

    public Jugador getDefensor() {
        return defensor;
    }

    /**
     * Sobrecarga 1: ataque de un monstruo contra otro monstruo.
     * Devuelve la diferencia (puede ser negativa).
     */
    public int calcularDaño(Monstruo atacante, Monstruo objetivo) {
        return atacante.calcularDaño(objetivo);
    }

    /**
     * Sobrecarga 2: ataque directo, el daño es el ATQ completo.
     */
    public int calcularDaño(Monstruo atacante) {
        return atacante.getAtq();
    }

    /**
     * Resuelve un ataque contra un monstruo y devuelve un texto
     * con lo que pasó, para mostrarlo en la vista y guardarlo en el historial.
     */
    public String resolverAtaque(Monstruo atacante, Monstruo objetivo) {
        int diferencia = calcularDaño(atacante, objetivo);
        StringBuilder sb = new StringBuilder();
        sb.append(atacante.getNombre()).append(" ataca a ").append(objetivo.getNombre()).append(". ");

        if (objetivo.getPosicion().equals(Monstruo.ATAQUE)) {
            if (diferencia > 0) {
                defensor.destruirMonstruo(objetivo);
                defensor.restarLp(diferencia);
                sb.append(objetivo.getNombre()).append(" destruido. ")
                  .append(defensor.getNombre()).append(" pierde ").append(diferencia).append(" LP.");
            } else if (diferencia < 0) {
                this.atacante.destruirMonstruo(atacante);
                this.atacante.restarLp(-diferencia);
                sb.append(atacante.getNombre()).append(" destruido. ")
                  .append(this.atacante.getNombre()).append(" pierde ").append(-diferencia).append(" LP.");
            } else {
                this.atacante.destruirMonstruo(atacante);
                defensor.destruirMonstruo(objetivo);
                sb.append("Ambos monstruos destruidos.");
            }
        } else {
            if (diferencia > 0) {
                defensor.destruirMonstruo(objetivo);
                sb.append(objetivo.getNombre()).append(" (defensa) destruido. Sin daño a los LP.");
            } else if (diferencia < 0) {
                this.atacante.restarLp(-diferencia);
                sb.append("El ataque falla. ").append(this.atacante.getNombre())
                  .append(" pierde ").append(-diferencia).append(" LP.");
            } else {
                sb.append("Empate: no se destruye ningún monstruo.");
            }
        }
        return sb.toString();
    }

    public String resolverAtaqueDirecto(Monstruo atacante) {
        if (!defensor.getCampo().isEmpty()) {
            return "No se puede atacar directo: " + defensor.getNombre() + " tiene monstruos en el campo.";
        }
        int daño = calcularDaño(atacante);
        defensor.restarLp(daño);
        return atacante.getNombre() + " ataca directo. " + defensor.getNombre()
                + " pierde " + daño + " LP.";
    }

}