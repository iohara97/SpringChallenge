package com.br.meli.springchallenge.Repository;

import com.br.meli.springchallenge.Database.Database;
import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
