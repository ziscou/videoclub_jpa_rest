package org.iesvdm.videoclub.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepositoryJPQL implements CustomCategoriaRepository{
    @Autowired
    private EntityManager en;
    @Override
    public List<Categoria> queryCustomCategoria(Optional<String> busOpt, Optional<String> ordOpt){
        StringBuilder queryBuild = new StringBuilder("Select C from Categoria C");
        if(busOpt.isPresent()){
            queryBuild.append(" ").append("where C.nombre like :nombre");
        }
        if(ordOpt.isPresent()){
            if (ordOpt.get().equalsIgnoreCase("asc")){
                queryBuild.append(" ").append("order by C.nombre asc");
            }
            if (ordOpt.get().equalsIgnoreCase("desc")){
                queryBuild.append(" ").append("order by C.nombre desc");
            }
        }

        Query query = en.createQuery(queryBuild.toString());
        if (busOpt.isPresent()){
            query.setParameter("nombre", "%"+busOpt.get()+"%");
        }
        return query.getResultList();
    }
}
