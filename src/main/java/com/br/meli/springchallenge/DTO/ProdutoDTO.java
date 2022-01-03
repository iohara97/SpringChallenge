package com.br.meli.springchallenge.DTO;

import com.br.meli.springchallenge.Entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe para otimizar a apresentaçào dos dados, exibindo uma resposta personalizada conforme o projeto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {

    private Long productId;
    private String name;
    private Integer quantity;

    /*public static Produto converte(ProdutoDTO dto) {
        Produto produto = Produto.builder()
                .productId(dto.getProductId())
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .build();
        return produto;
    }*/

    /**
     * Método que converte um dado de ProdutoDTO para produto, a ser utilizado em outros métodos/classes/componentes
     * @param produto
     * @return produtoDTO
     */
    public static ProdutoDTO converte(Produto produto) {
        return ProdutoDTO.builder()
                .productId(produto.getProductId())
                .name(produto.getName())
                .quantity(produto.getQuantity())
                .build();
    }

    /**
     * Método que converte uma lista de produto para lista de produtoDTO, a ser utilizado em outros métodos/classes/componentes
     * @param produtos
     * @return lista de produtosDTO
     */
    public static List<ProdutoDTO> converte(List<Produto> produtos) {
        return produtos.stream().map(p -> converte(p)).collect(Collectors.toList());
    }
}
