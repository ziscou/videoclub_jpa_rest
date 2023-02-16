package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomCategoriaRepository {

    List<Categoria> queryCustomCategoria(Optional<String> busOpt, Optional<String> ordOpt);
}
