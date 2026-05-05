package org.instituto.ProyectoBackEnd.respositorys;

import org.instituto.ProyectoBackEnd.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrepositoryVenta extends JpaRepository<Venta, Integer> {
    
}
