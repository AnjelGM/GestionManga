package interfaces;

import run.ControladorColeccion;

/**
 * 
 * @author Angel
 */
public interface IVentana {
    public abstract String getCodigo();
    public abstract String getTitulo();
    public abstract String getAutor();
    public abstract String getDibujo();
    public abstract String getDemografia();
    public abstract String getEditorial();
    public abstract String getTipoDeTomo();
    public abstract short getNumeroTomos();
    public abstract boolean getEdicionNormal();
    public abstract boolean getEdicionEspecial();
    public abstract boolean getTerminado();
    
    public byte getOperacion();
    
    public abstract void setCodigo(String codigo);
    public abstract void setTitulo(String titulo);
    public abstract void setAutor(String autor);
    public abstract void setDibujo(String dibujo);
    public abstract void setDemografia(String demografia);
    public abstract void setEditorial(String editorial);
    public abstract void setTipoDeTomo(String tipoDeTomo);
    public abstract void setNumeroTomos(short numeroTomos);
    public abstract void setEdicionNormal(boolean edicionNormal);
    public abstract void setEdicionEspecial(boolean edicionEspecial);
    public abstract void setTerminado(boolean terminado);
    
    public abstract void setControlador(ControladorColeccion cc);
    public abstract void mostrar(); //Visualice la IGU
}
