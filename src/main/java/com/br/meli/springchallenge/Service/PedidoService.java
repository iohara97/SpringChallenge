package com.br.meli.springchallenge.Service;

import com.br.meli.springchallenge.DTO.ProdutoDTO;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import com.br.meli.springchallenge.Repository.PedidoRepository;
import com.br.meli.springchallenge.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido envia(List<Produto> produtos) {
        try {
            Pedido pedido = pedidoRepository.criarPedido(produtos);
            return pedido;
        } catch(Exception e) {
            System.out.println("Error = "
                    + e.getMessage());
        }
        return null;
    }
}
