/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import Modelos.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Dalex
 */
public class VistaJuego extends javax.swing.JFrame {

    private int milisegundos = 0;    // Milisegundos
    KeyEventDispatcher dispatcher;
    KeyboardFocusManager manager;
    private static boolean dispatcherActivo = false;
    int Xmov, Ymov;
    private char vectorTextoAleatorio[];
    private int conAciertos = 0;
    private int pressKey = 0;
    int contFallos = 0;
    int contPartidas = 0;
    private Timer timer;
    Random random = new Random();
    private final String[] combinacionLetras = {"qwertasdfzxcv", "yuiopghjklbnm"};
    int tiempoDeJuego, dificultad;
    int tiempIndividual = 0;

    LinkedList<Jugador> juego;

    /**
     * Creates new form VistaJuego
     */
    public VistaJuego() {
        initComponents();
        this.setLocationRelativeTo(this);
        estilosArcade();

    }

    private void consumirEventoTeclado() {
        manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        dispatcher = (KeyEvent e) -> {
            if (dispatcherActivo && e.getID() == KeyEvent.KEY_PRESSED) {
                char keyChar = e.getKeyChar();
                if (Character.isLetter(keyChar)) {
                    this.pressKey++;
                    SwingUtilities.invokeLater(() -> calculoFallasAciertos(keyChar));
                }

            }
            return false;
        };

    }

    private void calculoFallasAciertos(char keyChar) {
        if (comprobacionTextoIngresado(keyChar)) {
            this.conAciertos++;
            this.jlblContadorAcierto.setText(String.valueOf(this.conAciertos));
        } else {
            this.contFallos++;
            this.jlblContadorFallos.setText(String.valueOf(this.contFallos));
        }
        if (this.pressKey == this.dificultad) {
            this.jtxtIngresoTextJugador.setEnabled(false);
            timer.stop();
            desactivarEventoTeclado();
            jlblGameOver.setText("game");
            jlblover.setText("over");
            asignarResultadosJugadores();
            activarBotones();
            if (this.contPartidas == 1) {
                this.jbtnEmpezar.setText("Siguiente");
            } else {
                this.jbtnEmpezar.setText("Finalizar");

            }

        }
    }

    private void activarEventoTeclado() {
        manager.addKeyEventDispatcher(dispatcher);
        this.dispatcherActivo = true;
    }

    private void desactivarEventoTeclado() {
        if (this.dispatcher != null && this.manager != null) {
            this.manager.removeKeyEventDispatcher(dispatcher);
            this.dispatcherActivo = false;
            this.dispatcher = null;
            this.manager = null;
        }

    }

    public void consumirDatos(LinkedList<Jugador> juego, int tiempoDeJuego, int dificultad) {
        this.tiempoDeJuego = tiempoDeJuego;
        this.dificultad = dificultad;
        this.juego = juego;
        jlblGameOver.setText("");
        jlblover.setText("");
        asignarDatos();

    }

    private void asignarDatos() {
        this.jlblNombreJugador.setText(this.juego.get(0).nombre);
        this.tiempIndividual = this.tiempoDeJuego;
        actualizarTiempo();
    }

    private void iniciarCronometro() {
        this.contPartidas++;
        timer = new Timer(10, (ActionEvent e) -> {
            if (milisegundos > 0 || tiempIndividual > 0) {
                milisegundos -= 10;
                if (milisegundos < 0) {
                    milisegundos = 999;
                    if (tiempIndividual > 0) {
                        tiempIndividual--;
                    } else {
                        desactivarEventoTeclado();
                        totalFallas();
                        this.jtxtIngresoTextJugador.setEnabled(false);
                        jlblGameOver.setText("game");
                        jlblover.setText("over");
                        activarBotones();
                        asignarResultadosJugadores();
                        if (this.contPartidas == this.juego.size()) {
                            this.jbtnEmpezar.setText("Finalizar");
                        } else {
                            this.jbtnEmpezar.setText("Siguiente");
                        }

                        timer.stop();
                        return;
                    }
                }
                actualizarTiempo();
            } else {
                timer.stop();
            }
        });

        actualizarTiempo();
        timer.start();
    }

