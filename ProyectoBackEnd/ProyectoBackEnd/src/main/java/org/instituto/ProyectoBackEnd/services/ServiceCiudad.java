package org.instituto.ProyectoBackEnd.services;

import org.instituto.ProyectoBackEnd.respositorys.IrepositoryCiudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class ServiceCiudad {

    @Autowired
    private IrepositoryCiudad repositoryCiudad;

    
}
