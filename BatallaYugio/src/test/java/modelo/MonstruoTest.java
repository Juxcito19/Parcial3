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

public class MonstruoTest {

    @Test
    public void dañoContraMonstruoEnAtaque() {
        Monstruo a = new Monstruo("Mago Oscuro", 2500, 2100, Monstruo.ATAQUE);
        Monstruo o = new Monstruo("Guerrero", 1800, 1000, Monstruo.ATAQUE);
        assertEquals(700, a.calcularDaño(o));
    }

    @Test
    public void dañoContraMonstruoEnDefensa() {
        Monstruo a = new Monstruo("Mago Oscuro", 2500, 2100, Monstruo.ATAQUE);
        Monstruo o = new Monstruo("Muro", 500, 2000, Monstruo.DEFENSA);
        assertEquals(500, a.calcularDaño(o));
    }

    @Test
    public void dañoNegativoCuandoElObjetivoEsMasFuerte() {
        Monstruo a = new Monstruo("Débil", 1000, 500, Monstruo.ATAQUE);
        Monstruo o = new Monstruo("Fuerte", 1800, 1000, Monstruo.ATAQUE);
        assertEquals(-800, a.calcularDaño(o));
    }

    @Test
    public void monstruoEfectoSumaBonusAlAtaque() {
        MonstruoEfec e = new MonstruoEfec("Dragón", 1000, 800, Monstruo.ATAQUE, "Bonus", 500);
        Monstruo o = new Monstruo("Rival", 1000, 500, Monstruo.ATAQUE);
        assertEquals(1500, e.getAtq());
        assertEquals(500, e.calcularDaño(o));
    }
}
