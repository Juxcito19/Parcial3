/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

/**
 *
 * @author Usuario
 */
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import modelo.Jugador;
import modelo.Monstruo;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void reportePorAtaqueOrdenaDeMayorAMenor() {
        ArrayList<Monstruo> lista = new ArrayList<>();
        lista.add(new Monstruo("A", 1000, 500, Monstruo.ATAQUE));
        lista.add(new Monstruo("B", 3000, 500, Monstruo.ATAQUE));
        lista.add(new Monstruo("C", 2000, 500, Monstruo.ATAQUE));
        ArrayList<Monstruo> r = Main.reportePorAtaque(lista);
        assertEquals("B", r.get(0).getNombre());
        assertEquals("C", r.get(1).getNombre());
        assertEquals("A", r.get(2).getNombre());
        // La lista original no cambia
        assertEquals("A", lista.get(0).getNombre());
    }

    @Test
    public void reporteDestruidosSoloIncluyeLosDestruidos() {
        Jugador j = new Jugador("Yugi");
        Monstruo a = new Monstruo("A", 1000, 500, Monstruo.ATAQUE);
        Monstruo b = new Monstruo("B", 2000, 500, Monstruo.ATAQUE);
        j.agregarMonstruo(a);
        j.agregarMonstruo(b);
        j.destruirMonstruo(a);
        ArrayList<Monstruo> r = Main.reporteDestruidos(j.getCementerio());
        assertEquals(1, r.size());
        assertEquals("A", r.get(0).getNombre());
    }
}
