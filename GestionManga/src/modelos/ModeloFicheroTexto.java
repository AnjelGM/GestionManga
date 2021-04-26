package modelos;

import clases.ColeccionManga;
import interfaces.IModelo;
import java.io.*;
import java.util.StringTokenizer;

public class ModeloFicheroTexto implements IModelo{
    
    private static final String NOMBREFICHERO = "ColeccionesDeMangas.txt";
    private short numeroRegistros, registroActual;
    private String mensaje;

    public ModeloFicheroTexto() {
        numeroRegistros = contarRegistros();
        registroActual = 0;
        mensaje = "";
    }
    
    @Override
    public void alta(ColeccionManga cm) throws IOException{
        String mangaString;
        File fichero1 = new File("auxiliar.txt");
        FileWriter escribir;
        BufferedWriter temporal = null;
        File fichero2 = new File(NOMBREFICHERO);
        FileReader leer;
        BufferedReader original = null;
        
        try {
            escribir = new FileWriter(fichero1);
            temporal = new BufferedWriter(escribir);
             
            leer = new FileReader(fichero2);
            original = new BufferedReader(leer);
            
            while(true){
                mangaString = original.readLine();
                
                if(mangaString == null){
                    temporal.write(cm.toString());
                    temporal.newLine();
                    
                    temporal.close();
                    original.close();
                    fichero2.delete();
                    fichero1.renameTo(fichero2);
                    numeroRegistros++;
                    break;
                }else if(StringAManga(mangaString).equals(cm)){
                    mensaje = "El manga ya existe";
                    temporal.close();
                    original.close();
                    fichero1.delete();
                    break;
                }
                temporal.write(mangaString);
                temporal.newLine();
            }
        } catch (FileNotFoundException ex) {
            temporal.write(cm.toString());
            temporal.newLine();
            temporal.close();
            fichero1.renameTo(fichero2);
            numeroRegistros++;
        }
        mensaje = "Manga creado";
    }

