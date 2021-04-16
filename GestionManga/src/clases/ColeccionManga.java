package clases;

import java.io.Serializable;

/**
 * 
 * @author Angel
 */
public class ColeccionManga implements Serializable{
    boolean terminado; //radioButton, Si está terminado o en curso
    boolean edicionNormal, edicionEspecial; //checkBox
    short numeroTomos; //jspinner
    String demografia; //comboBox
    String tipoDeTomo; //listBox
    String editorial; //comboBox
    String codigo, titulo, autor, dibujo; //textField

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public boolean isEdicionNormal() {
        return edicionNormal;
    }

    public void setEdicionNormal(boolean edicionNormal) {
        this.edicionNormal = edicionNormal;
    }

    public boolean isEdicionEspecial() {
        return edicionEspecial;
    }

    public void setEdicionEspecial(boolean edicionEspecial) {
        this.edicionEspecial = edicionEspecial;
    }

    public short getNumeroTomos() {
        return numeroTomos;
    }

    public void setNumeroTomos(short numeroTomos) {
        this.numeroTomos = numeroTomos;
    }

    public String getDemografia() {
        return demografia;
    }

    public void setDemografia(String demografia) {
        this.demografia = demografia;
    }

    public String getTipoDeTomo() {
        return tipoDeTomo;
    }

    public void setTipoDeTomo(String tipoDeTomo) {
        this.tipoDeTomo = tipoDeTomo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDibujo() {
        return dibujo;
    }

    public void setDibujo(String dibujo) {
        this.dibujo = dibujo;
    }
}
