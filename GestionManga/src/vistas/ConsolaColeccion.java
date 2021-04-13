package vistas;

import interfaces.IVentanaColeccion;
import java.util.Scanner;
import run.ControladorColeccion;

/**
 * 
 * @author Angel
 */
public class ConsolaColeccion implements IVentanaColeccion{
    String codigo, titulo, autor, dibujo, demografia, tipoDeTomo, editorial;
    boolean terminado, edicionNormal, edicionEspecial;
    short numeroTomos;
    ControladorColeccion controlador;
    
    @Override
    public String codigo() {
        return codigo;
    }

    @Override
    public String titulo() {
        return titulo;
    }

    @Override
    public String autor() {
        return autor;
    }

    @Override
    public String dibujo() {
        return dibujo;
    }

    @Override
    public String demografia() {
        return demografia;
    }

    @Override
    public String editorial() {
        return editorial;
    }

    @Override
    public String tipoDeTomo() {
        return tipoDeTomo;
    }

    @Override
    public short numeroTomos() {
        return numeroTomos;
    }

    @Override
    public boolean edicionNormal() {
        return edicionNormal;
    }

    @Override
    public boolean edicionEspecial() {
        return edicionEspecial;
    }

    @Override
    public boolean terminado() {
        return terminado;
    }

    @Override
    public void setControlador(ControladorColeccion cc) {
        this.controlador = cc;
    }

    @Override
    public void mostrar(){
        
    }
    
    public void crear() {
        char aux;
        
        Scanner sc = new Scanner (System.in);
        System.out.println("Introduce el codigo del manga:");
        codigo = sc.nextLine();
        System.out.println("Introduce el titulo del manga:");
        titulo = sc.nextLine();
        System.out.println("Introduce el autor del manga:");
        autor = sc.nextLine();
        System.out.println("Introduce el artista(ilustrador) del manga:");
        dibujo = sc.nextLine();
        System.out.println("Introduce la editorial del manga:");
        editorial = sc.nextLine();
        System.out.println("Introduce la demografía del manga:");
        demografia = sc.nextLine();
        System.out.println("Introduce el tipo de tomo que tiene el manga:");
        tipoDeTomo = sc.nextLine();
        System.out.println("Introduce el numero de tomos que tiene el manga");
        numeroTomos = sc.nextShort();
        sc.nextLine();
        System.out.println("¿EL manga tiene edición normal? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            edicionNormal = true;
        }
        System.out.println("¿EL manga tiene edición especial? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            edicionEspecial = true;
        }
        System.out.println("¿El manga está terminado? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            terminado = true;
        }
    }
    
    public void modificar() {
        char aux;
        Scanner sc = new Scanner (System.in);
        String codigoOriginal;
        
        System.out.println("Introduce el codigo del manga que desea modificar:");
        codigoOriginal = sc.nextLine();
        
        System.out.println("Introduce el codigo del manga:");
        codigo = sc.nextLine();
        System.out.println("Introduce el titulo del manga:");
        titulo = sc.nextLine();
        System.out.println("Introduce el autor del manga:");
        autor = sc.nextLine();
        System.out.println("Introduce el artista(ilustrador) del manga:");
        dibujo = sc.nextLine();
        System.out.println("Introduce la editorial del manga:");
        editorial = sc.nextLine();
        System.out.println("Introduce la demografía del manga:");
        demografia = sc.nextLine();
        System.out.println("Introduce el tipo de tomo que tiene el manga:");
        tipoDeTomo = sc.nextLine();
        System.out.println("Introduce el numero de tomos que tiene el manga");
        numeroTomos = sc.nextShort();
        sc.nextLine();
        System.out.println("¿EL manga tiene edición normal? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            edicionNormal = true;
        }
        System.out.println("¿EL manga tiene edición especial? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            edicionEspecial = true;
        }
        System.out.println("¿El manga está terminado? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            terminado = true;
        }
    }
    
    public void buscar() {
        char aux;
        
        Scanner sc = new Scanner (System.in);
        System.out.println("Introduce el codigo del manga que desea buscar:");
        codigo = sc.nextLine();
        
        
        
    }
}
