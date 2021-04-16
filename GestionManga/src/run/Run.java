/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package run;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.ModeloFicheroBinario;
import vistas.VentanaConsola;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Run {
    public static void main(String[] args) {
        VentanaConsola vista = new VentanaConsola();
        ModeloFicheroBinario modelo = new ModeloFicheroBinario();
        ControladorColeccion controlador = new ControladorColeccion(modelo, vista);
        
        vista.setControlador(controlador);
        modelo.setControlador(controlador);
        
        try {
            while(true){
                vista.mostrar();
                if(vista.getOperacion() == 10){
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
