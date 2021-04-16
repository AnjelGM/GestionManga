/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import interfaces.IVentanaBusqueda;
import java.util.Scanner;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VentanaBusquedaConsola implements IVentanaBusqueda{
    
    String buscarCodigo, buscarTitulo;
    Scanner sc;
    
    @Override
    public String getBuscarCodigo() {
        return buscarCodigo;
    }

    @Override
    public String getBuscarTitulo() {
        return buscarTitulo;
    }
    
    public String buscarCodigo() {
        
        System.out.println("Introduce el codigo del manga que desea buscar:");
        buscarCodigo = sc.nextLine();
        
        return buscarCodigo;
    }
    
    public String buscarNombre(){
        
        System.out.println("Introduce el titulo del manga que desea buscar:");
        buscarTitulo = sc.nextLine();
        
        return buscarTitulo;
    }
    
}
