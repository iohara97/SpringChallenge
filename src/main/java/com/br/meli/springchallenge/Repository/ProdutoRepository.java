package com.br.meli.springchallenge.Repository;

import com.br.meli.springchallenge.Database.Database;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Classe que irá persistir enquanto os dados não forem armazenados no banco de dados
 */
@Repository
public class ProdutoRepository {

    @Autowired
    Database database;

    /**
     * Método que vai persistir a criação da lista com o que está armazenado no banco de dados
     * @param produtos
     * @return lista de produtos
     */
    public List<Produto> salvar(List<Produto> produtos) {
        List<Produto> produtosA = database.insertProdutoList(produtos);
        return produtosA;
    }

    /**
     * Método que vai exibir uma lista com o que está armazenado no banco de dados filtrando pela categoria
     * @deprecated
     * @param category
     * @return lista de produtos por categoria
     */
    public List<Produto> procuraCategory(String category) {
        List<Produto> produtos = database.getAllProdutosByCategory(category);
        return produtos;
    }

    /**
     * Método que vai exibir uma lista com o que está armazenado no banco de dados através de múlitplos filtros
     * @param filtros
     * @return lista de produtos com filtros personalizados
     */
    public List<Produto> procuraPorFiltros(HashMap<String, String> filtros) {
        List<Produto> produtos = database.getAllProdutosByFilters(filtros);
        return produtos;
    }

}
