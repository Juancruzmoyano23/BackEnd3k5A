package org.instituto.ProyectoBackEnd.respositorys;

import org.instituto.ProyectoBackEnd.models.Segmento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrepositorySegmento extends JpaRepository<Segmento, Integer> {
    
}
