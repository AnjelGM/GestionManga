/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import interfaces.IVentanaMensaje;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VentanaMensajeConsola implements IVentanaMensaje{

    String mensaje;
    
    @Override
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void mostrar() {
        System.out.println("\n" + mensaje);
    }

}
