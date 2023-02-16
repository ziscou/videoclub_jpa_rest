package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.CustomCategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
