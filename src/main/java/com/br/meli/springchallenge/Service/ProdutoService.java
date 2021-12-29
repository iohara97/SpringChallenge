package com.br.meli.springchallenge.Service;

import com.br.meli.springchallenge.DTO.ProdutoDTO;
import com.br.meli.springchallenge.Entity.Produto;
import com.br.meli.springchallenge.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;


    public List<ProdutoDTO> cadastrar(List<Produto> produtos) {

        produtoRepository.salvar(produtos);

        return null;
    }
}
