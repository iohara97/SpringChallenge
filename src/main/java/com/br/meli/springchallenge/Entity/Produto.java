package com.br.meli.springchallenge.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Essa classe contém os atributos da entidade produto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Long productId;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeShipping;
    private String prestige;

}
