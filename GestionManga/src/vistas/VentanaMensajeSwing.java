/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import interfaces.IVentanaMensaje;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VentanaMensajeSwing extends JFrame implements IVentanaMensaje{
    
    String mensaje;
    JLabel lmensaje;
    GridLayout layout;
    
    public VentanaMensajeSwing(){
        layout = new GridLayout(1, 1);
        getContentPane().setLayout(layout);
        mensaje = "";
        lmensaje = new JLabel(mensaje);
        
        getContentPane().add(lmensaje);
        
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Mensaje");
        setLocationRelativeTo(null);
        pack();
    }
    
    @Override
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void mostrar() {
        lmensaje.setText(mensaje);
        setVisible(true);
    }

}
