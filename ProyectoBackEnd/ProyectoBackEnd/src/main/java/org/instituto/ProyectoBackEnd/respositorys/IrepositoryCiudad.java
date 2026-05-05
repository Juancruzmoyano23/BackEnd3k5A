package org.instituto.ProyectoBackEnd.respositorys;

import org.instituto.ProyectoBackEnd.models.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrepositoryCiudad extends JpaRepository<Ciudad, Long> {
    
}
