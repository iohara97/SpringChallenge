package com.br.meli.springchallenge;

import com.br.meli.clientes.SpringchallengeApplicationClientes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class SpringchallengeApplication {

    public static void main(String[] args) {
        SpringApplication loja = new SpringApplication(SpringchallengeApplication.class);
        loja.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        loja.run(args);


        //SpringApplication.run(SpringchallengeApplication.class, args);

    }

}
