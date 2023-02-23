package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.CustomCategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CustomCategoriaRepository customCategoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, CustomCategoriaRepository customCategoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.customCategoriaRepository = customCategoriaRepository;
    }

    public List<Categoria> all() {
        List<Categoria>l=this.categoriaRepository.findAll();

        //l.stream().map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNombre(), categoria.getUltimaActualizacion(), categoria.getPeliculas().size()))

        return this.categoriaRepository.findAll();
    }
    public Map<String, Object> all(int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());

        Page<Categoria> pageAll = this.categoriaRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();

        response.put("categorias", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getNumberOfElements());
        response.put("totalPage", pageAll.getTotalPages());

        return response;


    }

    public Map<String, Object> all(int pagina, int tamanio,Optional<String> busOpt, Optional<String> ordOpt) {

        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending());
        Page<Categoria> pageAll;

        if (ordOpt.isPresent() && ordOpt.get().equalsIgnoreCase("desc")){
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

    public List<Categoria> allFilters(Optional<String> busOpt, Optional<String> ordOpt) {

        return this.customCategoriaRepository.queryCustomCategoria(busOpt, ordOpt);
    }

    public Categoria save(Categoria categoria){
        categoria.setUltimaActualizacion(new Date());
        return categoriaRepository.save(categoria);
    }
    public Categoria one(Long id){
        return categoriaRepository.findById(id).orElseThrow( () -> new CategoriaNotFoundException(id));
    }
    public Categoria replace(Long id, Categoria categoria){
        categoria.setUltimaActualizacion(new Date());
        return categoriaRepository.findById(id).map(c -> (id.equals(categoria.getId()) ?
                                                        this.categoriaRepository.save(categoria) : null)).
                                orElseThrow(() -> new CategoriaNotFoundException(id));
    }
    public Categoria delete(Long id){
        return categoriaRepository.findById(id).map(c -> {
                    this.categoriaRepository.delete(c);
                return c;})
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }
}
