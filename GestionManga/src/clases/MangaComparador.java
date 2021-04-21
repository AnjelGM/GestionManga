package clases;

import java.util.Comparator;

/**
 * 
 * @author Angel
 */
public class MangaComparador implements Comparator{

    @Override
    public int compare(Object arg0, Object arg1) {
        ColeccionManga manga1 = (ColeccionManga) arg0;
        ColeccionManga manga2 = (ColeccionManga) arg1;
        
        return manga1.titulo.compareTo(manga2.titulo);
    }
    
}
