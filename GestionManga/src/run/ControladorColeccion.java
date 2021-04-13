package run;

import clases.ColeccionManga;
import interfaces.*;

/**
 * 
 * @author Angel
 */
public class ControladorColeccion {
    private IModelo modelo;
    private IVentanaColeccion vistaColeccion;
    
    public ControladorColeccion(IModelo modelo, IVentanaColeccion vistaColeccion) {
        this.modelo = modelo;
        this.vistaColeccion = vistaColeccion;
    }
    
    public ColeccionManga consultaPorClave(String codigo){
        return modelo.consultaClaveColeccion(codigo);
    }
    
    public ColeccionManga consultaPorNombre(String codigo){
        return modelo.consultaNombreColeccion(codigo);
    }
}
