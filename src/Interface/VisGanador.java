/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import Modelos.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import juegomecanografia.Archivo;

/**
 *
 * @author Dalex
 */
public class VisGanador extends javax.swing.JFrame {

    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    Archivo archivos = new Archivo();
    int Xmov, Ymov;
    int tiempoJuego, dificultad;
    final int PUNTOS_POR_ACIERTO = 10;
    final int PUNTOS_POR_FALLOS = 10;
    final int PUNTOS_POR_TIEMPO = 2;
    LinkedList<Jugador> listaJugadores;
    DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"NOMBRE", "ACIERTOS", "FALLOS", "TIEMPO sobrante", "Pun. ACIERTOS",
        "Pun. FALLOS", "Pun. TIEMPO", "PUNTAJE"}, 2);
    JLabel jLabel1 = new JLabel();
    ImageIcon icono1 = new ImageIcon(getClass().getResource("/Imagenes/trofeoact1.png"));

    /**
     * Creates new form VisGanador
     */
    public VisGanador() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.jTable1.setModel(modeloTabla);
        cambiarAnchoCeldas();
        this.jlblNombreGanador.setText("dennis");
        this.jlblTiempoGanado.setText("60");
        this.jlblPuntosGanador.setText("64");
        estilosArcade();
            jLabel4.setIcon (icono1);

    }

    private void estilosArcade() {
        this.jLabel3.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 60));
        jLabel1.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 45)); // Ajusta el tamaño y el estilo según sea necesario
        jlblTituloTiempo.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jlblTituloPuntaje.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jlblNombreGanador.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 40));
        jbtnInicio.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jbtnJugarNuevamente.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jbtnBorrarPartida.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));

    }

    private void cambiarAnchoCeldas() {
        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            this.jTable1.setRowHeight(i, 85);
        }
    }

    public void obtenerDatos(LinkedList<Jugador> ju, int tiempoJuego, int dificultad) {
        this.tiempoJuego = tiempoJuego;
        this.listaJugadores = ju;
        this.dificultad = dificultad;
        Collections.sort(listaJugadores);
        llenartabla();
        mostrarGanador();
    }

    public void restablecerValores() {
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            this.listaJugadores.get(i).setResultado(0);
            this.listaJugadores.get(i).setAciertos(0);
            this.listaJugadores.get(i).setFallos(0);
            this.listaJugadores.get(i).setTiempoRestante(0);
        }
    }

    public void llenartabla() {
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            this.listaJugadores.get(i).definirResulttados(this.PUNTOS_POR_ACIERTO, this.PUNTOS_POR_FALLOS, this.PUNTOS_POR_TIEMPO);
            this.jTable1.setValueAt(this.listaJugadores.get(i).nombre, i, 0);
            this.jTable1.setValueAt(this.listaJugadores.get(i).aciertos, i, 1);
            this.jTable1.setValueAt(this.listaJugadores.get(i).fallos, i, 2);
            this.jTable1.setValueAt(this.listaJugadores.get(i).tiempoRestante, i, 3);
            this.jTable1.setValueAt(this.listaJugadores.get(i).punAciertos, i, 4);
            this.jTable1.setValueAt(this.listaJugadores.get(i).puntFallos, i, 5);
            this.jTable1.setValueAt(this.listaJugadores.get(i).punTiempo, i, 6);
            this.jTable1.setValueAt(this.listaJugadores.get(i).resultado, i, 7);
        }
    }

    public void mostrarGanador() {
        if (this.listaJugadores.get(0).aciertos == this.listaJugadores.get(1).aciertos
                && this.listaJugadores.get(0).fallos == this.listaJugadores.get(1).fallos
                && this.listaJugadores.get(0).tiempoRestante == this.listaJugadores.get(1).tiempoRestante) {
            this.jlblNombreGanador.setText("empates");
            this.jlblPuntosGanador.setText(this.decimalFormat.format(this.listaJugadores.get(0).resultado));
            this.jlblTiempoGanado.setText(this.decimalFormat.format(this.tiempoJuego - this.listaJugadores.get(0).tiempoRestante) + " seg");
        } else {
            this.jlblNombreGanador.setText(this.listaJugadores.get(0).nombre);
            this.jlblPuntosGanador.setText(this.decimalFormat.format(this.listaJugadores.get(0).resultado));
            this.jlblTiempoGanado.setText(this.decimalFormat.format(this.tiempoJuego - this.listaJugadores.get(0).tiempoRestante) + " seg");
        }
    }

    public void guardarDatos() {
        LinkedList<Jugador> listaJugTotal = archivos.extraer("jugadores.txt");
        for (Jugador j : this.listaJugadores) {
            listaJugTotal.add(j);
        }
        archivos.guardar(listaJugTotal, "jugadores.txt");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jlblTituloPuntaje = new javax.swing.JLabel();
        jlblNombreGanador = new javax.swing.JLabel();
        jlblTituloTiempo = new javax.swing.JLabel();
        jlblTiempoGanado = new javax.swing.JLabel();
        jlblPuntosGanador = new javax.swing.JLabel();
        jbtnJugarNuevamente = new javax.swing.JButton();
        jbtnInicio = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPnl_salida = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPnl_min = new javax.swing.JPanel();
        jLbl_min = new javax.swing.JLabel();
        jbtnBorrarPartida = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 114, 241));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBackground(new java.awt.Color(153, 153, 153));
        jTable1.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 1000, 170));

        jlblTituloPuntaje.setForeground(new java.awt.Color(255, 255, 255));
        jlblTituloPuntaje.setText("PUNTUACION");
        jPanel1.add(jlblTituloPuntaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jlblNombreGanador.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblNombreGanador.setForeground(new java.awt.Color(255, 255, 255));
        jlblNombreGanador.setText("NOMBRE");
        jPanel1.add(jlblNombreGanador, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, -1, -1));

        jlblTituloTiempo.setForeground(new java.awt.Color(255, 255, 255));
        jlblTituloTiempo.setText("Tiempo culminado");
        jPanel1.add(jlblTituloTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 180, 250, 60));

        jlblTiempoGanado.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        jlblTiempoGanado.setForeground(new java.awt.Color(255, 255, 255));
        jlblTiempoGanado.setText("00");
        jPanel1.add(jlblTiempoGanado, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, -1, -1));

        jlblPuntosGanador.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        jlblPuntosGanador.setForeground(new java.awt.Color(255, 255, 255));
        jlblPuntosGanador.setText("00");
        jPanel1.add(jlblPuntosGanador, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jbtnJugarNuevamente.setBackground(new java.awt.Color(51, 255, 51));
        jbtnJugarNuevamente.setText("jugar de nuevo");
        jbtnJugarNuevamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnJugarNuevamenteActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnJugarNuevamente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, 200, 60));

        jbtnInicio.setBackground(new java.awt.Color(51, 255, 0));
        jbtnInicio.setText("Inicio");
        jbtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnInicioActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, 140, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(530, 420));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnl_salida.setBackground(new java.awt.Color(255, 255, 255));
        jPnl_salida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPnl_salidaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPnl_salidaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPnl_salidaMouseExited(evt);
            }
        });
        jPnl_salida.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("   X");
        jPnl_salida.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        jPanel3.add(jPnl_salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 50, 30));

        jPnl_min.setBackground(new java.awt.Color(255, 255, 255));
        jPnl_min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPnl_minMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPnl_minMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPnl_minMouseExited(evt);
            }
        });
        jPnl_min.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLbl_min.setBackground(new java.awt.Color(0, 0, 0));
        jLbl_min.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLbl_min.setText("   -");
        jPnl_min.add(jLbl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        jPanel3.add(jPnl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 50, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 30));

        jbtnBorrarPartida.setBackground(new java.awt.Color(255, 102, 102));
        jbtnBorrarPartida.setText("borrar partida");
        jbtnBorrarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrarPartidaActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnBorrarPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 480, 200, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ganador");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 180, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPnl_salidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseClicked

        int mensaje = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmación de salida", JOptionPane.YES_NO_OPTION);
        if (mensaje == JOptionPane.YES_OPTION) {
            guardarDatos();
            System.exit(0);
        }
    }//GEN-LAST:event_jPnl_salidaMouseClicked

    private void jPnl_salidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseEntered
        jPnl_salida.setBackground(Color.red);
    }//GEN-LAST:event_jPnl_salidaMouseEntered

    private void jPnl_salidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseExited
        jPnl_salida.setBackground(Color.WHITE);
    }//GEN-LAST:event_jPnl_salidaMouseExited

    private void jPnl_minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_minMouseClicked
        setExtendedState(JFrame.ICONIFIED);

        // TODO add your handling code here:
    }//GEN-LAST:event_jPnl_minMouseClicked

    private void jPnl_minMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_minMouseEntered
        jPnl_min.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_jPnl_minMouseEntered

    private void jPnl_minMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_minMouseExited
        jPnl_min.setBackground(Color.white);
    }//GEN-LAST:event_jPnl_minMouseExited

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - Xmov, y - Ymov);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        Xmov = evt.getX();
        Ymov = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jbtnJugarNuevamenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnJugarNuevamenteActionPerformed

        int mensaje = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere volver a jugar?", "Confirmación de salida", JOptionPane.YES_NO_OPTION);
        if (mensaje == JOptionPane.YES_OPTION) {
            restablecerValores();
            VistaJuego vistJuego = new VistaJuego();
            vistJuego.consumirDatos(this.listaJugadores, this.tiempoJuego, this.dificultad);
            vistJuego.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jbtnJugarNuevamenteActionPerformed

    private void jbtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInicioActionPerformed
        guardarDatos();
        VistaInicio visIn = new VistaInicio();
        visIn.setVisible(true);
        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnInicioActionPerformed

    private void jbtnBorrarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBorrarPartidaActionPerformed

        int mensaje = JOptionPane.showConfirmDialog(null, "¿Está seguro que deseas borrar esta partida?", "Confirmación de salida", JOptionPane.YES_NO_OPTION);
        if (mensaje == JOptionPane.YES_OPTION) {
            VistaInicio visIn = new VistaInicio();
            visIn.setVisible(true);
            this.dispose();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jbtnBorrarPartidaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisGanador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisGanador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisGanador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisGanador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisGanador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLbl_min;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPnl_min;
    public javax.swing.JPanel jPnl_salida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnBorrarPartida;
    private javax.swing.JButton jbtnInicio;
    private javax.swing.JButton jbtnJugarNuevamente;
    private javax.swing.JLabel jlblNombreGanador;
    private javax.swing.JLabel jlblPuntosGanador;
    private javax.swing.JLabel jlblTiempoGanado;
    private javax.swing.JLabel jlblTituloPuntaje;
    private javax.swing.JLabel jlblTituloTiempo;
    // End of variables declaration//GEN-END:variables
}
