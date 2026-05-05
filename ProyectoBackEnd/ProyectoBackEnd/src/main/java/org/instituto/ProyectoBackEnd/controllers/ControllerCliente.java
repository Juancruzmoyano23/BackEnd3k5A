package org.instituto.ProyectoBackEnd.controllers;

import java.util.List;
import java.util.Optional;

import org.instituto.ProyectoBackEnd.models.Cliente;
import org.instituto.ProyectoBackEnd.services.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ControllerCliente {

    // // Status 200 OK
    // ResponseEntity.ok(cliente)

// Status 201 CREATED (para guardar)
//ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente)

// Status 404 NOT FOUND
//ResponseEntity.notFound().build()

// Status 204 NO CONTENT (DELETE sin respuesta)
//ResponseEntity.noContent().build()

// Status 400 BAD REQUEST
//ResponseEntity.badRequest().body("Error: datos inválidos")

// Con headers custom
//ResponseEntity.ok()
//    .header("X-Custom-Header", "valor")
//    .body(cliente)

    @Autowired
    private ServiceCliente serviceCliente;

    @GetMapping()
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = serviceCliente.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = serviceCliente.obtenerPorId(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = serviceCliente.guardar(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = serviceCliente.obtenerPorId(id);
        if (clienteExistente.isPresent()) {
            Cliente clienteActualizado = serviceCliente.actualizar(id, cliente);
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        if (serviceCliente.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Cliente>> buscarClientesPorNombre(@PathVariable String nombre) {
        List<Cliente> clientes = serviceCliente.buscarPorNombre(nombre);
        return ResponseEntity.ok(clientes);
    }

}