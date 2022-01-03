package com.br.meli.clientes.Service;

import com.br.meli.clientes.Entity.Cliente;
import com.br.meli.clientes.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes(){
        return clienteRepository.getAllClientes();
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.insertCliente(cliente);
    }
}
