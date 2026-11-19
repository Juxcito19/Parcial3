/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Usuario
 */
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.Combate;
import modelo.Jugador;
import modelo.Monstruo;
import modelo.MonstruoEfec;
import modelo.RegistroCombate;
import principal.Main;
import vista.Batalla;

public class ControladorBatalla {

    private final Batalla vista;
    private final RegistroCombate registro;
    private Jugador j1;
    private Jugador j2;
    private Combate combate;
    private boolean enCurso = false;

    public ControladorBatalla(Batalla vista) {
        this.vista = vista;
        this.registro = new RegistroCombate();
        conectarEventos();
    }

    private void conectarEventos() {
        vista.getBtnIniciar().addActionListener(e -> iniciarDuelo());
        vista.getBtnAtacar().addActionListener(e -> atacar());
        vista.getBtnAtaqueDirecto().addActionListener(e -> ataqueDirecto());
        vista.getBtnReporteATQ().addActionListener(e -> reporteATQ());
        vista.getBtnReporteDestruidos().addActionListener(e -> reporteDestruidos());
    }

    private void iniciarDuelo() {
        String n1 = vista.getTxtNombreJ1().getText().trim();
        String n2 = vista.getTxtNombreJ2().getText().trim();
        if (n1.isEmpty()) {
            n1 = "Jugador 1";
        }
        if (n2.isEmpty()) {
            n2 = "Jugador 2";
        }

        j1 = new Jugador(n1);
        j2 = new Jugador(n2);
        // El Jugador 1 empieza atacando
        combate = new Combate(j1, j2);

        j1.agregarMonstruo(new Monstruo("Mago Oscuro", 2500, 2100, Monstruo.ATAQUE));
        j1.agregarMonstruo(new Monstruo("Dragón Blanco", 3000, 2500, Monstruo.ATAQUE));
        j1.agregarMonstruo(new MonstruoEfec("Guerrero Ígneo", 1800, 1200, Monstruo.ATAQUE, "Gana 500 de ATQ", 500));

        j2.agregarMonstruo(new Monstruo("Guerrero", 1800, 1000, Monstruo.ATAQUE));
        j2.agregarMonstruo(new Monstruo("Muro de Piedra", 500, 2000, Monstruo.DEFENSA));
        j2.agregarMonstruo(new Monstruo("Dragón Rojo", 2400, 2000, Monstruo.ATAQUE));

        enCurso = true;
        vista.getTxtLog().setText("");
        mostrar("Inicia el duelo: " + n1 + " vs " + n2 + ".");
        mostrar("Turno de " + j1.getNombre() + ".");
        refrescar();
    }

    private void atacar() {
        if (!duelo()) {
            return;
        }
        Jugador atacante = combate.getAtacante();
        Jugador defensor = combate.getDefensor();

        int ia = comboDe(atacante).getSelectedIndex();
        int io = comboDe(defensor).getSelectedIndex();
        if (ia < 0) {
            aviso("Selecciona el monstruo con el que vas a atacar.");
            return;
        }
        if (io < 0) {
            aviso(defensor.getNombre() + " no tiene monstruos. Usa Ataque directo.");
            return;
        }
        Monstruo monstruoAtacante = atacante.getCampo().get(ia);
        if (!puedeAtacar(monstruoAtacante)) {
            return;
        }
        Monstruo monstruoObjetivo = defensor.getCampo().get(io);
        mostrar(combate.resolverAtaque(monstruoAtacante, monstruoObjetivo));
        terminarTurno();
    }

    private void ataqueDirecto() {
        if (!duelo()) {
            return;
        }
        Jugador atacante = combate.getAtacante();
        Jugador defensor = combate.getDefensor();

        int ia = comboDe(atacante).getSelectedIndex();
        if (ia < 0) {
            aviso("Selecciona el monstruo con el que vas a atacar.");
            return;
        }
        if (!defensor.getCampo().isEmpty()) {
            aviso("No puedes atacar directo: " + defensor.getNombre() + " todavía tiene monstruos.");
            return;
        }
        Monstruo monstruoAtacante = atacante.getCampo().get(ia);
        if (!puedeAtacar(monstruoAtacante)) {
            return;
        }
        mostrar(combate.resolverAtaqueDirecto(monstruoAtacante));
        terminarTurno();
    }

    /**
     * Después de cada ataque: revisa si el duelo terminó y, si no,
     * le pasa el turno al otro jugador.
     */
    private void terminarTurno() {
        verificarFin();
        if (enCurso) {
            combate.cambiarTurno();
            // Si el jugador que sigue no tiene monstruos en ataque, no puede atacar y pierde el turno
            if (!combate.getAtacante().tieneMonstruosEnAtaque()) {
                mostrar(combate.getAtacante().getNombre() + " no tiene monstruos en ataque y pierde el turno.");
                combate.cambiarTurno();
            }
            mostrar("Turno de " + combate.getAtacante().getNombre() + ".");
        }
        refrescar();
    }

