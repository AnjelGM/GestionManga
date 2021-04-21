package interfaces;

import clases.ColeccionManga;
import java.io.IOException;
import run.ControladorColeccion;
import vistas.VentanaBusquedaConsola;

/**
 * 
 * @author Angel
 */
public interface IVentana {
    public abstract ColeccionManga getColeccionManga();
    public abstract IVentanaBusqueda getVentanaBusqueda();
    public abstract IVentanaMensaje getVentanaMensaje();
    
    public abstract byte getOperacion();
    
    public abstract void setColeccionManga(ColeccionManga Manga);
    public abstract void mostrarManga() throws IOException; //que tenga codigo vacio en el de swing
    
    public abstract void setControlador(ControladorColeccion cc);
    public abstract void mostrar() throws IOException; //Visualice la IGU
}
