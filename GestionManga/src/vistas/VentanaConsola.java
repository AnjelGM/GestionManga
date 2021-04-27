package vistas;

import clases.ColeccionManga;
import java.util.Scanner;
import run.ControladorColeccion;
import interfaces.IVentana;
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
        while(true){
                    System.out.println("\nIntroduce la operacion que quieres realizar:\n"
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
                if(manga == null){
                    mensaje.setMensaje("No hay mangas");
                    mensaje.mostrar();
                }
                break;
            case 7:
                controlador.notifiacion();
                mostrarManga();
                if(manga == null){
                    mensaje.setMensaje("No hay mangas");
                    mensaje.mostrar();
                }
                break;
            case 8:
                controlador.notifiacion();
                mostrarManga();
                if(manga == null){
                    mensaje.setMensaje("No hay mangas");
                    mensaje.mostrar();
                }
                break;
            case 9:
                controlador.notifiacion();
                mostrarManga();
                if(manga == null){
                    mensaje.setMensaje("No hay mangas");
                    mensaje.mostrar();
                }
                break;
            }
            if(operacion == 10){
                break;
            }
        }
    }
    
    private void crear() {
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
        pedirEditorial();
        System.out.println("Introduce la demografía del manga:");
        pedirDemografia();
        System.out.println("Introduce el tipo de tomo que tiene el manga:");
        pedirTipoDeTomo();
        System.out.println("Introduce el numero de tomos que tiene el manga");
        try{
            String numero = sc.nextLine();
            manga.setNumeroTomos(Short.parseShort(numero));
        }catch(java.lang.NumberFormatException ex){
            System.out.println("Has introducido un dato que no es un numero\n"
                    + "El manga se va a crear con un unico tomo");
            manga.setNumeroTomos(1);
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
            manga.setTerminado(true);
        }else{
            manga.setTerminado(false);
        }
    }
    
    private void borrar() {        
        System.out.println("Introduce el codigo que deseas eliminar:");
        manga.setCodigo(sc.nextLine());
    }
    
    private void modificar() {
        char aux;
        
        
        try{
            System.out.println("Introduce el codigo del manga que desea modificar:");
            manga.setCodigo(sc.nextLine());

            System.out.println("Introduce la demografía del manga:");
            manga.setDemografia(sc.nextLine());
            System.out.println("Introduce el tipo de tomo que tiene el manga:");
            pedirTipoDeTomo();
            System.out.println("Introduce el numero de tomos que tiene el manga");
            String numero = sc.nextLine();
            manga.setNumeroTomos(Short.parseShort(numero));
            System.out.println("¿El manga está terminado? (S/N)");
            aux = sc.next().charAt(0);
            if(aux == 'S'){
                manga.setTerminado(true);
            }else{
                manga.setTerminado(false);
            }
        }catch(java.lang.NumberFormatException ex){
            sc.nextLine();
            System.out.println("Has introducido un dato que no es un numero\n"
                    + "La operacion se va a cancelar");
        }
        
    }
    
    private void pedirEditorial(){
        byte editorial;
        System.out.println("(1)Planeta\t(2)Norma\t(3)ECC\n"
                + "(4)Panini\t(5)Ivrea\t(6)Milky Way\n"
                + "(7)Fandogamia\t(8)Kodai\t(9)Satori\n"
                + "(10)Babylon\t(11)Arechi\t(12)Kitsune\n"
                + "(13)Ooso");
        try{
            editorial = Byte.parseByte(sc.nextLine());
        }catch(java.lang.NumberFormatException ex){
            System.out.println("Se seleccionará el primer dato");
            editorial = 1;
        }
        switch(editorial){
            case 1:
                manga.setEditorial("Planeta");
                break;
            case 2:
                manga.setEditorial("Norma");
                break;
            case 3:
                manga.setEditorial("ECC");
                break;
            case 4:
                manga.setEditorial("Panini");
                break;
            case 5:
                manga.setEditorial("Ivrea");
                break;
            case 6:
                manga.setEditorial("Milky Way");
                break;
            case 7:
                manga.setEditorial("Fandogamia");
                break;
            case 8:
                manga.setEditorial("Kodai");
                break;
            case 9:
                manga.setEditorial("Satori Ediciones");
                break;
            case 10:
                manga.setEditorial("Ediciones Babylon");
                break;
            case 11:
                manga.setEditorial("Arechi");
                break;
            case 12:
                manga.setEditorial("Kitsune Manga");
                break;
            case 13:
                manga.setEditorial("Ooso");
                break;
        }
    }
    
    private void pedirDemografia(){
        byte demografia;
        System.out.println("(1)Shonen\t(2)Shoujo\t(3)Seinen\n"
                + "(4)Josei");
        try{
            demografia = Byte.parseByte(sc.nextLine());
        }catch(java.lang.NumberFormatException ex){
            System.out.println("Se seleccionará el primer dato");
            demografia = 1;
        }
        switch(demografia){
            case 1:
                manga.setDemografia("Shonen");
                break;
            case 2:
                manga.setDemografia("Shoujo");
                break;
            case 3:
                manga.setDemografia("Seinen");
                break;
            case 4:
                manga.setDemografia("Josei");
                break;
        }
    }
    
    private void pedirTipoDeTomo(){
        byte tipoDeTomo;
        System.out.println("(1)Tankobon\t(2)Kanzenban\t(3)Shinsoban\n");
        try{
            tipoDeTomo = Byte.parseByte(sc.nextLine());
        }catch(java.lang.NumberFormatException ex){
            System.out.println("Se seleccionará el primer dato");
            tipoDeTomo = 1;
        }
        switch(tipoDeTomo){
            case 1:
                manga.setTipoDeTomo("Tankobon");
                break;
            case 2:
                manga.setTipoDeTomo("Kanzenban");
                break;
            case 3:
                manga.setTipoDeTomo("Shinsoban");
                break;
        }
    }
    
    @Override
    public void mostrarManga(){
        if(manga != null){
            System.out.println("Codigo: " + manga.getCodigo());
            System.out.println("Titulo: " + manga.getTitulo());
            System.out.println("Autor: " + manga.getAutor());
            System.out.println("Ilustrador: " + manga.getDibujo());
            System.out.println("Editorial: " + manga.getEditorial());
            System.out.println("Demografía: " + manga.getDemografia());
            System.out.println("Tipo de tomo: " + manga.getTipoDeTomo());
            System.out.println("Numeros de tomos: " + manga.getNumeroTomos());
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
}