package org.instituto.ProyectoBackEnd.services;

import org.instituto.ProyectoBackEnd.respositorys.IrepositorySegmento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class ServiceSegmento {

    @Autowired
    private IrepositorySegmento repositorySegmento;

    
}
