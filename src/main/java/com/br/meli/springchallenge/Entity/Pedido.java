package com.br.meli.springchallenge.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    private long id;
    private List<Produto> produtos;
    private double total;

    public double calculaTotal(){
        double pTotal= 0;

        for (Produto p:produtos){
            pTotal += p.getTotal();
        }
        return pTotal;
    }
}
