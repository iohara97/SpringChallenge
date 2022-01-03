package com.br.meli.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class SpringchallengeApplicationClientes {

    public static void main(String[] args) {
        SpringApplication clientes = new SpringApplication(SpringchallengeApplicationClientes.class);
        clientes.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        clientes.run(args);
    }

}
