package com.br.meli.clientes.Controller;

import com.br.meli.clientes.Entity.Cliente;
import com.br.meli.clientes.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/clientes")
public class ClientesController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("")
    public ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> clientes = clienteService.getAllClientes();
        if (clientes.size() > 0) {
            return ResponseEntity.ok(clientes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<List<Cliente>> getAllClientesByEstado(@RequestParam String estado){
        List<Cliente> clientes = clienteService.getAllClientesByEstado(estado);

        return ResponseEntity.ok(clientes);

    }



    @PostMapping("/")
    public Cliente createClientes(@RequestBody Cliente cliente){
        return clienteService.createCliente(cliente);
    }

    @GetMapping("/ping")
    public String postPedido() {
        return "pong";
    }


}
