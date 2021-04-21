/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package run;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.*;
import vistas.*;

/**
 * 
 * @author Angel
 */
public class Run {
    public static void main(String[] args) {
        VentanaConsola vista = new VentanaConsola();
        
        ModeloColeccion modelo = new ModeloColeccion();
        ControladorColeccion controlador = new ControladorColeccion(modelo, vista);
        
        vista.setControlador(controlador);
        
        try {
            vista.mostrar();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
