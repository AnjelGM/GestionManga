/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package run;

import com.github.javafaker.Faker;
import java.io.IOException;
import modelos.*;
import vistas.*;

/**
 * 
 * @author Angel
 */
public class Run {
    public static void main(String[] args) {
        Faker a;
        Ventana vista = new Ventana();
        ModeloMySQL modelo = new ModeloMySQL();
        ControladorColeccion controlador = new ControladorColeccion(modelo, vista);
        
        vista.setControlador(controlador);
        
        try {
            vista.mostrar();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}