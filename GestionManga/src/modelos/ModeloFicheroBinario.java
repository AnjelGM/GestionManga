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
        File fichero1 = new File("auxiliar");
        File fichero2 = new File(NOMBREFICHERO);
        
        FileOutputStream fileout = new FileOutputStream(fichero1, true);
        FileInputStream filein = new FileInputStream(fichero2);
        
        ObjectOutputStream temporal = new ObjectOutputStream(fileout); 
        ObjectInputStream original = new ObjectInputStream(filein);
        try {
            while(true){
                manga = (ColeccionManga) original.readObject();
                if(!manga.equals(cm)){
                    temporal.writeObject(manga);
                }
            }
        } catch (EOFException eo) {
            System.out.println("Se ha eliminado el manga");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(ColeccionManga cm) throws IOException{
        
    }

    @Override
    public ColeccionManga consultaClave(String clave) {
        return null;
    }

    @Override
    public ColeccionManga consultaNombre(String nombre) {
        return null;
    }
    
}
