package com.br.meli.springchallenge.Service;

import com.br.meli.springchallenge.DTO.ProdutoDTO;
import com.br.meli.springchallenge.Entity.Produto;
import com.br.meli.springchallenge.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

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

    // Revisar
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
