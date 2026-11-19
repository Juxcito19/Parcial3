/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import controlador.ControladorBatalla;
import java.util.ArrayList;
import modelo.Monstruo;
import vista.Batalla;

/**
 * Punto de entrada del programa. Crea la vista y el controlador,
 * así la vista no necesita conocer al controlador (MVC).
 * También contiene los dos reportes sobre los monstruos guardados en ArrayList.
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) {
        // Aspecto visual Nimbus (si no existe, se usa el de por defecto)
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("No se pudo aplicar Nimbus: " + ex.getMessage());
        }

        Batalla ventana = new Batalla();
        new ControladorBatalla(ventana);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    /**
     * Reporte 1: devuelve los monstruos ordenados de mayor a menor ATQ.
     * Usa ordenamiento burbuja sobre una copia para no cambiar la lista original.
     */
    public static ArrayList<Monstruo> reportePorAtaque(ArrayList<Monstruo> monstruos) {
        ArrayList<Monstruo> ordenados = new ArrayList<>(monstruos);
        for (int i = 0; i < ordenados.size() - 1; i++) {
            for (int j = 0; j < ordenados.size() - 1 - i; j++) {
                if (ordenados.get(j).getAtq() < ordenados.get(j + 1).getAtq()) {
                    Monstruo temporal = ordenados.get(j);
                    ordenados.set(j, ordenados.get(j + 1));
                    ordenados.set(j + 1, temporal);
                }
            }
        }
        return ordenados;
    }

    /**
     * Reporte 2: devuelve solo los monstruos que fueron destruidos.
     */
    public static ArrayList<Monstruo> reporteDestruidos(ArrayList<Monstruo> monstruos) {
        ArrayList<Monstruo> destruidos = new ArrayList<>();
        for (Monstruo m : monstruos) {
            if (m.isDestruido()) {
                destruidos.add(m);
            }
        }
        return destruidos;
    }
}
