package com.br.meli.springchallenge.Repository;

import com.br.meli.springchallenge.Database.Database;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ProdutoRepository {

    @Autowired
    Database database;

    public List<Produto> salvar(List<Produto> produtos) {
        List<Produto> produtosA = database.insertProdutoList(produtos);
        return produtosA;
    }

    public List<Produto> procuraCategory(String category) {
        List<Produto> produtos = database.getAllProdutosByCategory(category);
        return produtos;
    }

    public Pedido criarPedido(List<Produto> produtos) {
        Pedido pedido = database.getProdutosComPreco(produtos);
        pedido.calculaTotal();
        return pedido;
    }

    public List<Produto> procuraPorFiltros(HashMap<String, String> filtros) {
        List<Produto> produtos = database.getAllProdutosByFilters(filtros);
        return produtos;
    }

}
