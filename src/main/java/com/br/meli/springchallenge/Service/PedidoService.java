package com.br.meli.springchallenge.Service;

import com.br.meli.springchallenge.DTO.ProdutoDTO;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import com.br.meli.springchallenge.Repository.ProdutoRepository;
import exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Pedido envia(List<Produto> produtos) {
        try {
            Pedido pedido = produtoRepository.criarPedido(produtos);
            return pedido;
        } catch(Exception e) {
            throw new CustomException("deu treta");
        }
        return null;
    }
}
