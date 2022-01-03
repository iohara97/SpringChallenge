package com.br.meli.springchallenge.Controller;
import com.br.meli.springchallenge.Database.Database;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Essa classe é um intermediador entre a Entity de Produto e o View
 */
@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    /**
     * Método que define o post para cadastrar um produto, retornando um http Status com código 201.
     * @param produtos
     * @return http response com a lista de produtos cadastrados
     */
    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProdutoDTO>> postProduct(@RequestBody List<Produto> produtos) {
        List<ProdutoDTO> produtosDTO = produtoService.cadastrar(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosDTO);
    }

    /**
     * Método que retorna uma lista de produtos com possibilidade de multiplos filtros
     * @param requestFilters
     * @return lista de produtos ou status de erro
     */
    @GetMapping("articles")
    @ResponseBody
    public ResponseEntity<List<Produto>> getByFilters(@RequestParam MultiValueMap<String, String> requestFilters) {
        HashMap<String, String> filtros = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : requestFilters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().get(0);// concatenar strings
            filtros.put(key, value);
        }

        List<Produto> produtos = produtoService.pesquisaPorFiltros(filtros);
        if(produtos.size() < 1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }
}
