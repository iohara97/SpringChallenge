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



    public void salvar(List<Produto> produtos) {

        String query = "INSERT INTO produto (name, category, brand, price, quantity, free_shipping, prestige) VALUES ";
//            String subquery = "";
        for (Produto elem:produtos) {

            String subquery = " ('" + elem.getName() + "', " +
                            "'" + elem.getCategory() + "', " +
                            "'" + elem.getBrand() + "', " +
                            elem.getPrice().toString() + ", " +
                            elem.getQuantity() + ", " +
                            elem.getFreeShipping().toString() + ", " +
                            "'"+ elem.getPrestige() + "'),";

            query += subquery;
        }

        // Retirando o último caracter
        StringBuilder sb = new StringBuilder(query);
        sb.deleteCharAt(query.length() - 1);

//        System.out.println(sb.toString());

        database.customQuery(sb.toString());
    }
}