    private void estilosArcade() {
        jlblTitiloTurno.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30)); // Ajusta el tamaño y el estilo según sea necesario
        jlbl__Tiempo.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jLabel4.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jlblTextoVisualizacion.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jtxtIngresoTextJugador.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jLabel2.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jLabel3.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jlblContadorAcierto.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jlblContadorFallos.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jbtnVolver.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 18));
        jbtnEmpezar.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 18));
        jlbl__Observa.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 30));
        jlblTiempo.setFont(new Font("Consolas", Font.PLAIN, 68));
        jlblNombreJugador.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 50));
        jlblGameOver.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 50));
        jlblover.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 50));

    }

    private void actualizarTiempo() {
        int minutos = tiempIndividual / 60;
        int segundos = tiempIndividual % 60;
        int centesimas = (milisegundos / 10) % 100;
        jlblTiempo.setText(String.format("%02d:%02d:%02d", minutos, segundos, centesimas));
    }

    /*
    private String generarCombinacionAleatoria(String cadena, int longitud) {
        if (longitud == 0) {
            return "";
        } else {
            int indiceAleatorio = this.random.nextInt(cadena.length());
            char letraAleatoria = cadena.charAt(indiceAleatorio);
            return letraAleatoria + generarCombinacionAleatoria(cadena, longitud - 1);
        }
    }
     */
    private String generarCombinacionAleatoria(String cadena, int longitud) {
        StringBuilder combinacionAleatoria = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = this.random.nextInt(cadena.length());
            char letraAleatoria = cadena.charAt(indiceAleatorio);
            combinacionAleatoria.append(letraAleatoria);
        }
        return combinacionAleatoria.toString();
    }

    private void llenarDatos() {
        int indiceAleatorio = random.nextInt(combinacionLetras.length);
        String cadenaTotal = generarCombinacionAleatoria(this.combinacionLetras[indiceAleatorio], this.dificultad);
        this.jlblTextoVisualizacion.setText(cadenaTotal);
        this.vectorTextoAleatorio = cadenaTotal.toCharArray();
        this.jtxtIngresoTextJugador.setEnabled(true);
    }

    private void totalFallas() {
        this.contFallos = (this.dificultad - this.pressKey) + this.contFallos;
        this.jlblContadorFallos.setText(String.valueOf(this.contFallos));
    }

    private boolean comprobacionTextoIngresado(char valor) {
        return this.vectorTextoAleatorio[this.pressKey - 1] == Character.toLowerCase(valor);
    }

    private void asignarResultadosJugadores() {
        this.juego.get(this.contPartidas - 1).setAciertos(this.conAciertos);
        this.juego.get(this.contPartidas - 1).setFallos(this.contFallos);
        this.juego.get(this.contPartidas - 1).setTiempoRestante(tiempoTotalsobrante());
    }

    private void restaurarValores() {
        this.jlblNombreJugador.setText(this.juego.get(this.contPartidas).nombre);
        this.tiempIndividual = this.tiempoDeJuego;
        this.conAciertos = 0;
        this.contFallos = 0;
        this.pressKey = 0;
        this.jlblContadorAcierto.setText("00");
        this.jlblContadorFallos.setText("00");
        this.jtxtIngresoTextJugador.setText("");
        this.jlblTextoVisualizacion.setText("");
        this.jtxtIngresoTextJugador.setEnabled(false);
        actualizarTiempo();

    }

    private float tiempoTotalsobrante() {
        String[] valores = this.jlblTiempo.getText().split(":");
       return (Float.parseFloat(valores[0]) * 60) + (Float.parseFloat(valores[1])+ (Float.parseFloat(valores[2])/100));
    }

    private void bloquearBotones() {
        this.jbtnEmpezar.setEnabled(false);
        this.jbtnVolver.setEnabled(false);

    }

    private void activarBotones() {
        this.jbtnEmpezar.setEnabled(true);
        this.jbtnVolver.setEnabled(true);
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
        jlblTextoVisualizacion = new javax.swing.JLabel();
        jlbl__Tiempo = new javax.swing.JLabel();
        jlblTitiloTurno = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlblContadorFallos = new javax.swing.JLabel();
        jbtnVolver = new javax.swing.JButton();
        jbtnEmpezar = new javax.swing.JButton();
        jlblContadorAcierto = new javax.swing.JLabel();
        jlbl__Observa = new javax.swing.JLabel();
        jlblTiempo = new javax.swing.JLabel();
        jtxtIngresoTextJugador = new javax.swing.JTextField();
        jlblNombreJugador = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPnl_salida = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPnl_min = new javax.swing.JPanel();
        jLbl_min = new javax.swing.JLabel();
        jlblGameOver = new javax.swing.JLabel();
        jlblover = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));
        jPanel1.setForeground(new java.awt.Color(188, 47, 47));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTextoVisualizacion.setBackground(new java.awt.Color(255, 255, 255));
        jlblTextoVisualizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jlblTextoVisualizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 670, 49));

        jlbl__Tiempo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlbl__Tiempo.setForeground(new java.awt.Color(255, 255, 255));
        jlbl__Tiempo.setText("tiempo");
        jPanel1.add(jlbl__Tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        jlblTitiloTurno.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblTitiloTurno.setForeground(new java.awt.Color(255, 255, 255));
        jlblTitiloTurno.setText("TURNO");
        jPanel1.add(jlblTitiloTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 140, 49));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ACIERTOS ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fallos");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ESCRIBE");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jlblContadorFallos.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jlblContadorFallos.setForeground(new java.awt.Color(255, 255, 255));
        jlblContadorFallos.setText("00");
        jPanel1.add(jlblContadorFallos, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 70, 50));

        jbtnVolver.setBackground(new java.awt.Color(255, 153, 153));
        jbtnVolver.setText("Volver");
        jbtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 140, 50));

        jbtnEmpezar.setBackground(new java.awt.Color(51, 255, 0));
        jbtnEmpezar.setText("Empezar");
        jbtnEmpezar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEmpezarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnEmpezar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, 150, 50));

        jlblContadorAcierto.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jlblContadorAcierto.setForeground(new java.awt.Color(255, 255, 255));
        jlblContadorAcierto.setText("00");
        jPanel1.add(jlblContadorAcierto, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 70, 50));

        jlbl__Observa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlbl__Observa.setForeground(new java.awt.Color(255, 255, 255));
        jlbl__Observa.setText("OBSERVA ");
        jPanel1.add(jlbl__Observa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jlblTiempo.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jlblTiempo.setForeground(new java.awt.Color(255, 255, 255));
        jlblTiempo.setText("00:00");
        jPanel1.add(jlblTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 310, 70));

        jtxtIngresoTextJugador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtIngresoTextJugadorFocusLost(evt);
            }
        });
        jtxtIngresoTextJugador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtIngresoTextJugadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtIngresoTextJugadorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtIngresoTextJugadorKeyTyped(evt);
            }
        });
        jPanel1.add(jtxtIngresoTextJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 670, 50));

        jlblNombreJugador.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblNombreJugador.setForeground(new java.awt.Color(255, 255, 255));
        jlblNombreJugador.setText("nombre");
        jPanel1.add(jlblNombreJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

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

        jPanel3.add(jPnl_salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 50, 30));

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

        jPanel3.add(jPnl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 50, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 30));

        jlblGameOver.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblGameOver.setForeground(new java.awt.Color(255, 255, 255));
        jlblGameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblGameOver.setText("game");
        jPanel1.add(jlblGameOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 130, 50));

        jlblover.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblover.setForeground(new java.awt.Color(255, 255, 255));
        jlblover.setText("over");
        jPanel1.add(jlblover, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnEmpezarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEmpezarActionPerformed
        if (this.jbtnEmpezar.getText().equalsIgnoreCase("Empezar")) {
            iniciarCronometro();
            llenarDatos();
            consumirEventoTeclado();
            activarEventoTeclado();
            bloquearBotones();
        }
        if (this.jbtnEmpezar.getText().equalsIgnoreCase("Siguiente")) {
            restaurarValores();
            jlblGameOver.setText("");
            jlblover.setText("");
            this.jbtnEmpezar.setText("Empezar");
        }
        if (this.jbtnEmpezar.getText().equalsIgnoreCase("Finalizar")) {
            VisGanador vs = new VisGanador();
            vs.obtenerDatos(juego, this.tiempoDeJuego, this.dificultad);
            vs.setVisible(true);
            this.dispose();
        }


    }//GEN-LAST:event_jbtnEmpezarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    }//GEN-LAST:event_formKeyPressed

    private void jtxtIngresoTextJugadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtIngresoTextJugadorKeyTyped
        char caracter = evt.getKeyChar();
        if (caracter != KeyEvent.VK_BACK_SPACE && !Character.isLetter(caracter)) {
            evt.consume();
        }
    }//GEN-LAST:event_jtxtIngresoTextJugadorKeyTyped

    private void jtxtIngresoTextJugadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtIngresoTextJugadorKeyReleased

    }//GEN-LAST:event_jtxtIngresoTextJugadorKeyReleased

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

    private void jtxtIngresoTextJugadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtIngresoTextJugadorKeyPressed
    }//GEN-LAST:event_jtxtIngresoTextJugadorKeyPressed

    private void jtxtIngresoTextJugadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtIngresoTextJugadorFocusLost

    }//GEN-LAST:event_jtxtIngresoTextJugadorFocusLost

    private void jbtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolverActionPerformed
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea volver se borraran los datos de la partida?", "Confirmación de salida", JOptionPane.YES_NO_OPTION);
        if (mensaje == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(VistaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLbl_min;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPnl_min;
    public javax.swing.JPanel jPnl_salida;
    private javax.swing.JButton jbtnEmpezar;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JLabel jlblContadorAcierto;
    private javax.swing.JLabel jlblContadorFallos;
    private javax.swing.JLabel jlblGameOver;
    private javax.swing.JLabel jlblNombreJugador;
    private javax.swing.JLabel jlblTextoVisualizacion;
    private javax.swing.JLabel jlblTiempo;
    private javax.swing.JLabel jlblTitiloTurno;
    private javax.swing.JLabel jlbl__Observa;
    private javax.swing.JLabel jlbl__Tiempo;
    private javax.swing.JLabel jlblover;
    private javax.swing.JTextField jtxtIngresoTextJugador;
    // End of variables declaration//GEN-END:variables
}
