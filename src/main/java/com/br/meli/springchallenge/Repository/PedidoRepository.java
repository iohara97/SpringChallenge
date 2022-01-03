package com.br.meli.springchallenge.Repository;

import com.br.meli.springchallenge.Database.Database;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe que irá persistir os dados numa lista de pedido
 */
@Repository
public class PedidoRepository {

    @Autowired
    Database database;

    /**
     * Método para criar um pedido com uma lista de produto
     * @param produtos
     * @return pedido com uma lista de produtp exibindo o total
     */
    public Pedido criarPedido(List<Produto> produtos) {
        Pedido pedido = database.criarPedido(produtos);
        pedido.calculaTotal();
        return pedido;
    }
}
