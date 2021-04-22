package modelos;

import clases.ColeccionManga;
import interfaces.IModelo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * 
 * @author Angel
 */
public class ModeloMapa implements IModelo{
    private short numeroRegistros, registroActual;
    private LinkedHashMap<String,ColeccionManga> mangas; //La clave será el codigo del manga
    private String mensaje;
    
    public ModeloMapa() {
        mangas = new LinkedHashMap<String,ColeccionManga>();
        numeroRegistros = 0;
        registroActual = -1;
    }
    
    @Override
    public String getMensaje() {
        return mensaje;
    }
    
    @Override
    public void alta(ColeccionManga cm) throws IOException {
        if(mangas.containsKey(cm.getCodigo())){
            mensaje = "El manga ya existe";
        }else{
            mangas.put(cm.getCodigo(), cm);
            mensaje = "Manga creado";
            numeroRegistros++;
        }
    }

    @Override
    public void baja(ColeccionManga cm) throws IOException {
        if(mangas.containsKey(cm.getCodigo())){
            mangas.remove(cm.getCodigo());
            mensaje = "Manga eliminado";
            numeroRegistros--;
        }else{
            mensaje = "No se ha encontrado el manga";
        }
    }

    @Override
    public void modificar(ColeccionManga cm) throws IOException {
        if(mangas.containsKey(cm.getCodigo())){
            mangas.get(cm.getCodigo()).setDemografia(cm.getDemografia());
            mangas.get(cm.getCodigo()).setTipoDeTomo(cm.getTipoDeTomo());
            mangas.get(cm.getCodigo()).setNumeroTomos(cm.getNumeroTomos());
            mangas.get(cm.getCodigo()).setTerminado(cm.isTerminado());
            mensaje = "Manga modificado";
        }else{
            mensaje = "No se ha encontrado el manga";
        }
    }

    @Override
    public ColeccionManga consultaClave(String clave) throws IOException {
        short count = 0;
        ArrayList lista;
        try{
            lista = iteratorArrayList();
            while(true){
                if(mangas.get(lista.get(count)).getCodigo().equals(clave)){
                    registroActual = count;
                    return mangas.get(lista.get(count));
                }
            }
        }catch(java.lang.IndexOutOfBoundsException IOOBE){
            mensaje = "No se encontró el manga";
        }
        return null;
    }

    @Override
    public ColeccionManga consultaNombre(String nombre) throws IOException {
        short count = 0;
        ArrayList lista;
        try{
            lista = iteratorArrayList();
            while(true){
                if(mangas.get(lista.get(count)).getTitulo().equals(nombre)){
                    registroActual = count;
                    return mangas.get(lista.get(count));
                }
            }
        }catch(java.lang.IndexOutOfBoundsException IOOBE){
            mensaje = "No se encontró el manga";
        }
        return null;
    }

    @Override
    public ColeccionManga siguiente() throws IOException {
        if(numeroRegistros == 0){
            mensaje = "No hay mangas";
            return null;
        }
        ArrayList lista = iteratorArrayList();
        if(registroActual == numeroRegistros){
            registroActual--;
        }else if(registroActual > numeroRegistros){
            return ultimo();
        }
        registroActual++;
        return mangas.get(lista.get(registroActual));
    }

    @Override
    public ColeccionManga anterior() throws IOException {
        if(numeroRegistros == 0){
            mensaje = "No hay mangas";
            return null;
        }
        ArrayList lista = iteratorArrayList();
        if(registroActual == 0){
            registroActual++;
        }
        registroActual--;
        return mangas.get(lista.get(registroActual));
    }

    @Override
    public ColeccionManga primero() throws IOException {
        if(numeroRegistros == 0){
            mensaje = "No hay mangas";
            return null;
        }
        ArrayList lista = iteratorArrayList();
        registroActual = 0;
        return mangas.get(lista.get(registroActual)); 
    }

    @Override
    public ColeccionManga ultimo() throws IOException {
        if(numeroRegistros == 0){
            mensaje = "No hay mangas";
            return null;
        }
        ArrayList lista = iteratorArrayList();
        registroActual = (short) (mangas.size() - 1);
        return mangas.get(lista.get(registroActual));
    }
    
    private ArrayList<String> iteratorArrayList(){
        LinkedHashSet<String> lhs;
        ArrayList<String> salida = new ArrayList();
        Iterator<String> i;
        
        lhs = (LinkedHashSet) mangas.keySet();
        i = lhs.iterator();
        
        while(i.hasNext()){
            salida.add(i.next());
        }
        return salida;
    }
    
}
