/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

import clases.ColeccionManga;
import clases.MangaComparador;
import interfaces.IModelo;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ModeloArray implements IModelo{
    private short numeroRegistros, registroActual;
    private ColeccionManga[] mangas;
    private String mensaje;

    public ModeloArray() {
        mangas = new ColeccionManga[4];
        numeroRegistros = (short) (mangas.length - 1);
        registroActual = -1;
    }
    
    @Override
    public String getMensaje() {
        return mensaje;
    }
    
    @Override
    public void alta(ColeccionManga cm) throws IOException {
        ColeccionManga aux;
        for(byte count = 0;count < mangas.length;count++){
            aux = mangas[count];
            if(aux == null){
                mangas[count] = cm;
                mensaje = "Manga creado";
                break;
            }else if(aux.equals(cm)){
                mensaje = "El manga ya está creado";
                break;
            }else if(count == mangas.length -1 && ! aux.equals(cm)){
                mensaje = "No queda espacio en el array";
            }
        }
        Arrays.sort(mangas, Comparator.nullsLast(new MangaComparador()));
    }

    @Override
    public void baja(ColeccionManga cm) throws IOException {
        ColeccionManga aux;
        for(byte count = 0;count < mangas.length;count++){
            aux = mangas[count];
            if(aux.equals(cm)){
                mangas[count] = null;
                mensaje = "Manga eliminado";
                break;
            }else if(count == mangas.length -1 && (aux == null || !aux.equals(cm))){
                mensaje = "No se ha encontrado el manga";
            }
        }
        Arrays.sort(mangas, Comparator.nullsLast(new MangaComparador()));
    }

    @Override
    public void modificar(ColeccionManga cm) throws IOException {
        ColeccionManga aux;
        for(byte count = 0;count < mangas.length;count++){
            aux = mangas[count];
            if(aux.equals(cm)){
                mangas[count].setDemografia(cm.getDemografia());
                mangas[count].setTipoDeTomo(cm.getTipoDeTomo());
                mangas[count].setNumeroTomos(cm.getNumeroTomos());
                mangas[count].setTerminado(cm.isTerminado());
                mensaje = "Manga modificado";
                break;
            }else if(count == mangas.length -1 && (aux == null || !aux.equals(cm))){
                mensaje = "No se ha encontrado el manga";
            }
        }
        Arrays.sort(mangas, Comparator.nullsLast(new MangaComparador()));
    }

    @Override
    public ColeccionManga consultaClave(String clave) throws IOException {
        ColeccionManga aux;
        for(byte count = 0;count < mangas.length;count++){
            aux = mangas[count];
            if(aux.getCodigo().equals(clave)){
                mensaje = "";
                registroActual = count;
                return aux;
            }else if(aux == null ){
                mensaje = "No se ha encontrado el manga";
            }else if(count == mangas.length -1 && !aux.getCodigo().equals(clave)){
                mensaje = "No se ha encontrado el manga";
            }
        }
        return null;
    }

    @Override
    public ColeccionManga consultaNombre(String nombre) throws IOException {
        ColeccionManga aux;
        for(byte count = 0;count < mangas.length;count++){
            aux = mangas[count];
            if(aux.getTitulo().equals(nombre)){
                mensaje = "";
                registroActual = count;
                return aux;
            }else if(aux == null ){
                mensaje = "No se ha encontrado el manga";
            }else if(count == mangas.length -1 && !aux.getTitulo().equals(nombre)){
                mensaje = "No se ha encontrado el manga";
            }
        }
        return null;
    }

    @Override
    public ColeccionManga siguiente() throws IOException {
        if(registroActual == numeroRegistros){
            registroActual--;
        }else if(mangas[registroActual] == null){
            registroActual -= 2;
        }
        registroActual++;
        if(mangas[registroActual] == null){
            registroActual--;
        }
        return mangas[registroActual];
    }

    @Override
    public ColeccionManga anterior() throws IOException {
        if(registroActual == 0){
            registroActual++;
        }
        registroActual--;
        if(mangas[registroActual] == null){
            registroActual--;
        }
        return mangas[registroActual];
    }

    @Override
    public ColeccionManga primero() throws IOException {
        registroActual = 0;
        return mangas[registroActual];
    }

    @Override
    public ColeccionManga ultimo() throws IOException {
        ColeccionManga aux;
        registroActual = 0;
        
        for(byte count = 0;count < mangas.length;count++){
            aux = mangas[count];
            if(aux == null){
                break;
            }
            registroActual++;
        }
        return mangas[registroActual];
    }
}