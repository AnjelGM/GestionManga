package run;

import clases.ColeccionManga;
import interfaces.*;

/**
 * 
 * @author Angel
 */
public class ControladorColeccion {
    private IModelo modelo;
    private IVentana vistaColeccion;
    
    public ControladorColeccion(IModelo modelo, IVentana vistaColeccion) {
        this.modelo = modelo;
        this.vistaColeccion = vistaColeccion;
    }
    
    public void notifiacion(){
        switch(vistaColeccion.getOperacion()){
            case 1: //Alta
                
                break;
            case 2: //Baja
                
                break;
            case 3: //Modificacion
                
                break;
            case 4: //Consulta por clave
                
                break;
            case 5: //Consulta por nombre
                
                break;
        }
    }
}
