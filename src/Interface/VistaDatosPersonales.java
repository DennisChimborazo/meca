/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import Modelos.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Dalex
 */
public class VistaDatosPersonales extends javax.swing.JFrame {

    int Xmov, Ymov;

    SpinnerNumberModel spnModeloSegundos = new SpinnerNumberModel(10, 10, 60, 1);
    SpinnerNumberModel spnModeloMinutos = new SpinnerNumberModel(0, 0, 1, 1);

    /**
     * Creates new form VistaDatosPersonales
     */
    public VistaDatosPersonales() {
        initComponents();
        this.setLocationRelativeTo(this);
        estilosArcade();
        this.jspnSegundos.setModel(spnModeloSegundos);
        this.jspnMinutos.setModel(spnModeloMinutos);
        deshabilitarEdicionJpinner();

    }

    private void deshabilitarEdicionJpinner() {
        JComponent editorSegundos = jspnSegundos.getEditor();
        if (editorSegundos instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editorSegundos).getTextField().setEditable(false);
        }
        JComponent editorMinutos = jspnMinutos.getEditor();
        if (editorMinutos instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editorMinutos).getTextField().setEditable(false);
        }
    }

    private void estilosArcade() {
        jlblTitJugador1.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jlblTitJugador2.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jlblTitTiempo.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jlblTitParticipantes.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jtxtJugador1.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jtxtJugador2.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jlbltitMinutos.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jlbltitsegundos.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jbtnContinuar.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jspnMinutos.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jspnSegundos.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jrbtnDificil.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jrbtnNormal.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jrbtnBaja.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 24));
        jlblTitDificultad.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
    }

    private int tiempoTotalJuego() {
        return ((int) this.jspnMinutos.getValue() * 60) + ((int) this.jspnSegundos.getValue());
    }

    private boolean verificacionNombres() {
        if (!this.jtxtJugador1.getText().isEmpty() && !this.jtxtJugador2.getText().isEmpty()) {
            if (this.jtxtJugador1.getText().trim().length() > 3 && this.jtxtJugador2.getText().trim().length() > 3) {
                if (this.jtxtJugador1.getText().trim().length() <=7 && this.jtxtJugador2.getText().trim().length() <=7) {

                    if (!this.jtxtJugador1.getText().equalsIgnoreCase(this.jtxtJugador2.getText())) {

                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Los nombres \n deben ser distintos");
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Porfavor ingrese nombres mas \ncortos maximo 7 caracteres");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese nombres mas largos\n minimo 4 caracteres");
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Porfavor ingrese todos nombres \nde los jugadores");
            return false;
        }
    }

    private int determinarDificultad() {
        return this.jrbtnBaja.isSelected() ? 15
                : this.jrbtnNormal.isSelected() ? 25
                : this.jrbtnDificil.isSelected() ? 35 : 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jlblTitTiempo = new javax.swing.JLabel();
        jlblTitJugador2 = new javax.swing.JLabel();
        jlblTitJugador1 = new javax.swing.JLabel();
        jtxtJugador1 = new javax.swing.JTextField();
        jtxtJugador2 = new javax.swing.JTextField();
        jlblTitParticipantes = new javax.swing.JLabel();
        jbtnContinuar = new javax.swing.JButton();
        jlbltitMinutos = new javax.swing.JLabel();
        jlbltitsegundos = new javax.swing.JLabel();
        jspnMinutos = new javax.swing.JSpinner();
        jspnSegundos = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jPnl_salida = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPnl_min = new javax.swing.JPanel();
        jLbl_min = new javax.swing.JLabel();
        jlblTitDificultad = new javax.swing.JLabel();
        jrbtnDificil = new javax.swing.JRadioButton();
        jrbtnNormal = new javax.swing.JRadioButton();
        jrbtnBaja = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTitTiempo.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jlblTitTiempo.setForeground(new java.awt.Color(255, 255, 255));
        jlblTitTiempo.setText("Tiempo");
        jPanel2.add(jlblTitTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, -1, -1));

        jlblTitJugador2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblTitJugador2.setForeground(new java.awt.Color(255, 255, 255));
        jlblTitJugador2.setText("Jugador 2");
        jPanel2.add(jlblTitJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jlblTitJugador1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblTitJugador1.setForeground(new java.awt.Color(255, 255, 255));
        jlblTitJugador1.setText("Jugador 1");
        jPanel2.add(jlblTitJugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jtxtJugador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtJugador1ActionPerformed(evt);
            }
        });
        jPanel2.add(jtxtJugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 280, 40));
        jPanel2.add(jtxtJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 280, 40));

        jlblTitParticipantes.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jlblTitParticipantes.setForeground(new java.awt.Color(255, 255, 255));
        jlblTitParticipantes.setText("Participantes");
        jPanel2.add(jlblTitParticipantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jbtnContinuar.setBackground(new java.awt.Color(51, 255, 0));
        jbtnContinuar.setText("continuar");
        jbtnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnContinuarActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 220, 50));

        jlbltitMinutos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlbltitMinutos.setForeground(new java.awt.Color(255, 255, 255));
        jlbltitMinutos.setText("minutos");
        jPanel2.add(jlbltitMinutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        jlbltitsegundos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlbltitsegundos.setForeground(new java.awt.Color(255, 255, 255));
        jlbltitsegundos.setText("segundos");
        jPanel2.add(jlbltitsegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, -1, -1));
        jPanel2.add(jspnMinutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 90, 40));
        jPanel2.add(jspnSegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, 90, 40));

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

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("   X");
        jPnl_salida.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        jPanel3.add(jPnl_salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 50, 30));

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

        jPanel3.add(jPnl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 50, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 30));

        jlblTitDificultad.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jlblTitDificultad.setForeground(new java.awt.Color(255, 255, 255));
        jlblTitDificultad.setText("dificultad");
        jPanel2.add(jlblTitDificultad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, -1, -1));

        jbtnGroup.add(jrbtnDificil);
        jrbtnDificil.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrbtnDificil.setForeground(new java.awt.Color(255, 255, 255));
        jrbtnDificil.setText("Alta");
        jrbtnDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnDificilActionPerformed(evt);
            }
        });
        jPanel2.add(jrbtnDificil, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        jbtnGroup.add(jrbtnNormal);
        jrbtnNormal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrbtnNormal.setForeground(new java.awt.Color(255, 255, 255));
        jrbtnNormal.setText("Media");
        jrbtnNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnNormalActionPerformed(evt);
            }
        });
        jPanel2.add(jrbtnNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, -1, -1));

        jbtnGroup.add(jrbtnBaja);
        jrbtnBaja.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrbtnBaja.setForeground(new java.awt.Color(255, 255, 255));
        jrbtnBaja.setText("baja");
        jrbtnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnBajaActionPerformed(evt);
            }
        });
        jPanel2.add(jrbtnBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnContinuarActionPerformed
        if (verificacionNombres()) {
            if (determinarDificultad() != 0) {
                Jugador j1 = new Jugador(this.jtxtJugador1.getText(), 0, 0, 0);
                Jugador j2 = new Jugador(this.jtxtJugador2.getText(), 0, 0, 0);
                LinkedList<Jugador> nuevoJuego = new LinkedList();
                nuevoJuego.add(j1);
                nuevoJuego.add(j2);
                VistaJuego vistJuego = new VistaJuego();
                vistJuego.consumirDatos(nuevoJuego, tiempoTotalJuego(), determinarDificultad());
                vistJuego.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Debe selecionar la dificultad");
            }

        }
    }//GEN-LAST:event_jbtnContinuarActionPerformed

    private void jPnl_salidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseClicked

        int mensaje = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmación de salida", JOptionPane.YES_NO_OPTION);

        if (mensaje == JOptionPane.YES_OPTION) {
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

    private void jrbtnDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnDificilActionPerformed
        SpinnerNumberModel spnModeloMinutosdificil = new SpinnerNumberModel(0, 0, 0, 1);
        this.jspnMinutos.setModel(spnModeloMinutosdificil);
        deshabilitarEdicionJpinner();

        // TODO add your handling code here:
    }//GEN-LAST:event_jrbtnDificilActionPerformed

    private void jrbtnNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnNormalActionPerformed

        this.jspnMinutos.setModel(spnModeloMinutos);
        deshabilitarEdicionJpinner();
