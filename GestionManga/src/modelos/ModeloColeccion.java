package modelos;

import clases.ColeccionManga;
import interfaces.IModelo;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Angel
 */
public class ModeloColeccion implements IModelo{
    
    private short numeroRegistros, registroActual;
    private ArrayList<ColeccionManga> mangas;
    private String mensaje;

    public ModeloColeccion() {
        mangas = new ArrayList<ColeccionManga>();
        numeroRegistros = 0;
        registroActual = -1;
    }
    
    @Override
    public String getMensaje() {
        return mensaje;
    }
    
    @Override
    public void alta(ColeccionManga cm) throws IOException {
        if(mangas.contains(cm)){
            mensaje = "El manga ya existe";
        }else{
            mangas.add(cm);
            mensaje = "Manga creado";
            numeroRegistros++;
        }
    }

    @Override
    public void baja(ColeccionManga cm) throws IOException {
        if(mangas.contains(cm)){
            mangas.remove(cm);
            mensaje = "Manga eliminado";
            numeroRegistros--;
        }else{
            mensaje = "No se ha encontrado el manga";
        }
    }

    @Override
    public void modificar(ColeccionManga cm) throws IOException {
        ColeccionManga aux;
        if(mangas.contains(cm)){
            for(short count = 0;count < numeroRegistros;count++){
                if(mangas.get(count).equals(cm)){
                    mangas.get(count).setDemografia(cm.getDemografia());
                    mangas.get(count).setTipoDeTomo(cm.getTipoDeTomo());
                    mangas.get(count).setNumeroTomos(cm.getNumeroTomos());
                    mangas.get(count).setTerminado(cm.isTerminado());
                }
            }
            mensaje = "Manga modificado";
        }else{
            mensaje = "No se ha encontrado el manga";
        }
    }

    @Override
    public ColeccionManga consultaClave(String clave) throws IOException {
        short count = 0;
        try{
            while(true){
            if(mangas.get(count).getCodigo().equals(clave)){
                registroActual = count;
                return mangas.get(count);
            }
            count++;
        }
        }catch(java.lang.IndexOutOfBoundsException IOOBE){
            mensaje = "No se ha encontrado el manga";
        }
        return null;
    }

    @Override
    public ColeccionManga consultaNombre(String nombre) throws IOException {
        short count = 0;
        try{
            while(true){
            if(mangas.get(count).getTitulo().equals(nombre)){
                registroActual = count;
                return mangas.get(count);
            }
            count++;
        }
        }catch(java.lang.IndexOutOfBoundsException IOOBE){
            mensaje = "No se ha encontrado el manga";
        }
        return null;
    }

    @Override
    public ColeccionManga siguiente() throws IOException {
        if(registroActual == numeroRegistros){
            registroActual--;
        }else if(registroActual > numeroRegistros){
            registroActual = (short) (numeroRegistros - 2);
        }
        registroActual++;
        return mangas.get(registroActual);
    }

    @Override
    public ColeccionManga anterior() throws IOException {
        if(registroActual == 0){
            registroActual++;
        }
        registroActual--;
        return mangas.get(registroActual);
    }

    @Override
    public ColeccionManga primero() throws IOException {
        registroActual = 0;
        return mangas.get(registroActual);
    }

    @Override
    public ColeccionManga ultimo() throws IOException {
        registroActual = (short) (mangas.size() - 1);
        return mangas.get(registroActual);
    }
    
}
