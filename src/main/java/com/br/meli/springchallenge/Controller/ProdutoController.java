package com.br.meli.springchallenge.Controller;


import com.br.meli.springchallenge.DTO.ProdutoDTO;
import com.br.meli.springchallenge.Database;
import com.br.meli.springchallenge.Entity.Produto;
import jdk.tools.jlink.internal.AbstractResourcePoolEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

    /*private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/produto")
    public ResponseEntity<String> greeting() {
        Database.connect();
        return ResponseEntity.ok("CONECTOU!");

    }*/

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProdutoDTO>> postProduct(List<Produto> produtos, UriComponentsBuilder uriBuilder) {
        List<ProdutoDTO> produtosDTO = produtoService.cadastrar(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosDTO);
    }
}
