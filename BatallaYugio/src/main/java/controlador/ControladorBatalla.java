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
        refrescar();
    }

    private void atacar() {
        if (!duelo()) {
            return;
        }
        int ia = vista.getCmbAtacante().getSelectedIndex();
        int io = vista.getCmbObjetivo().getSelectedIndex();
        if (ia < 0 || io < 0) {
            aviso("Selecciona un monstruo atacante y un objetivo.");
            return;
        }
        Monstruo atacante = j1.getCampo().get(ia);
        Monstruo objetivo = j2.getCampo().get(io);
        mostrar(combate.resolverAtaque(atacante, objetivo));
        refrescar();
        verificarFin();
    }

    private void ataqueDirecto() {
        if (!duelo()) {
            return;
        }
        int ia = vista.getCmbAtacante().getSelectedIndex();
        if (ia < 0) {
            aviso("Selecciona un monstruo atacante.");
            return;
        }
        Monstruo atacante = j1.getCampo().get(ia);
        mostrar(combate.resolverAtaqueDirecto(atacante));
        refrescar();
        verificarFin();
    }

    private void reporteATQ() {
        if (j1 == null) {
            aviso("Primero inicia el duelo.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- Reporte por ataque (mayor a menor) ---\n");
        agregarLista(sb, j1, j1.reporteMonstruosPorATQ());
        agregarLista(sb, j2, j2.reporteMonstruosPorATQ());
        vista.getTxtLog().append(sb.toString());
    }

    private void reporteDestruidos() {
        if (j1 == null) {
            aviso("Primero inicia el duelo.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- Reporte de monstruos destruidos ---\n");
        agregarLista(sb, j1, j1.reporteMonstruosDestruidos());
        agregarLista(sb, j2, j2.reporteMonstruosDestruidos());
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
        Jugador ganador = null;
        if (j2.estaDerrotado()) {
            ganador = j1;
        } else if (j1.estaDerrotado()) {
            ganador = j2;
        }
        if (ganador != null) {
            enCurso = false;
            mostrar("Fin del duelo. Gana " + ganador.getNombre() + ".");
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
        llenarCombo(vista.getCmbAtacante(), j1.getCampo());
        llenarCombo(vista.getCmbObjetivo(), j2.getCampo());
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
