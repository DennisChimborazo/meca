/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegomecanografia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Dalex
 * @param <T>
 */
public class Archivo<T> implements Serializable {

    public void guardar(LinkedList<T> lista, String archivo) {
        borrar(archivo);
        try ( FileOutputStream fileOut = new FileOutputStream(archivo);  ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            for (T obj : lista) {
                objectOut.writeObject(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<T> extraer(String archivo) {
        LinkedList<T> lista = new LinkedList<>();
        try ( FileInputStream fileIn = new FileInputStream(archivo);  ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Object obj;
            while ((obj = objectIn.readObject()) != null) {
                lista.add((T) obj);
            }
        } catch (IOException | ClassNotFoundException e) {

        }
        return lista;
    }

    public void borrar(String archivo) {
        File file = new File(archivo);
        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("No se pudo borrar el archivo " + archivo);
            }
        }
    }

}
