package org.instituto.ProyectoBackEnd.respositorys;

import org.instituto.ProyectoBackEnd.models.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrepositoryMoto extends JpaRepository<Moto, String> {
    
}
