/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

import clases.ColeccionManga;
import interfaces.IModelo;
import java.io.IOException;
import run.ControladorColeccion;

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
        numeroRegistros = (short) mangas.length;
        registroActual = -1;
    }
    
    @Override
    public String getMensaje() {
        return mensaje;
    }
    
    @Override
    public void alta(ColeccionManga cm) throws IOException {
        for(byte count = 0;count < mangas.length;count++){
            
        }
    }

    @Override
    public void baja(ColeccionManga cm) throws IOException {
        
    }

    @Override
    public void modificar(ColeccionManga cm) throws IOException {
        
    }

    @Override
    public ColeccionManga consultaClave(String clave) throws IOException {
        
    }

    @Override
    public ColeccionManga consultaNombre(String nombre) throws IOException {
        
    }

    @Override
    public ColeccionManga siguiente() throws IOException {
        
    }

    @Override
    public ColeccionManga anterior() throws IOException {
        
    }

    @Override
    public ColeccionManga primero() throws IOException {
        
    }

    @Override
    public ColeccionManga ultimo() throws IOException {
        
    }
}
