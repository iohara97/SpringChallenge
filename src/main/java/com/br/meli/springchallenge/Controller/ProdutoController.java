package com.br.meli.springchallenge.Controller;
import com.br.meli.springchallenge.Database.Database;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.meli.springchallenge.DTO.ProdutoDTO;
import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    /*private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/produto")
    public ResponseEntity<String> greeting() {
        Database.connect();
        return ResponseEntity.ok("CONECTOU!");

    }*/

    @GetMapping
    public String okay() {
        return "okay";
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProdutoDTO>> postProduct(@RequestBody List<Produto> produtos) {
        List<ProdutoDTO> produtosDTO = produtoService.cadastrar(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosDTO);
    }

    @RequestMapping(value="articles", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Produto>> getByCategory(@RequestParam("category") String category) {
        List<Produto> produtos = produtoService.pesquisaCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

}
