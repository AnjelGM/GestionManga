/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import interfaces.IVentanaBusqueda;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import run.ControladorColeccion;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VentanaBusquedaSwing extends JFrame implements IVentanaBusqueda{
    
    private String buscarCodigo, buscarTitulo;
    private byte operacion;
    private JTextField tfBuscarCodigo, tfBuscarTitulo;
    private JButton jbBuscarCodigo, jbBuscarTitulo;
    private GridLayout layoutInicial;
    private VentanaSwing ventanaPrincipal;
    private ControladorColeccion controlador;
    
    public VentanaBusquedaSwing(){
        layoutInicial = new GridLayout(2,2);
        
        tfBuscarCodigo = new JTextField("");
        tfBuscarTitulo = new JTextField("");
        
        jbBuscarCodigo = new JButton("Buscar Codigo");
        jbBuscarTitulo = new JButton("Buscar Titulo");
        
        jbBuscarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                operacion = 4;
                buscarCodigo = tfBuscarCodigo.getText();
                ventanaPrincipal.setOperacion(operacion);
                try {
                    controlador.notifiacion();
                    ventanaPrincipal.mostrarManga();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaBusquedaSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jbBuscarTitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                operacion = 5;
                buscarTitulo = tfBuscarTitulo.getText();
                ventanaPrincipal.setOperacion(operacion);
                try {
                    controlador.notifiacion();
                    ventanaPrincipal.mostrarManga();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaBusquedaSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
                setVisible(false);
            }
        });
        
        getContentPane().setLayout(layoutInicial);
        getContentPane().add(tfBuscarCodigo);
        getContentPane().add(tfBuscarTitulo);
        getContentPane().add(jbBuscarCodigo);
        getContentPane().add(jbBuscarTitulo);
        
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Busqueda");
        setLocationRelativeTo(null);
        pack();
    }
    
    public void mostrar(){
        setVisible(true);
    }
    
    public byte getOperacion(){
        return operacion;
    }
    
    public void setControlador(ControladorColeccion controlador){
        this.controlador = controlador;
    }
    
    public void setVistaPrincipal(VentanaSwing ventana){
        ventanaPrincipal = ventana;
    }
    
    @Override
    public String getBuscarCodigo() {
        return buscarCodigo;
    }

    @Override
    public String getBuscarTitulo() {
        return buscarTitulo;
    }

}
