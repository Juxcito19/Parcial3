/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Usuario
 */
public class Batalla extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Batalla.class.getName());

    /**
     * Creates new form Batalla
     */
    public Batalla() {
        initComponents();
        aplicarDiseno();
    }

    // ===================== Diseño de la ventana =====================
    private static final Color FONDO = new Color(24, 26, 38);
    private static final Color TARJETA = new Color(36, 39, 56);
    private static final Color BORDE = new Color(60, 64, 85);
    private static final Color AZUL = new Color(77, 148, 255);
    private static final Color ROJO = new Color(240, 86, 86);
    private static final Color DORADO = new Color(255, 200, 87);
    private static final Color TEXTO = new Color(230, 232, 240);
    private static final Color TEXTO_SUAVE = new Color(150, 155, 175);
    private static final Color GRIS_BOTON = new Color(70, 75, 98);


    private void aplicarDiseno() {
        setTitle("Batalla de Monstruos");
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout(0, 16));
        jPanel1.setBackground(FONDO);
        jPanel1.setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));


        jLabel1.setText("JUEGO DE MONSTRUOS");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        jLabel1.setForeground(DORADO);
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel2.setText("Jugador 1:");
        jLabel3.setText("Jugador 2:");
        estiloEtiqueta(jLabel2, AZUL, 14, Font.BOLD);
        estiloEtiqueta(jLabel3, ROJO, 14, Font.BOLD);
        estiloCampo(txtNombreJ1);
        estiloCampo(txtNombreJ2);
        estiloBoton(btnIniciar, DORADO, FONDO);

        JPanel filaNombres = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        filaNombres.setOpaque(false);
        filaNombres.add(jLabel2);
        filaNombres.add(txtNombreJ1);
        filaNombres.add(Box.createHorizontalStrut(24));
        filaNombres.add(btnIniciar);
        filaNombres.add(Box.createHorizontalStrut(24));
        filaNombres.add(jLabel3);
        filaNombres.add(txtNombreJ2);

        JPanel encabezado = new JPanel(new BorderLayout(0, 14));
        encabezado.setOpaque(false);
        encabezado.add(jLabel1, BorderLayout.NORTH);
        encabezado.add(filaNombres, BorderLayout.CENTER);


        jLabel4.setText("LIFE POINTS");
        jLabel6.setText("LIFE POINTS");
        jLabel5.setText("Monstruo atacante");
        jLabel7.setText("Monstruo objetivo");

        JPanel tarjetas = new JPanel(new GridLayout(1, 2, 18, 0));
        tarjetas.setOpaque(false);
        tarjetas.add(crearTarjeta("JUGADOR 1", AZUL, jLabel4, lblLpJ1, jLabel5, cmbAtacante));
        tarjetas.add(crearTarjeta("JUGADOR 2", ROJO, jLabel6, lblLpJ2, jLabel7, cmbObjetivo));


        estiloBoton(btnAtacar, ROJO, Color.WHITE);
        estiloBoton(btnAtaqueDirecto, AZUL, Color.WHITE);
        estiloBoton(btnReporteATQ, GRIS_BOTON, TEXTO);
        estiloBoton(btnReporteDestruidos, GRIS_BOTON, TEXTO);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        acciones.setOpaque(false);
        acciones.add(btnAtacar);
        acciones.add(btnAtaqueDirecto);
        acciones.add(Box.createHorizontalStrut(30));
        acciones.add(btnReporteATQ);
        acciones.add(btnReporteDestruidos);

        JPanel arriba = new JPanel(new BorderLayout(0, 18));
        arriba.setOpaque(false);
        arriba.add(encabezado, BorderLayout.NORTH);
        arriba.add(tarjetas, BorderLayout.CENTER);
        arriba.add(acciones, BorderLayout.SOUTH);

 
        JLabel tituloLog = new JLabel("REGISTRO DEL DUELO");
        estiloEtiqueta(tituloLog, TEXTO_SUAVE, 12, Font.BOLD);

        txtLog.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtLog.setBackground(new Color(18, 20, 30));
        txtLog.setForeground(TEXTO);
        txtLog.setCaretColor(TEXTO);
        txtLog.setLineWrap(true);
        txtLog.setWrapStyleWord(true);
        txtLog.setMargin(new Insets(8, 10, 8, 10));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(BORDE));
        jScrollPane1.setPreferredSize(new Dimension(0, 190));

        JPanel registro = new JPanel(new BorderLayout(0, 6));
        registro.setOpaque(false);
        registro.add(tituloLog, BorderLayout.NORTH);
        registro.add(jScrollPane1, BorderLayout.CENTER);

        jPanel1.add(arriba, BorderLayout.NORTH);
        jPanel1.add(registro, BorderLayout.CENTER);

        setMinimumSize(new Dimension(760, 640));
        pack();
    }

    private JPanel crearTarjeta(String titulo, Color acento, JLabel lblTituloLp,
            JLabel lblLp, JLabel lblMonstruo, JComboBox<String> combo) {
        JPanel tarjeta = new JPanel(new GridBagLayout());
        tarjeta.setBackground(TARJETA);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(4, 0, 0, 0, acento),
                BorderFactory.createEmptyBorder(14, 18, 18, 18)));

        JLabel lblTitulo = new JLabel(titulo);
        estiloEtiqueta(lblTitulo, acento, 15, Font.BOLD);
        estiloEtiqueta(lblTituloLp, TEXTO_SUAVE, 11, Font.BOLD);
        estiloEtiqueta(lblLp, TEXTO, 40, Font.BOLD);
        estiloEtiqueta(lblMonstruo, TEXTO_SUAVE, 12, Font.PLAIN);

        // Ancho fijo para que el combo no se estire con nombres largos
        combo.setPrototypeDisplayValue("Guerrero Ígneo [ATQ 2300 / DEF 1200 / ATAQUE]");
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;

        c.gridy = 0;
        tarjeta.add(lblTitulo, c);
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        tarjeta.add(lblTituloLp, c);
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0);
        tarjeta.add(lblLp, c);
        c.gridy = 3;
        c.insets = new Insets(14, 0, 4, 0);
        tarjeta.add(lblMonstruo, c);
        c.gridy = 4;
        c.insets = new Insets(0, 0, 0, 0);
        tarjeta.add(combo, c);
        return tarjeta;
    }

    private void estiloEtiqueta(JLabel lbl, Color color, int tamano, int estilo) {
        lbl.setForeground(color);
        lbl.setFont(new Font("Segoe UI", estilo, tamano));
    }

    private void estiloCampo(JTextField campo) {
        campo.setColumns(10);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void estiloBoton(JButton boton, Color fondo, Color texto) {
        Color hover = fondo.brighter();
        boton.setUI(new BasicButtonUI() {
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                g.setColor(fondo.darker());
                g.fillRect(0, 0, b.getWidth(), b.getHeight());
            }
        });
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setBorder(BorderFactory.createEmptyBorder(9, 18, 9, 18));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(fondo);
            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreJ1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombreJ2 = new javax.swing.JTextField();
        btnIniciar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblLpJ1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbAtacante = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        lblLpJ2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbObjetivo = new javax.swing.JComboBox<>();
        btnAtacar = new javax.swing.JButton();
        btnAtaqueDirecto = new javax.swing.JButton();
        btnReporteATQ = new javax.swing.JButton();
        btnReporteDestruidos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("JUEGO DE MONSTRUOS");

        jLabel2.setText("Jugador 1: ");

        txtNombreJ1.addActionListener(this::txtNombreJ1ActionPerformed);

        jLabel3.setText("Jugador 2:");

        btnIniciar.setText("Iniciar Duelo");
        btnIniciar.addActionListener(this::btnIniciarActionPerformed);

        jLabel4.setText("LP Jugador 1");

        lblLpJ1.setText("8000");

        jLabel5.setText("Monstruo atacante");

        jLabel6.setText("LP Jugador 2");

        lblLpJ2.setText("8000");

        jLabel7.setText("Monstruo objetivo");

        btnAtacar.setText("Atacar");
        btnAtacar.addActionListener(this::btnAtacarActionPerformed);

        btnAtaqueDirecto.setText("Ataque directo");
        btnAtaqueDirecto.addActionListener(this::btnAtaqueDirectoActionPerformed);

        btnReporteATQ.setText("Reporte por ataque");

        btnReporteDestruidos.setText("Reporte destruidos");

        txtLog.setEditable(false);
        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNombreJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(lblLpJ1))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbAtacante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAtacar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnReporteATQ, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(btnIniciar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNombreJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(169, 169, 169))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(lblLpJ2))
                                    .addComponent(jLabel7)
                                    .addComponent(btnAtaqueDirecto)
                                    .addComponent(cmbObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReporteDestruidos)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNombreJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtNombreJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIniciar)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLpJ2)
                    .addComponent(lblLpJ1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAtacante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtacar)
                    .addComponent(btnAtaqueDirecto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteATQ)
                    .addComponent(btnReporteDestruidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreJ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreJ1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreJ1ActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtacarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtacarActionPerformed

    private void btnAtaqueDirectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtaqueDirectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtaqueDirectoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        // Colores oscuros para Nimbus (campos de texto, combos, scroll y diálogos)
        javax.swing.UIManager.put("control", new Color(36, 39, 56));
        javax.swing.UIManager.put("info", new Color(36, 39, 56));
        javax.swing.UIManager.put("nimbusBase", new Color(30, 33, 48));
        javax.swing.UIManager.put("nimbusBlueGrey", new Color(60, 64, 85));
        javax.swing.UIManager.put("nimbusLightBackground", new Color(28, 30, 44));
        javax.swing.UIManager.put("nimbusFocus", new Color(77, 148, 255));
        javax.swing.UIManager.put("nimbusSelectionBackground", new Color(77, 148, 255));
        javax.swing.UIManager.put("nimbusSelectedText", Color.WHITE);
        javax.swing.UIManager.put("nimbusDisabledText", new Color(120, 120, 135));
        javax.swing.UIManager.put("text", new Color(230, 232, 240));
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

java.awt.EventQueue.invokeLater(() -> {
    Batalla ventana = new Batalla();
    new controlador.ControladorBatalla(ventana);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
});
    }
    public javax.swing.JTextField getTxtNombreJ1() { return txtNombreJ1; }
    public javax.swing.JTextField getTxtNombreJ2() { return txtNombreJ2; }
    public javax.swing.JButton getBtnIniciar() { return btnIniciar; }
    public javax.swing.JLabel getLblLpJ1() { return lblLpJ1; }
    public javax.swing.JLabel getLblLpJ2() { return lblLpJ2; }
    public javax.swing.JComboBox<String> getCmbAtacante() { return cmbAtacante; }
    public javax.swing.JComboBox<String> getCmbObjetivo() { return cmbObjetivo; }
    public javax.swing.JButton getBtnAtacar() { return btnAtacar; }
    public javax.swing.JButton getBtnAtaqueDirecto() { return btnAtaqueDirecto; }
    public javax.swing.JButton getBtnReporteATQ() { return btnReporteATQ; }
    public javax.swing.JButton getBtnReporteDestruidos() { return btnReporteDestruidos; }
    public javax.swing.JTextArea getTxtLog() { return txtLog; }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtacar;
    private javax.swing.JButton btnAtaqueDirecto;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnReporteATQ;
    private javax.swing.JButton btnReporteDestruidos;
    private javax.swing.JComboBox<String> cmbAtacante;
    private javax.swing.JComboBox<String> cmbObjetivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLpJ1;
    private javax.swing.JLabel lblLpJ2;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtNombreJ1;
    private javax.swing.JTextField txtNombreJ2;
    // End of variables declaration//GEN-END:variables
}
