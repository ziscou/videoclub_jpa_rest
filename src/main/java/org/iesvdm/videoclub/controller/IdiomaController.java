package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Idioma;
import org.iesvdm.videoclub.service.IdiomaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/idiomas")
public class IdiomaController {
    private final IdiomaService idiomaService;

    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    @GetMapping({"","/"})
    public List<Idioma> all() {
        log.info("Accediendo a todas las películas");
        return this.idiomaService.all();
    }

    @PostMapping({"","/"})
    public Idioma newIdioma(@RequestBody Idioma idioma) {
        return this.idiomaService.save(idioma);
    }

    @GetMapping("/{id}")
    public Idioma one(@PathVariable("id") Long id) {
        return this.idiomaService.one(id);
    }

    @PutMapping("/{id}")
    public Idioma replaceIdioma(@PathVariable("id") Long id, @RequestBody Idioma idioma) {
        return this.idiomaService.replace(id, idioma);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteIdioma(@PathVariable("id") Long id) {
        this.idiomaService.delete(id);
    }


}
