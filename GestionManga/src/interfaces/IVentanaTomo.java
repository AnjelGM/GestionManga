package interfaces;

import run.ControladorTomo;

/**
 * 
 * @author Angel
 */
public interface IVentanaTomo {
    public abstract String codigo();
    public abstract String portada(); //Sujeto a cambios para guardar en un futuro una imagen
    public abstract short numero();
    public abstract float precio();
    
    public abstract void setControlador(ControladorTomo ct);
    public abstract void mostrar(); //Visualice la IGU
}
