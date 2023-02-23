package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> findByNombreContainingIgnoreCase(String nombre);
    public Page<Categoria> findByNombreContainingIgnoreCase(String nombre, Pageable paginable);

}
