/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class JugadorTest {

    @Test
    public void iniciaConLpInicial() {
        Jugador j = new Jugador("Yugi");
        assertEquals(8000, j.getLp());
    }

    @Test
    public void restarLpDescuentaCorrectamente() {
        Jugador j = new Jugador("Yugi");
        j.restarLp(1500);
        assertEquals(6500, j.getLp());
    }

    @Test
    public void lpNuncaBajaDeCero() {
        Jugador j = new Jugador("Yugi");
        j.restarLp(9000);
        assertEquals(0, j.getLp());
        assertTrue(j.estaDerrotado());
    }

    @Test
    public void noEstaDerrotadoConLpPositivo() {
        Jugador j = new Jugador("Yugi");
        j.restarLp(7999);
        assertFalse(j.estaDerrotado());
    }

    @Test
    public void destruirMonstruoLoMueveAlCementerio() {
        Jugador j = new Jugador("Yugi");
        Monstruo m = new Monstruo("Mago", 2500, 2100, Monstruo.ATAQUE);
        j.agregarMonstruo(m);
        j.destruirMonstruo(m);
        assertTrue(j.getCampo().isEmpty());
        assertEquals(1, j.getCementerio().size());
        assertTrue(m.isDestruido());
    }

    @Test
    public void reportePorAtqOrdenaDeMayorAMenor() {
        Jugador j = new Jugador("Yugi");
        j.agregarMonstruo(new Monstruo("A", 1000, 500, Monstruo.ATAQUE));
        j.agregarMonstruo(new Monstruo("B", 3000, 500, Monstruo.ATAQUE));
        j.agregarMonstruo(new Monstruo("C", 2000, 500, Monstruo.ATAQUE));
        ArrayList<Monstruo> r = j.reporteMonstruosPorATQ();
        assertEquals("B", r.get(0).getNombre());
        assertEquals("C", r.get(1).getNombre());
        assertEquals("A", r.get(2).getNombre());
    }

    @Test
    public void reporteDestruidosSoloIncluyeLosDestruidos() {
        Jugador j = new Jugador("Yugi");
        Monstruo a = new Monstruo("A", 1000, 500, Monstruo.ATAQUE);
        Monstruo b = new Monstruo("B", 2000, 500, Monstruo.ATAQUE);
        j.agregarMonstruo(a);
        j.agregarMonstruo(b);
        j.destruirMonstruo(a);
        ArrayList<Monstruo> r = j.reporteMonstruosDestruidos();
        assertEquals(1, r.size());
        assertEquals("A", r.get(0).getNombre());
    }
}