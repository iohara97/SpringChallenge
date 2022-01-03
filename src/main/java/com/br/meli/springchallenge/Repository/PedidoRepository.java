package com.br.meli.springchallenge.Repository;

import com.br.meli.springchallenge.Database.Database;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class PedidoRepository {

    @Autowired
    Database database;

    public Pedido criarPedido(List<Produto> produtos) throws SQLException {
        Pedido pedido = database.criarPedido(produtos);
        pedido.calculaTotal();
        return pedido;
    }
}
