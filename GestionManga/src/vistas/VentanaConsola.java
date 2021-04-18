package vistas;

import clases.ColeccionManga;
import java.util.Scanner;
import run.ControladorColeccion;
import interfaces.IVentana;
import interfaces.IVentanaMensaje;
import java.io.IOException;

/**
 * 
 * @author Angel
 */
public class VentanaConsola implements IVentana{
    private final VentanaBusquedaConsola busqueda;
    private final VentanaMensajeConsola mensaje;
    private Scanner sc = new Scanner (System.in);
    private ColeccionManga manga;
    private byte operacion;
    private ControladorColeccion controlador;
    
    public VentanaConsola(){
        manga = new ColeccionManga();
        busqueda = new VentanaBusquedaConsola();
        mensaje = new VentanaMensajeConsola();
    }
    
    @Override
    public VentanaBusquedaConsola getVentanaBusqueda() {
        return busqueda;
    }
    
    @Override
    public VentanaMensajeConsola getVentanaMensaje() {
        return mensaje;
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
    public void setColeccionManga(ColeccionManga manga) {
        this.manga = manga;
    }
    
    @Override
    public void setControlador(ControladorColeccion cc) {
        this.controlador = cc;
    }

    @Override
    public void mostrar() throws IOException{
        System.out.println("Introduce la operacion que quieres realizar:\n"
                + "1 para crear una coleccion,\n2 para borrarla,\n"
                + "3 para modificarla,\n4 para hacer una consulta por clave y "
                + "\n5 para hacer una consulta por el nombre\n6 para ver el "
                + "siguiente tomo\n7 para ver el anterior tomo\n8 para ver el "
                + "primer tomo\n9 para ver el ultimo tomo\n10 para salir");
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
                busqueda.buscarCodigo();
                controlador.notifiacion();
                mostrarManga();
                break;
            case 5: //Consulta por nombre
                busqueda.buscarNombre();
                controlador.notifiacion();
                mostrarManga();
                break;
            case 6:
                controlador.notifiacion();
                mostrarManga();
                break;
            case 7:
                controlador.notifiacion();
                mostrarManga();
                break;
            case 8:
                controlador.notifiacion();
                mostrarManga();
                break;
            case 9:
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
        
        System.out.println("Introduce el codigo del manga que desea modificar:");
        manga.setCodigo(sc.nextLine());
        
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