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
    
    public abstract void altaTomo(TomoManga tm);
    public abstract void bajaTomo(TomoManga tm);
    public abstract void modificarTomo(TomoManga tm);
    public abstract TomoManga consultaClaveTomo(String clave);
    public abstract TomoManga consultaNumeroTomo(short numero);
    
}
