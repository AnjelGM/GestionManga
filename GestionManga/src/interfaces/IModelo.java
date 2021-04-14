package interfaces;

import clases.*;

/**
 * 
 * @author Angel
 */
public interface IModelo {
    public abstract void altaColeccion(ColeccionManga cm);
    public abstract void bajaColeccion(ColeccionManga cm);
    public abstract void modificarColeccion(ColeccionManga cm);
    public abstract ColeccionManga consultaClaveColeccion(String clave);
    public abstract ColeccionManga consultaNombreColeccion(String nombre);
    
}
