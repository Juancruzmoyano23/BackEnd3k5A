package org.instituto.ProyectoBackEnd.services;

import org.instituto.ProyectoBackEnd.respositorys.IrepositoryVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class ServiceVenta {

    @Autowired
    private IrepositoryVenta repositoryVenta;

    
}
