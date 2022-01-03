package com.br.meli.clientes.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class ClientesController {

    @GetMapping("/ping")
    public String postPedido() {
        return "pong";
    }


}
