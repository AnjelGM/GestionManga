package run;

import clases.ColeccionManga;
import interfaces.*;
import java.io.IOException;

/**
 * 
 * @author Angel
 */
public class ControladorColeccion {
    private IModelo modelo;
    private IVentana vistaColeccion;
    
    public ControladorColeccion(IModelo modelo, IVentana vistaColeccion) {
        this.modelo = modelo;
        this.vistaColeccion = vistaColeccion;
    }
    
    public void notifiacion() throws IOException{
        switch(vistaColeccion.getOperacion()){
            case 1: //Alta
                modelo.alta(vistaColeccion.getColeccionManga());
                break;
            case 2: //Baja
                modelo.baja(vistaColeccion.getColeccionManga());
                break;
            case 3: //Modificacion
                modelo.baja(vistaColeccion.getColeccionManga());
                break;
            case 4: //Consulta por clave
                modelo.consultaClave(vistaColeccion.getVentanaBusqueda().getBuscarCodigo());
                break;
            case 5: //Consulta por nombre
                modelo.consultaNombre(vistaColeccion.getVentanaBusqueda().getBuscarTitulo());
                break;
            case 6: //Siguiente registro
                modelo.siguiente();
                break;
            case 7: //Anterior registro
                modelo.anterior();
                break;
            case 8: //Primer registro
                modelo.primero();
                break;
            case 9: //Ultimo registro
                modelo.ultimo();
                break;
        }
    }
    
    public void mandarMensaje(String mensaje){
        vistaColeccion.getVentanaMensaje().setMensaje(mensaje);
        vistaColeccion.getVentanaMensaje().mostrar();
    }
}
