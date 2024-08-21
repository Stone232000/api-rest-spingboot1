package com.ntt.demo.services;

import org.springframework.stereotype.Service;
import com.ntt.demo.model.Cliente;
import com.ntt.demo.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Método para guardar un nuevo cliente o actualizar uno existente
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Método para obtener todos los clientes
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    // Método para obtener un cliente por su ID
    public Optional<Cliente> findClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    // Método para eliminar un cliente por su ID
    public void deleteClienteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
