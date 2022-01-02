package com.br.meli.springchallenge.Service;

import com.br.meli.springchallenge.DTO.ProdutoDTO;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import com.br.meli.springchallenge.Repository.ProdutoRepository;
import exception.CustomException;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    ProdutoRepository produtoRepository;


    @SneakyThrows
    public Pedido envia(List<Produto> produtos) {
        try {
            Pedido pedido = produtoRepository.criarPedido(produtos);
            return pedido;
        } catch(SQLException e) {
            throw new CustomException("deu treta");
        }
    }
}
