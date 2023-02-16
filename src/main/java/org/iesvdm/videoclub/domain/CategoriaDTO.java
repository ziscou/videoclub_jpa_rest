package org.iesvdm.videoclub.domain;

import java.util.Date;
import java.util.Set;

public class CategoriaDTO extends Categoria{
    private int numPeliculas;
    public CategoriaDTO(long id, String nombre, Set<Pelicula> peliculas, Date ultimaActualizacion, int numPeliculas) {
        super(id, nombre, peliculas, ultimaActualizacion);
        this.numPeliculas = numPeliculas;
    }
}
