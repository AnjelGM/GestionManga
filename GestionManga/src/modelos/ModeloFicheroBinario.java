package modelos;

import clases.ColeccionManga;
import interfaces.IModelo;
import java.io.*;
import java.util.logging.*;

/**
 * 
 * @author Angel
 */
public class ModeloFicheroBinario implements IModelo {
    private static final String NOMBREFICHERO = "ColeccionesDeMangas.dat";
    
    @Override
    public void alta(ColeccionManga cm) throws IOException{
        ColeccionManga manga;
        File fichero1 = new File("auxiliar.dat");
        File fichero2 = new File(NOMBREFICHERO);
        
        FileOutputStream fileout = new FileOutputStream(fichero1, true);
        FileInputStream filein = new FileInputStream(fichero2);
        
        ObjectOutputStream temporal = new ObjectOutputStream(fileout); 
        ObjectInputStream original = new ObjectInputStream(filein);
        
        fichero2.createNewFile();
        try {
            while(true){
                manga = (ColeccionManga) original.readObject();
                temporal.writeObject(manga);
            }
        } catch (EOFException eo) {
            temporal.writeObject(cm);
            temporal.close();
            fichero1.renameTo(fichero2);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void baja(ColeccionManga cm) throws IOException{
        ColeccionManga manga;
        String mensaje;
        
        File fichero1 = new File("auxiliar");
        File fichero2 = new File(NOMBREFICHERO);
        
        FileOutputStream fileout = new FileOutputStream(fichero1, true);
        FileInputStream filein = new FileInputStream(fichero2);
        
        ObjectOutputStream temporal = new ObjectOutputStream(fileout); 
        ObjectInputStream original = new ObjectInputStream(filein);
        
        mensaje = "No se ha encontrado el manga";
        
        try {
            while(true){
                manga = (ColeccionManga) original.readObject();
                if(!manga.equals(cm)){
                    temporal.writeObject(manga);
                    mensaje = "Se ha eliminado el manga";
                }
            }
        } catch (EOFException eo) {
            System.out.println(mensaje);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(ColeccionManga cm) throws IOException{
        ColeccionManga manga;
        String mensaje;
        
        File fichero1 = new File("auxiliar");
        File fichero2 = new File(NOMBREFICHERO);
        
        FileOutputStream fileout = new FileOutputStream(fichero1, true);
        FileInputStream filein = new FileInputStream(fichero2);
        
        ObjectOutputStream temporal = new ObjectOutputStream(fileout); 
        ObjectInputStream original = new ObjectInputStream(filein);
        
        mensaje = "No se ha encontrado el manga";
        
        try {
            while(true){
                manga = (ColeccionManga) original.readObject();
                if(manga.equals(cm)){
                    manga.setDemografia(cm.getDemografia());
                    manga.setTipoDeTomo(cm.getTipoDeTomo());
                    manga.setNumeroTomos(cm.getNumeroTomos());
                    manga.setTerminado(cm.isTerminado());
                    temporal.writeObject(manga);
                    mensaje = "Se ha terminado la modificacion el manga";
                }else{
                    temporal.writeObject(manga);
                }
            }
        } catch (EOFException eo) {
            System.out.println(mensaje);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ColeccionManga consultaClave(String clave) throws IOException{
        ColeccionManga manga;
        
        File fichero2 = new File(NOMBREFICHERO);
        
        FileInputStream filein = new FileInputStream(fichero2);
        
        ObjectInputStream original = new ObjectInputStream(filein);
        
        try {
            while(true){
                manga = (ColeccionManga) original.readObject();
                if(manga.getCodigo().equals(clave)){
                    return manga;
                }
            }
        } catch (EOFException eo) {
            System.out.println("No se ha encontrado el manga");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ColeccionManga consultaNombre(String nombre) throws IOException{
        ColeccionManga manga;
        
        File fichero2 = new File(NOMBREFICHERO);
        
        FileInputStream filein = new FileInputStream(fichero2);
        
        ObjectInputStream original = new ObjectInputStream(filein);
        
        try {
            while(true){
                manga = (ColeccionManga) original.readObject();
                if(manga.getTitulo().equals(nombre)){
                    return manga;
                }
            }
        } catch (EOFException eo) {
            System.out.println("No se ha encontrado el manga");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
