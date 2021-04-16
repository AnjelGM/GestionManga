package interfaces;

import clases.ColeccionManga;
import run.ControladorColeccion;
import vistas.VentanaBusquedaConsola;

/**
 * 
 * @author Angel
 */
public interface IVentana {
    public abstract ColeccionManga getColeccionManga();
    public abstract VentanaBusquedaConsola getVentanaBusquedaConsola();
    
    public abstract byte getOperacion();
    
    public abstract void setColeccionManga(ColeccionManga Manga);
    
    public abstract void setControlador(ControladorColeccion cc);
    public abstract void mostrar(); //Visualice la IGU
}
