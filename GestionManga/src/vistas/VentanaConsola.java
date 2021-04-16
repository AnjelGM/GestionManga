package vistas;

import clases.ColeccionManga;
import java.util.Scanner;
import run.ControladorColeccion;
import interfaces.IVentana;

/**
 * 
 * @author Angel
 */
public class VentanaConsola implements IVentana{
    private final VentanaBusquedaConsola busqueda = new VentanaBusquedaConsola();
    private Scanner sc = new Scanner (System.in);
    private ColeccionManga manga;
    private byte operacion;
    private ControladorColeccion controlador;

    @Override
    public VentanaBusquedaConsola getVentanaBusquedaConsola() {
        return busqueda;
    }
    
    @Override
    public ColeccionManga getColeccionManga() {
        return manga;
    }
    
    @Override
    public byte getOperacion() {
        return operacion;
    }
    
    @Override
    public void setColeccionManga(ColeccionManga Manga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setControlador(ControladorColeccion cc) {
        this.controlador = cc;
    }

    @Override
    public void mostrar(){
        System.out.println("Introduce la operacion que quieres realizar: "
                + "1 para crear una coleccion,\n 2 para borrarla,\n "
                + "3 para modificarla,\n 4 para hacer una consulta por clave y "
                + "\n5 para hacer una consulta por el nombre");
        this.operacion = sc.nextByte();
        sc.nextLine();
        switch(operacion){
            case 1: //Alta
                crear();
                controlador.notifiacion();
                break;
            case 2: //Baja
                borrar();
                controlador.notifiacion();
                break;
            case 3: //Modificacion
                modificar();
                controlador.notifiacion();
                break;
            case 4: //Consulta por clave
                controlador.notifiacion();
                mostrarManga();
                break;
            case 5: //Consulta por nombre
                controlador.notifiacion();
                mostrarManga();
                break;
        }
    }
    
    public void crear() {
        char aux;
        
        System.out.println("Introduce el codigo del manga:");
        manga.setCodigo(sc.nextLine());
        System.out.println("Introduce el titulo del manga:");
        manga.setTitulo(sc.nextLine());
        System.out.println("Introduce el autor del manga:");
        manga.setAutor(sc.nextLine());
        System.out.println("Introduce el artista(ilustrador) del manga:");
        manga.setDibujo(sc.nextLine());
        System.out.println("Introduce la editorial del manga:");
        manga.setEditorial(sc.nextLine());
        System.out.println("Introduce la demografía del manga:");
        manga.setDemografia(sc.nextLine());
        System.out.println("Introduce el tipo de tomo que tiene el manga:");
        manga.setTipoDeTomo(sc.nextLine());
        System.out.println("Introduce el numero de tomos que tiene el manga");
        manga.setNumeroTomos(sc.nextShort());
        sc.nextLine();
        System.out.println("¿EL manga tiene edición normal? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            manga.setEdicionNormal(true);
        }else{
            manga.setEdicionNormal(false);
        }
        System.out.println("¿EL manga tiene edición especial? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            manga.setEdicionEspecial(true);
        }else{
            manga.setEdicionEspecial(false);
        }
        System.out.println("¿El manga está terminado? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            if(aux == 'S'){
            manga.setTerminado(true);
        }else{
            manga.setTerminado(false);
        }
        }
    }
    
    public void borrar() {
        char aux;
        
        System.out.println("Introduce el codigo que deseas eliminar:");
        manga.setCodigo(sc.nextLine());
    }
    
    public void modificar() {
        char aux;
        String codigoOriginal;
        
        System.out.println("Introduce el codigo del manga que desea modificar:");
        codigoOriginal = sc.nextLine();
        
        System.out.println("Introduce la demografía del manga:");
        manga.setDemografia(sc.nextLine());
        System.out.println("Introduce el tipo de tomo que tiene el manga:");
        manga.setTipoDeTomo(sc.nextLine());
        System.out.println("Introduce el numero de tomos que tiene el manga");
        manga.setNumeroTomos(sc.nextShort());
        sc.nextLine();
        System.out.println("¿El manga está terminado? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            if(aux == 'S'){
            manga.setTerminado(true);
        }else{
            manga.setTerminado(false);
        }
        }
    }
    
    
    
    public void mostrarManga(){
        System.out.println("Codigo: " + manga.getCodigo());
        System.out.println("Titulo: " + manga.getTitulo());
        System.out.println("Autor: " + manga.getAutor());
        System.out.println("Ilustrador: " + manga.getDibujo());
        System.out.println("Editorial: " + manga.getEditorial());
        System.out.println("Demografía: " + manga.getDemografia());
        System.out.println("Tipo de tomo: " + manga.getTipoDeTomo());
        System.out.println("Numeros de tomos: " + manga.getNumeroTomos());
        if(manga.isEdicionNormal()){
            System.out.println("El manga tiene una edicion normal");
        }
        if(manga.isEdicionEspecial()){
            System.out.println("El manga tiene una edicion especial");
        }
        if(manga.isTerminado()){
            System.out.println("El manga está terminado");
        }else{
            System.out.println("El manga no está terminado");
        }
    }
}