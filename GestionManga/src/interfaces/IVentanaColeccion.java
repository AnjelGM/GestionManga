package interfaces;

import run.Controlador;

/**
 * 
 * @author Angel
 */
public interface IVentanaColeccion {
    public abstract double getDato1();
    public abstract double getDato2();
    public abstract String getOperacion();
    public abstract void setResultado(double d);
    
    public abstract void setControlador(Controlador c);
    public abstract void mostrar(); //Visualice la IGU
}
