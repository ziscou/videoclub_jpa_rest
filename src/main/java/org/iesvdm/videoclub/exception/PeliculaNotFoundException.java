package org.iesvdm.videoclub.exception;

public class PeliculaNotFoundException extends RuntimeException{
    public PeliculaNotFoundException(Long id) {
        super("Not found Pelicula with id: " + id);
    }
}
