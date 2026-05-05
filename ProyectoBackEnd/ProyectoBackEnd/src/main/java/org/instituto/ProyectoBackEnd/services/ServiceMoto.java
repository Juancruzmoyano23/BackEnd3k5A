package org.instituto.ProyectoBackEnd.services;

import org.instituto.ProyectoBackEnd.respositorys.IrepositoryMoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class ServiceMoto {

    @Autowired
    private IrepositoryMoto repositoryMoto;

    
}
