package interfaces;

import run.ControladorColeccion;

/**
 * 
 * @author Angel
 */
public interface IVentanaColeccion {
    public abstract String codigo();
    public abstract String titulo();
    public abstract String autor();
    public abstract String dibujo();
    public abstract String demografia();
    public abstract String editorial();
    public abstract String tipoDeTomo();
    public abstract short numeroTomos();
    public abstract boolean edicionNormal();
    public abstract boolean edicionEspecial();
    public abstract boolean terminado();
    
    public abstract void setControlador(ControladorColeccion cc);
    public abstract void mostrar(); //Visualice la IGU
}
