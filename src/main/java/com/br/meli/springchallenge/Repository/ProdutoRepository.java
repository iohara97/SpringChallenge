package com.br.meli.springchallenge.Repository;

import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoRepository {

    List<Produto> produtosA = new ArrayList<>();

    public void cadastrar(List<Produto> produtos) {
        produtosA.addAll(produtos);
    }
}
