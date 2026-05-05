package org.instituto.ProyectoBackEnd.services;

import org.instituto.ProyectoBackEnd.models.Cliente;
import org.instituto.ProyectoBackEnd.respositorys.IrepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ServiceCliente {

    @Autowired
    private IrepositoryCliente repositoryCliente;

    // GET - Obtener todos los clientes (Consulta)
    public List<Cliente> obtenerTodos() {
        return repositoryCliente.findAll();
    }

    // GET - Obtener cliente por ID (Consulta específica)
    public Optional<Cliente> obtenerPorId(Long id) {
        return repositoryCliente.findById(id);
    }

    // POST - Crear/Guardar nuevo cliente (Alta)
    public Cliente guardar(Cliente cliente) {
        return repositoryCliente.save(cliente);
    }

    // PUT - Actualizar cliente (Modificación)
    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        Optional<Cliente> clienteExistente = repositoryCliente.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setApellido(clienteActualizado.getApellido());
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setNro_doc(clienteActualizado.getNro_doc());
            cliente.setCalle(clienteActualizado.getCalle());
            cliente.setNro_calle(clienteActualizado.getNro_calle());
            cliente.setCiudad(clienteActualizado.getCiudad());
            return repositoryCliente.save(cliente);
        }
        return null;
    }

    // DELETE - Eliminar cliente (Baja)
    public boolean eliminar(Long id) {
        if (repositoryCliente.existsById(id)) {
            repositoryCliente.deleteById(id);
            return true;
        }
        return false;
    }

    // Consulta personalizada - Buscar clientes por nombre
    public List<Cliente> buscarPorNombre(String nombre) {
        return repositoryCliente.findAll().stream()
                .filter(cliente -> cliente.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }
}
