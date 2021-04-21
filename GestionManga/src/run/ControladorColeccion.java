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
                mandarMensaje(modelo.getMensaje());
                break;
            case 2: //Baja
                modelo.baja(vistaColeccion.getColeccionManga());
                mandarMensaje(modelo.getMensaje());
                break;
            case 3: //Modificacion
                modelo.modificar(vistaColeccion.getColeccionManga());
                mandarMensaje(modelo.getMensaje());
                break;
            case 4: //Consulta por clave
                if(modelo.consultaClave(vistaColeccion.getVentanaBusqueda().getBuscarCodigo()) == null){
                    vistaColeccion.setColeccionManga(modelo.consultaClave(vistaColeccion.getVentanaBusqueda().getBuscarCodigo()));
                    mandarMensaje(modelo.getMensaje());
                }else{
                    vistaColeccion.setColeccionManga(modelo.consultaClave(vistaColeccion.getVentanaBusqueda().getBuscarCodigo()));
                }
                break;
            case 5: //Consulta por nombre
                if(modelo.consultaNombre(vistaColeccion.getVentanaBusqueda().getBuscarTitulo()) == null){
                    vistaColeccion.setColeccionManga(modelo.consultaNombre(vistaColeccion.getVentanaBusqueda().getBuscarTitulo()));
                    mandarMensaje(modelo.getMensaje());
                }else{
                    vistaColeccion.setColeccionManga(modelo.consultaNombre(vistaColeccion.getVentanaBusqueda().getBuscarTitulo()));
                }
                break;
            case 6: //Siguiente registro
                vistaColeccion.setColeccionManga(modelo.siguiente());
                break;
            case 7: //Anterior registro
                vistaColeccion.setColeccionManga(modelo.anterior());
                break;
            case 8: //Primer registro
                vistaColeccion.setColeccionManga(modelo.primero());
                break;
            case 9: //Ultimo registro
                vistaColeccion.setColeccionManga(modelo.ultimo());
                break;
        }
    }
    
    public void mandarMensaje(String mensaje){
        vistaColeccion.getVentanaMensaje().setMensaje(mensaje);
        vistaColeccion.getVentanaMensaje().mostrar();
    }
}