// TODO add your handling code here:
    }//GEN-LAST:event_jrbtnNormalActionPerformed

    private void jrbtnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnBajaActionPerformed

        this.jspnMinutos.setModel(spnModeloMinutos);
        deshabilitarEdicionJpinner();// TODO add your handling code here:
    }//GEN-LAST:event_jrbtnBajaActionPerformed

    private void jtxtJugador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtJugador1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtJugador1ActionPerformed

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
            java.util.logging.Logger.getLogger(VistaDatosPersonales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaDatosPersonales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaDatosPersonales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaDatosPersonales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaDatosPersonales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLbl_min;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPnl_min;
    public javax.swing.JPanel jPnl_salida;
    private javax.swing.JButton jbtnContinuar;
    private javax.swing.ButtonGroup jbtnGroup;
    private javax.swing.JLabel jlblTitDificultad;
    private javax.swing.JLabel jlblTitJugador1;
    private javax.swing.JLabel jlblTitJugador2;
    private javax.swing.JLabel jlblTitParticipantes;
    private javax.swing.JLabel jlblTitTiempo;
    private javax.swing.JLabel jlbltitMinutos;
    private javax.swing.JLabel jlbltitsegundos;
    private javax.swing.JRadioButton jrbtnBaja;
    private javax.swing.JRadioButton jrbtnDificil;
    private javax.swing.JRadioButton jrbtnNormal;
    private javax.swing.JSpinner jspnMinutos;
    private javax.swing.JSpinner jspnSegundos;
    private javax.swing.JTextField jtxtJugador1;
    private javax.swing.JTextField jtxtJugador2;
    // End of variables declaration//GEN-END:variables
}
