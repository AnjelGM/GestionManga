package vistas;

import java.util.Scanner;
import run.ControladorColeccion;
import interfaces.IVentana;

/**
 * 
 * @author Angel
 */
public class VentanaConsola implements IVentana{
    Scanner sc = new Scanner (System.in);
    String codigo, titulo, autor, dibujo, demografia, tipoDeTomo, editorial;
    boolean terminado, edicionNormal, edicionEspecial;
    byte operacion;
    short numeroTomos;
    ControladorColeccion controlador;
    
    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public String getDibujo() {
        return dibujo;
    }

    @Override
    public String getDemografia() {
        return demografia;
    }

    @Override
    public String getEditorial() {
        return editorial;
    }

    @Override
    public String getTipoDeTomo() {
        return tipoDeTomo;
    }

    @Override
    public short getNumeroTomos() {
        return numeroTomos;
    }

    @Override
    public boolean getEdicionNormal() {
        return edicionNormal;
    }

    @Override
    public boolean getEdicionEspecial() {
        return edicionEspecial;
    }

    @Override
    public boolean getTerminado() {
        return terminado;
    }

    @Override
    public byte getOperacion() {
        return operacion;
    }

    @Override
    public void setControlador(ControladorColeccion cc) {
        this.controlador = cc;
    }
    
    @Override
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public void setDibujo(String dibujo) {
        this.dibujo = dibujo;
    }

    @Override
    public void setDemografia(String demografia) {
        this.demografia = demografia;
    }

    @Override
    public void setTipoDeTomo(String tipoDeTomo) {
        this.tipoDeTomo = tipoDeTomo;
    }

    @Override
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    @Override
    public void setEdicionNormal(boolean edicionNormal) {
        this.edicionNormal = edicionNormal;
    }

    @Override
    public void setEdicionEspecial(boolean edicionEspecial) {
        this.edicionEspecial = edicionEspecial;
    }

    @Override
    public void setNumeroTomos(short numeroTomos) {
        this.numeroTomos = numeroTomos;
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
                buscarCodigo();
                controlador.notifiacion();
                mostrarManga();
                break;
            case 5: //Consulta por nombre
                buscarNombre();
                controlador.notifiacion();
                mostrarManga();
                break;
        }
    }
    
    public void crear() {
        char aux;
        
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
    
    public void borrar() {
        char aux;
        
        System.out.println("Introduce el codigo que deseas eliminar:");
        codigo = sc.nextLine();
    }
    
    public void modificar() {
        char aux;
        String codigoOriginal;
        
        System.out.println("Introduce el codigo del manga que desea modificar:");
        codigoOriginal = sc.nextLine();
        
        System.out.println("Introduce la demografía del manga:");
        demografia = sc.nextLine();
        System.out.println("Introduce el tipo de tomo que tiene el manga:");
        tipoDeTomo = sc.nextLine();
        System.out.println("Introduce el numero de tomos que tiene el manga");
        numeroTomos = sc.nextShort();
        sc.nextLine();
        System.out.println("¿El manga está terminado? (S/N)");
        aux = sc.next().charAt(0);
        if(aux == 'S'){
            terminado = true;
        }
    }
    
    public void buscarCodigo() {
        System.out.println("Introduce el codigo del manga que desea buscar:");
        codigo = sc.nextLine();
    }
    
    public void buscarNombre(){
        System.out.println("Introduce el titulo del manga que desea buscar:");
        titulo = sc.nextLine();
    }
    
    public void mostrarManga(){
        System.out.println("Codigo: " + codigo);
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Ilustrador: " + dibujo);
        System.out.println("Editorial: " + editorial);
        System.out.println("Demografía: " + demografia);
        System.out.println("Tipo de tomo: " + tipoDeTomo);
        System.out.println("Numeros de tomos: " + numeroTomos);
        if(edicionNormal){
            System.out.println("El manga tiene una edicion normal");
        }
        if(edicionEspecial){
            System.out.println("El manga tiene una edicion especial");
        }
        if(terminado){
            System.out.println("El manga está terminado");
        }else{
            System.out.println("El manga no está terminado");
        }
    }
}
