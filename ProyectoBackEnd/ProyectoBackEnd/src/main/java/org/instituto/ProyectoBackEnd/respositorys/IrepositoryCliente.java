package org.instituto.ProyectoBackEnd.respositorys;

import org.instituto.ProyectoBackEnd.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrepositoryCliente  extends JpaRepository<Cliente, Long> {
    
}
