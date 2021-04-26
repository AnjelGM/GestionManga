package clases;

import java.io.Serializable;

/**
 * 
 * @author Angel
 */
public class ColeccionManga implements Serializable, Comparable{
    boolean terminado; //radioButton, Si está terminado o en curso
    boolean edicionEspecial; //checkBox
    int numeroTomos; //jspinner
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

    public boolean isEdicionEspecial() {
        return edicionEspecial;
    }

    public void setEdicionEspecial(boolean edicionEspecial) {
        this.edicionEspecial = edicionEspecial;
    }

    public int getNumeroTomos() {
        return numeroTomos;
    }

    public void setNumeroTomos(int numeroTomos) {
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
    
    @Override
    public boolean equals (Object object){
        ColeccionManga otroManga = (ColeccionManga) object;
        return this.getCodigo().equals(otroManga.getCodigo());
    }
    
    @Override
    public String toString(){
        StringBuilder salida = new StringBuilder("");
        salida.append(codigo).append("|").append(titulo).append("|").append(autor);
        salida.append("|").append(dibujo).append("|").append(editorial).append("|");
        salida.append(tipoDeTomo).append("|").append(demografia).append("|").append(numeroTomos);
        salida.append("|").append(edicionEspecial).append("|").append(terminado);
        return salida.toString();
    }

    @Override
    public int compareTo(Object arg0) {
        ColeccionManga otroManga = (ColeccionManga) arg0;
        return codigo.compareTo(otroManga.getCodigo());
    }
}
