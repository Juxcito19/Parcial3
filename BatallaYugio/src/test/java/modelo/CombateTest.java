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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CombateTest {

    private Jugador j1;
    private Jugador j2;
    private Combate combate;

    @BeforeEach
    public void preparar() {
        j1 = new Jugador("Yugi");
        j2 = new Jugador("Kaiba");
        combate = new Combate(j1, j2);
    }

    @Test
    public void sobrecargaAtaqueDirectoDevuelveElAtq() {
        Monstruo a = new Monstruo("Mago", 2500, 2100, Monstruo.ATAQUE);
        assertEquals(2500, combate.calcularDaño(a));
    }

    @Test
    public void sobrecargaContraMonstruoDevuelveLaDiferencia() {
        Monstruo a = new Monstruo("Mago", 2500, 2100, Monstruo.ATAQUE);
        Monstruo o = new Monstruo("Guerrero", 1800, 1000, Monstruo.ATAQUE);
        assertEquals(700, combate.calcularDaño(a, o));
    }

    @Test
    public void atacanteGanaEnAtaque() {
        Monstruo a = new Monstruo("Mago", 2500, 2100, Monstruo.ATAQUE);
        Monstruo o = new Monstruo("Guerrero", 1800, 1000, Monstruo.ATAQUE);
        j1.agregarMonstruo(a);
        j2.agregarMonstruo(o);
        combate.resolverAtaque(a, o);
        assertEquals(7300, j2.getLp());
        assertEquals(8000, j1.getLp());
        assertTrue(o.isDestruido());
        assertFalse(a.isDestruido());
    }

    @Test
    public void atacantePierdeEnAtaque() {
        Monstruo a = new Monstruo("Débil", 1000, 500, Monstruo.ATAQUE);
        Monstruo o = new Monstruo("Fuerte", 1800, 1000, Monstruo.ATAQUE);
        j1.agregarMonstruo(a);
        j2.agregarMonstruo(o);
        combate.resolverAtaque(a, o);
        assertEquals(7200, j1.getLp());
        assertEquals(8000, j2.getLp());
        assertTrue(a.isDestruido());
    }

    @Test
    public void empateDestruyeAmbosSinDañoALp() {
        Monstruo a = new Monstruo("A", 1500, 500, Monstruo.ATAQUE);
        Monstruo o = new Monstruo("B", 1500, 500, Monstruo.ATAQUE);
        j1.agregarMonstruo(a);
        j2.agregarMonstruo(o);
        combate.resolverAtaque(a, o);
        assertTrue(a.isDestruido());
        assertTrue(o.isDestruido());
        assertEquals(8000, j1.getLp());
        assertEquals(8000, j2.getLp());
    }

    @Test
    public void ataqueSuperaDefensaDestruyeSinDañoALp() {
        Monstruo a = new Monstruo("Mago", 2500, 2100, Monstruo.ATAQUE);
        Monstruo o = new Monstruo("Muro", 500, 2000, Monstruo.DEFENSA);
        j1.agregarMonstruo(a);
        j2.agregarMonstruo(o);
        combate.resolverAtaque(a, o);
        assertTrue(o.isDestruido());
        assertEquals(8000, j2.getLp());
    }

    @Test
    public void ataqueMenorQueDefensaDescuentaLpAlAtacante() {
        Monstruo a = new Monstruo("Débil", 1000, 500, Monstruo.ATAQUE);
        Monstruo o = new Monstruo("Muro", 500, 2000, Monstruo.DEFENSA);
        j1.agregarMonstruo(a);
        j2.agregarMonstruo(o);
        combate.resolverAtaque(a, o);
        assertEquals(7000, j1.getLp());
        assertFalse(o.isDestruido());
    }

    @Test
    public void ataqueDirectoConCampoVacioDescuentaLp() {
        Monstruo a = new Monstruo("Mago", 2500, 2100, Monstruo.ATAQUE);
        j1.agregarMonstruo(a);
        combate.resolverAtaqueDirecto(a);
        assertEquals(5500, j2.getLp());
    }

    @Test
    public void ataqueDirectoBloqueadoSiHayMonstruosEnElCampo() {
        Monstruo a = new Monstruo("Mago", 2500, 2100, Monstruo.ATAQUE);
        j1.agregarMonstruo(a);
        j2.agregarMonstruo(new Monstruo("Escudo", 500, 500, Monstruo.DEFENSA));
        combate.resolverAtaqueDirecto(a);
        assertEquals(8000, j2.getLp());
    }
}