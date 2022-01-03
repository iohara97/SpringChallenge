package com.br.meli.springchallenge.Service;

import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import com.br.meli.springchallenge.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido envia(List<Produto> produtos) {
        try {
            Pedido pedido = pedidoRepository.criarPedido(produtos);
            return pedido;
        } catch(SQLException s) {
            throw new CustomException("");
        }
        return null;
    }
}
