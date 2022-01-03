package com.br.meli.springchallenge.Service;

import com.br.meli.exception.CustomException;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import com.br.meli.springchallenge.Repository.PedidoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Essa classe contém a lógica de negócio da entidade Pedido
 */
@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    /**
     * Método que retorna um pedido com a lista de produtos
     * @exception SQLException
     * @param produtos
     * @return um pedido com a lista de produtos
     */
    @SneakyThrows
    public Pedido envia(List<Produto> produtos) {
        try {
            Pedido pedido = pedidoRepository.criarPedido(produtos);
            return pedido;
        } catch(SQLException s) {
            throw new CustomException("Erro ao processar sua solicitação.");
        }
    }
}
