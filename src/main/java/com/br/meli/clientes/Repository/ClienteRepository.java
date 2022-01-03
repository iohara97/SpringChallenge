package com.br.meli.clientes.Repository;

import com.br.meli.clientes.Database.Database;
import com.br.meli.clientes.Entity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    Database database;

    public List<Cliente> getAllClientes(){
        return database.getAllClientes();
    }

    public Cliente insertCliente(Cliente cliente) {
        return database.insertCliente(cliente);
    }

    public List<Cliente> getAllClientesByEstado(String estado) {
        return database.getAllClientesByState(estado);
    }
}
