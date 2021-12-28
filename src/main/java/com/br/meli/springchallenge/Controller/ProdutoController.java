package com.br.meli.springchallenge.Controller;


import com.br.meli.springchallenge.Database;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ProdutoController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/produto")
    public ResponseEntity<String> greeting() {
        Database.connect();
        return ResponseEntity.ok("CONECTOU!");

    }

}
