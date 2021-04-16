package modelos;

import clases.ColeccionManga;
import interfaces.IModelo;
import java.io.*;
import java.util.logging.*;
import run.ControladorColeccion;

/**
 *
 * @author Angel
 */
public class ModeloFicheroBinario implements IModelo {

    private static final String NOMBREFICHERO = "ColeccionesDeMangas.dat";
    private short numeroRegistros, registroActual;
    private ControladorColeccion controlador;

    public ModeloFicheroBinario() {
        numeroRegistros = contarFicheros();
        registroActual = 0;
    }

    public void setControlador(ControladorColeccion controlador) {
        this.controlador = controlador;
    }

    @Override
    public void alta(ColeccionManga cm) throws IOException {
        ColeccionManga manga;
        File fichero1 = null;
        FileOutputStream fileout;
        ObjectOutputStream temporal = null;
        File fichero2 = null;
        FileInputStream filein;
        ObjectInputStream original = null;
        
        try {
            // Cuando lo abra se borraran todo el contenido creando lo nuevo
            fichero1 = new File("auxiliar.dat");
            fileout = new FileOutputStream(fichero1, true);
            temporal = new ObjectOutputStream(fileout);

            fichero2 = new File(NOMBREFICHERO);
            filein = new FileInputStream(fichero2);
            original = new ObjectInputStream(filein);
            
            while (true) {
                manga = (ColeccionManga) original.readObject();
                temporal.writeObject(manga);
            }
        } catch (EOFException eo) {
            temporal.writeObject(cm);
            temporal.close();
            original.close();
            fichero2.delete();
            fichero1.renameTo(fichero2);
            numeroRegistros++;
        } catch (FileNotFoundException ex) {
            temporal.writeObject(cm);
            temporal.close();
            fichero1.renameTo(fichero2);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        controlador.mandarMensaje("Fichero creado");
    }

    @Override
    public void baja(ColeccionManga cm) throws IOException {
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
            while (true) {
                manga = (ColeccionManga) original.readObject();
                if (!manga.equals(cm)) {
                    temporal.writeObject(manga);
                    mensaje = "Se ha eliminado el manga";
                    numeroRegistros--;
                }
            }
        } catch (EOFException eo) {
            //El mensaje de esta excepcion se manda abajo
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        controlador.mandarMensaje(mensaje);
    }

    @Override
    public void modificar(ColeccionManga cm) throws IOException {
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
            while (true) {
                manga = (ColeccionManga) original.readObject();
                if (manga.equals(cm)) {
                    manga.setDemografia(cm.getDemografia());
                    manga.setTipoDeTomo(cm.getTipoDeTomo());
                    manga.setNumeroTomos(cm.getNumeroTomos());
                    manga.setTerminado(cm.isTerminado());
                    temporal.writeObject(manga);
                    mensaje = "Se ha terminado la modificacion el manga";
                } else {
                    temporal.writeObject(manga);
                }
            }
        } catch (EOFException eo) {
            //El mensaje de esta excepcion se manda abajo
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        controlador.mandarMensaje(mensaje);
    }

    @Override
    public ColeccionManga consultaClave(String clave) throws IOException {
        ColeccionManga manga;

        File fichero2 = new File(NOMBREFICHERO);

        FileInputStream filein = new FileInputStream(fichero2);

        ObjectInputStream original = new ObjectInputStream(filein);

        try {
            short count = 1;
            while (true) {
                manga = (ColeccionManga) original.readObject();
                if (manga.getCodigo().equals(clave)) {
                    registroActual = count;
                    return manga;
                }
                count++;
            }
        } catch (EOFException eo) {
            controlador.mandarMensaje("No se ha encontrado el manga");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ColeccionManga consultaNombre(String nombre) throws IOException {
        ColeccionManga manga;

        File fichero2 = new File(NOMBREFICHERO);

        FileInputStream filein = new FileInputStream(fichero2);

        ObjectInputStream original = new ObjectInputStream(filein);

        try {
            while (true) {
                short count = 1;
                manga = (ColeccionManga) original.readObject();
                if (manga.getTitulo().equals(nombre)) {
                    registroActual = count;
                    return manga;
                }
                count++;
            }
        } catch (EOFException eo) {
            controlador.mandarMensaje("No se ha encontrado el manga");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ColeccionManga siguiente() throws IOException {
        ColeccionManga manga = null;

        File fichero2 = new File(NOMBREFICHERO);

        FileInputStream filein = new FileInputStream(fichero2);

        ObjectInputStream original = new ObjectInputStream(filein);

        if (registroActual != numeroRegistros) {
            registroActual++;
        } else if (registroActual > numeroRegistros) {
            registroActual = numeroRegistros;
        }

        try {
            for (short count = 1; count < registroActual; count++) {
                manga = (ColeccionManga) original.readObject();
            }

        } catch (EOFException eo) {
            controlador.mandarMensaje("No se ha encontrado el manga");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manga;
    }

    @Override
    public ColeccionManga anterior() throws IOException {
        ColeccionManga manga = null;

        File fichero2 = new File(NOMBREFICHERO);

        FileInputStream filein = new FileInputStream(fichero2);

        ObjectInputStream original = new ObjectInputStream(filein);

        if (registroActual != 1) {
            registroActual--;
        }

        try {
            for (short count = 1; count < registroActual; count++) {
                manga = (ColeccionManga) original.readObject();
            }

        } catch (EOFException eo) {
            controlador.mandarMensaje("No se ha encontrado el manga");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manga;
    }

    @Override
    public ColeccionManga primero() throws IOException {
        ColeccionManga manga = null;

        File fichero2 = new File(NOMBREFICHERO);

        FileInputStream filein = new FileInputStream(fichero2);

        ObjectInputStream original = new ObjectInputStream(filein);

        registroActual = 1;

        try {
            for (short count = 1; count < registroActual; count++) {
                manga = (ColeccionManga) original.readObject();
            }

        } catch (EOFException eo) {
            controlador.mandarMensaje("No se ha encontrado el manga");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manga;
    }

    @Override
    public ColeccionManga ultimo() throws IOException {
        ColeccionManga manga = null;

        File fichero2 = new File(NOMBREFICHERO);

        FileInputStream filein = new FileInputStream(fichero2);

        ObjectInputStream original = new ObjectInputStream(filein);

        registroActual = numeroRegistros;

        try {
            for (short count = 1; count < registroActual; count++) {
                manga = (ColeccionManga) original.readObject();
            }

        } catch (EOFException eo) {
            controlador.mandarMensaje("No se ha encontrado el manga");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manga;
    }
    
    public short contarFicheros(){
        ColeccionManga manga;
        short count = 0;
        
        try {
            File fichero2 = new File(NOMBREFICHERO);

            FileInputStream filein = new FileInputStream(fichero2);

            ObjectInputStream original = new ObjectInputStream(filein);
            
            while (true) {
                manga = (ColeccionManga) original.readObject();
                registroActual = count;
                count++;
            }
        } catch (EOFException eo) {
        } catch (FileNotFoundException ex) {
            return 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModeloFicheroBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
