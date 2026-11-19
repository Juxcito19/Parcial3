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
    public void tieneMonstruosEnAtaqueSoloCuentaLosDeAtaque() {
        Jugador j = new Jugador("Yugi");
        assertFalse(j.tieneMonstruosEnAtaque());
        j.agregarMonstruo(new Monstruo("Muro", 500, 2000, Monstruo.DEFENSA));
        assertFalse(j.tieneMonstruosEnAtaque());
        j.agregarMonstruo(new Monstruo("Mago", 2500, 2100, Monstruo.ATAQUE));
        assertTrue(j.tieneMonstruosEnAtaque());
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
}