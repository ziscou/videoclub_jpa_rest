package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> all() {
        return this.peliculaRepository.findAll();
    }

    private Set<String> listaCamposPer = new HashSet<>(Arrays.asList("titulo","descripcion","anyolanzamiento"));

    public Map<String, Object> all(int[] pagina ,Optional<String[]> camposOpt) {

        Pageable pageable = PageRequest.of(pagina[0], pagina[1], Sort.by("nombre").ascending());
        Page<Categoria> pageAll;
        if(camposOpt.isPresent()){
            for (int i = 0; i<camposOpt.get().length; i+=2 ){
                if(){

                }
            }
        }


        if (camposOpt.length>=2 && camposOpt[1].get().equalsIgnoreCase("desc")){
            pageable = PageRequest.of(pagina, tamanio, Sort.by("nombre").descending());
        }
        if (busOpt.isPresent()){
            pageAll = this.categoriaRepository.findByNombreContainingIgnoreCase(busOpt.get(), pageable);
        }else {
            pageAll = this.categoriaRepository.findAll(pageable);
        }




        Map<String, Object> response = new HashMap<>();

        response.put("categorias", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getNumberOfElements());
        response.put("totalPage", pageAll.getTotalPages());

        return response;


    }

    public Pelicula save(Pelicula pelicula) {
        return this.peliculaRepository.save(pelicula);
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map( p -> (id.equals(pelicula.getIdPelicula())  ?
                                                            this.peliculaRepository.save(pelicula) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public void delete(Long id) {
        this.peliculaRepository.findById(id).map(p -> {this.peliculaRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

}
