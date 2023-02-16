package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categorias")
public class CategoriaCotroller {
    private final CategoriaService categoriaService;

    public CategoriaCotroller(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"", "/"}, params = {"!buscar", "!ordenar"})
    public List<Categoria> all(){
        log.info("Accediendo a todas las Categorias");
        return this.categoriaService.all();
    }
    @GetMapping(value = {"", "/"})
    public List<Categoria> all(@RequestParam("buscar")Optional<String> buscarOptional,
                               @RequestParam("ordenar") Optional<String> ordenarOptional){
        log.info("Accediendo a todas las Categorias");
        return this.categoriaService.allFilters(buscarOptional, ordenarOptional);
    }

    @PostMapping({"", "/"})
    public Categoria newCategoria(@RequestBody Categoria categoria){
        return this.categoriaService.save(categoria);
    }
    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id){
        return this.categoriaService.one(id);
    }
    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria){
        return this.categoriaService.replace(id, categoria);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id){
        this.categoriaService.delete(id);
    }
}
