/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.io.Serializable;

/**
 *
 * @author Dalex
 */
public class Jugador implements Serializable, Comparable<Jugador> {

    public String nombre;
    public int aciertos, fallos, puntFallos, punAciertos;
   public float resultado, punTiempo, tiempoRestante;

    public Jugador(String nombre, int aciertos, int fallos, float tiempoRestante) {
        this.nombre = nombre;
        this.aciertos = aciertos;
        this.fallos = fallos;
        this.tiempoRestante = tiempoRestante;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public void setTiempoRestante(float tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public void definirResulttados(int punAciertos, int puntFallos, int punTiem) {
        this.punAciertos = this.aciertos * punAciertos;
        this.puntFallos = this.fallos * puntFallos;
        this.punTiempo = this.tiempoRestante * punTiem;
        this.resultado = (this.punAciertos - this.puntFallos) + this.punTiempo;

    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    @Override
    public int compareTo(Jugador otro) {
        return Float.compare(otro.resultado, this.resultado);
    }

}
