package interfaces;

import clases.*;
import java.io.IOException;

/**
 * 
 * @author Angel
 */
public interface IModelo {
    public abstract void alta(ColeccionManga cm) throws IOException;
    public abstract void baja(ColeccionManga cm) throws IOException;
    public abstract void modificar(ColeccionManga cm) throws IOException;
    public abstract ColeccionManga consultaClave(String clave) throws IOException;
    public abstract ColeccionManga consultaNombre(String nombre) throws IOException;
}
