package com.br.meli.springchallenge.Controller;

import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import com.br.meli.springchallenge.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Essa classe é um intermediador entre a Entity de Pedidos, Produtos e o View
 */
@RestController
@RequestMapping("/api/v1")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    /**
     * Método que define o post para cadastrar um pedido, retornando um http Status com código 201.
     * @param produtos
     * @return http response com um pedido contendo a lista de produtos
     */
    @PostMapping("/purchase-request")
    public ResponseEntity<Pedido> postPedido(@RequestBody List<Produto> produtos) {
        Pedido pedido = pedidoService.envia(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
