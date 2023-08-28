package com.gestion.clientes.Controller;

import com.gestion.clientes.exception.ResourceNotFoundException;
import com.gestion.clientes.model.Cliente;
import com.gestion.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/Clientes")
    public List<Cliente> listarCliente() {
        return clienteRepository.findAll();
    }

    @PostMapping("/Clientes")
    public Cliente guardarCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/Clientes/{id}")
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese ID no existe: " + id ));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/Clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteRequest){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cleinte con ese ID no existe: " + id ));

        cliente.setNombre(clienteRequest.getNombre());
        cliente.setApellido(clienteRequest.getApellido());
        cliente.setEmail(clienteRequest.getEmail());

        Cliente clienteActualizado = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/Clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese ID no existe:" + id));

        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}