    @Override
    public void baja(ColeccionManga cm) throws IOException {
        String mangaString;
        File fichero1 = new File("auxiliar.txt");
        FileWriter escribir;
        BufferedWriter temporal = null;
        File fichero2 = new File(NOMBREFICHERO);
        FileReader leer;
        BufferedReader original = null;
        
        try {
            escribir = new FileWriter(fichero1);
            temporal = new BufferedWriter(escribir);
             
            leer = new FileReader(fichero2);
            original = new BufferedReader(leer);
            
            mensaje = "No se ha encontrado el manga";
            
            while(true){
                mangaString = original.readLine();
                
                if(mangaString == null){
                    temporal.close();
                    original.close();
                    fichero2.delete();
                    fichero1.renameTo(fichero2);
                    break;
                }
                
                if(!StringAManga(mangaString).equals(cm)){
                    temporal.write(mangaString);
                    temporal.newLine();
                }else{
                    mensaje = "Se ha eliminado el manga";
                    numeroRegistros--;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        }
    }

    @Override
    public void modificar(ColeccionManga cm) throws IOException {
        ColeccionManga manga;
        String mangaString;
        File fichero1 = new File("auxiliar.txt");
        FileWriter escribir;
        BufferedWriter temporal = null;
        File fichero2 = new File(NOMBREFICHERO);
        FileReader leer;
        BufferedReader original = null;
        
        try {
            escribir = new FileWriter(fichero1);
            temporal = new BufferedWriter(escribir);
             
            leer = new FileReader(fichero2);
            original = new BufferedReader(leer);
            
            mensaje = "No se ha encontrado el manga";
            
            while(true){
                mangaString = original.readLine();
                
                if(mangaString == null){
                    temporal.close();
                    original.close();
                    fichero2.delete();
                    fichero1.renameTo(fichero2);
                    break;
                }
                if(!StringAManga(mangaString).equals(cm)){
                    temporal.write(mangaString);
                    temporal.newLine();
                }else{
                    manga = StringAManga(mangaString);
                    manga.setDemografia(cm.getDemografia());
                    manga.setTipoDeTomo(cm.getTipoDeTomo());
                    manga.setNumeroTomos(cm.getNumeroTomos());
                    manga.setTerminado(cm.isTerminado());
                    temporal.write(manga.toString());
                    temporal.newLine();
                    mensaje = "Se ha modificado el manga";
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        }
    }

    @Override
    public ColeccionManga consultaClave(String clave) throws IOException {
        ColeccionManga manga;
        String mangaString;
        File fichero1 = new File(NOMBREFICHERO);
        FileReader leer = new FileReader(fichero1);
        BufferedReader original = new BufferedReader(leer);
        
        try{
            short count = 1;
            while(true){
                mangaString = original.readLine();
                if(mangaString == null){
                    mensaje = "No se ha encontrado el manga";
                    break;
                }
                manga = StringAManga(mangaString);
                if (manga.getCodigo().equals(clave)) {
                    registroActual = count;
                    original.close();
                    return manga;
                }
                count++;
            }
        }catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        }
        return null;
    }

    @Override
    public ColeccionManga consultaNombre(String nombre) throws IOException {
        ColeccionManga manga;
        String mangaString;
        File fichero1 = new File(NOMBREFICHERO);
        FileReader leer = new FileReader(fichero1);
        BufferedReader original = new BufferedReader(leer);
        
        try{
            short count = 1;
            while(true){
                mangaString = original.readLine();
                if(mangaString == null){
                    mensaje = "No se ha encontrado el manga";
                    break;
                }
                manga = StringAManga(mangaString);
                if (manga.getTitulo().equals(nombre)) {
                    registroActual = count;
                    original.close();
                    return manga;
                }
                count++;
            }
        }catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        }
        return null;
    }

    @Override
    public ColeccionManga siguiente() throws IOException {
        ColeccionManga manga = null;
        String mangaString;
        File fichero1 = new File(NOMBREFICHERO);
        FileReader leer = new FileReader(fichero1);
        BufferedReader original = new BufferedReader(leer);
        
        if (registroActual != numeroRegistros) {
            registroActual++;
        } else if (registroActual > numeroRegistros) {
            registroActual = numeroRegistros;
        }
        
        try{
            for(short count = 0; count < registroActual; count++){
                mangaString = original.readLine();
                manga = StringAManga(mangaString);
            }
            original.close();
        }catch (FileNotFoundException ex) {
            System.out.println("No hay mangas");
        }
        return manga;
    }

    @Override
    public ColeccionManga anterior() throws IOException {
        ColeccionManga manga = null;
        String mangaString;
        File fichero1 = new File(NOMBREFICHERO);
        FileReader leer = new FileReader(fichero1);
        BufferedReader original = new BufferedReader(leer);
        
        if (registroActual != 1) {
            registroActual--;
        }else if(registroActual < 1){
            registroActual = 1;
        }
        
        try{
            for(short count = 0; count < registroActual; count++){
                mangaString = original.readLine();
                manga = StringAManga(mangaString);
            }
            original.close();
        }catch (FileNotFoundException ex) {
            System.out.println("No hay mangas");
        }
        return manga;
    }

    @Override
    public ColeccionManga primero() throws IOException {
        ColeccionManga manga = null;
        String mangaString;
        File fichero1 = new File(NOMBREFICHERO);
        FileReader leer = new FileReader(fichero1);
        BufferedReader original = new BufferedReader(leer);
        
        registroActual = 1;
        
        try{
            for(short count = 0; count < registroActual; count++){
                mangaString = original.readLine();
                manga = StringAManga(mangaString);
            }
            original.close();
        }catch (FileNotFoundException ex) {
            System.out.println("No hay mangas");
        }
        return manga;
    }

    @Override
    public ColeccionManga ultimo() throws IOException {
        ColeccionManga manga = null;
        String mangaString;
        File fichero1 = new File(NOMBREFICHERO);
        FileReader leer = new FileReader(fichero1);
        BufferedReader original = new BufferedReader(leer);
        
        registroActual = numeroRegistros;
        
        try{
            for(short count = 0; count < registroActual; count++){
                mangaString = original.readLine();
                manga = StringAManga(mangaString);
            }
            original.close();
        }catch (FileNotFoundException ex) {
            System.out.println("No hay mangas");
        }
        return manga;
    }

    @Override
    public String getMensaje() {
        return mensaje;
    }
    
    private short contarRegistros() {
        short count = 0;
        String mangaString;
        File fichero1 = new File(NOMBREFICHERO);
        FileReader leer = null;
        BufferedReader original;
        
        if (registroActual != 1) {
            registroActual--;
        }
        
        try{
            leer = new FileReader(fichero1);
            original = new BufferedReader(leer);
            while(true){
                mangaString = original.readLine();
                if(mangaString == null){
                    original.close();
                    break;
                }
                count++;
            }
        }catch (FileNotFoundException ex) {
            return 0;
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return count;
    }
    
    private ColeccionManga StringAManga(String mangaString){
        ColeccionManga salida = new ColeccionManga();
        StringTokenizer mangaDividido = new StringTokenizer(mangaString, "|"); //Son 10 atributos del manga
        
        salida.setCodigo(mangaDividido.nextToken());
        salida.setTitulo(mangaDividido.nextToken());
        salida.setAutor(mangaDividido.nextToken());
        salida.setDibujo(mangaDividido.nextToken());
        salida.setEditorial(mangaDividido.nextToken());
        salida.setTipoDeTomo(mangaDividido.nextToken());
        salida.setDemografia(mangaDividido.nextToken());
        salida.setNumeroTomos(Integer.parseInt(mangaDividido.nextToken()));
        if(mangaDividido.nextToken().equals("true")){
            salida.setEdicionEspecial(true);
        }else{
            salida.setEdicionEspecial(false);
        }
        if(mangaDividido.nextToken().equals("true")){
            salida.setTerminado(true);
        }else{
            salida.setTerminado(false);
        }
        return salida;
    }    
}