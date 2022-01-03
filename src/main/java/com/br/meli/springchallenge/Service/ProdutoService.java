package com.br.meli.springchallenge.Service;

import com.br.meli.springchallenge.DTO.ProdutoDTO;
import com.br.meli.springchallenge.Entity.Produto;
import com.br.meli.springchallenge.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Essa classe contém a lógica de negócio da entidade Produto
 */
@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    /**
     * Método para criar uma lista de produtos e armazenar no banco de dados
     * @exception Exception
     * @param produtos
     * @return lista de produtos cadastrados
     */
    public List<ProdutoDTO> cadastrar(List<Produto> produtos) {
        try {
            List<Produto> produtosA = produtoRepository.salvar(produtos);
            return ProdutoDTO.converte(produtosA);
        } catch(Exception e) {
            System.out.println("Error = "
                    + e.getMessage());
        }
        return null;
    }

    /**
     * Método para listar todos os produtos de uma determinada categoria
     * @deprecated
     * @param category
     * @return lista de produtos de uma categoria
     */
    public List<Produto> pesquisaCategory(String category) {
        try {
            List<Produto> produtos = produtoRepository.procuraCategory(category);
            return produtos;
        } catch (Exception e) {
            System.out.println("Error = "
                    + e.getMessage());
        }
        return null;
    }

    /**
     * Método para retornar uma lista de produto referenciada por filtros múltiplos
     * @exception Exception
     * @param filtros
     * @return lista de produtos conforme filtros múltiplos
     */
    public List<Produto> pesquisaPorFiltros(HashMap<String, String> filtros) {
        try {
            List<Produto> produtos = produtoRepository.procuraPorFiltros(filtros);
            return produtos;
        } catch (Exception e) {
            System.out.println("Error = "
                    + e.getMessage());
        }
        return null;
    }
}