    /**
     * Un monstruo en posición de defensa no puede atacar.
     */
    private boolean puedeAtacar(Monstruo monstruo) {
        if (monstruo.getPosicion().equals(Monstruo.DEFENSA)) {
            aviso(monstruo.getNombre() + " está en defensa y no puede atacar.");
            return false;
        }
        return true;
    }

    /**
     * Devuelve el combo de monstruos del jugador indicado.
     * El de la izquierda es del Jugador 1 y el de la derecha del Jugador 2.
     */
    private JComboBox<String> comboDe(Jugador jugador) {
        if (jugador == j1) {
            return vista.getCmbJugador1();
        } else {
            return vista.getCmbJugador2();
        }
    }

    private void reporteATQ() {
        if (j1 == null) {
            aviso("Primero inicia el duelo.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- Reporte por ataque (mayor a menor) ---\n");
        agregarLista(sb, j1, Main.reportePorAtaque(j1.getCampo()));
        agregarLista(sb, j2, Main.reportePorAtaque(j2.getCampo()));
        vista.getTxtLog().append(sb.toString());
    }

    private void reporteDestruidos() {
        if (j1 == null) {
            aviso("Primero inicia el duelo.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- Reporte de monstruos destruidos ---\n");
        agregarLista(sb, j1, Main.reporteDestruidos(j1.getCementerio()));
        agregarLista(sb, j2, Main.reporteDestruidos(j2.getCementerio()));
        vista.getTxtLog().append(sb.toString());
    }

    private void agregarLista(StringBuilder sb, Jugador j, ArrayList<Monstruo> lista) {
        sb.append(j.getNombre()).append(":\n");
        if (lista.isEmpty()) {
            sb.append("  (ninguno)\n");
        }
        for (Monstruo m : lista) {
            sb.append("  ").append(m).append("\n");
        }
    }

    private void verificarFin() {
        boolean termino = false;
        Jugador ganador = null;

        if (j2.estaDerrotado()) {
            termino = true;
            ganador = j1;
        } else if (j1.estaDerrotado()) {
            termino = true;
            ganador = j2;
        } else if (!j1.tieneMonstruosEnAtaque() && !j2.tieneMonstruosEnAtaque()) {
            // Nadie tiene monstruos en ataque: gana el que tenga más LP
            termino = true;
            if (j1.getLp() > j2.getLp()) {
                ganador = j1;
            } else if (j2.getLp() > j1.getLp()) {
                ganador = j2;
            }
        }

        if (termino) {
            enCurso = false;
            if (ganador != null) {
                mostrar("Fin del duelo. Gana " + ganador.getNombre() + ".");
            } else {
                mostrar("Fin del duelo. Empate.");
            }
        }
    }

    private boolean duelo() {
        if (!enCurso) {
            aviso("Inicia un duelo nuevo con el botón Iniciar duelo.");
            return false;
        }
        return true;
    }

    private void refrescar() {
        vista.getLblLpJ1().setText(String.valueOf(j1.getLp()));
        vista.getLblLpJ2().setText(String.valueOf(j2.getLp()));
        llenarCombo(vista.getCmbJugador1(), j1.getCampo());
        llenarCombo(vista.getCmbJugador2(), j2.getCampo());

        if (!enCurso) {
            vista.getLblTurno().setText("Duelo terminado. Presiona Iniciar Duelo para jugar otra vez");
        } else if (combate.getAtacante() == j1) {
            vista.getLblTurno().setText("Turno de " + j1.getNombre() + " (Jugador 1)");
            vista.getLblMonstruoJ1().setText("Monstruo atacante");
            vista.getLblMonstruoJ2().setText("Monstruo objetivo");
        } else {
            vista.getLblTurno().setText("Turno de " + j2.getNombre() + " (Jugador 2)");
            vista.getLblMonstruoJ1().setText("Monstruo objetivo");
            vista.getLblMonstruoJ2().setText("Monstruo atacante");
        }
    }

    private void llenarCombo(JComboBox<String> combo, ArrayList<Monstruo> lista) {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        for (Monstruo m : lista) {
            modelo.addElement(m.toString());
        }
        combo.setModel(modelo);
    }

    private void mostrar(String texto) {
        vista.getTxtLog().append(texto + "\n");
        registro.registrar(texto);
    }

    private void aviso(String texto) {
        JOptionPane.showMessageDialog(vista, texto);
    }
}
