package com.br.meli.springchallenge;

import java.sql.*;

public class Database {

    public static void connect() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:desafio1.db")) {

            System.out.println("Conexão realizada !!!!");
            Statement statement = connection.createStatement();

            // criando uma tabela
            //statement.execute("CREATE TABLE IF NOT EXISTS teste( ID INTEGER, NOME VARCHAR )");

            // inserindo registros
            //statement.execute("INSERT INTO teste( ID, NOME) VALUES (1, 'Lucian'), (2, 'JAVA')");

            // lendo os registros
            PreparedStatement stmt = connection.prepareStatement("select * from produto where category like('Roupas') order by category desc");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                String id = resultSet.getString("productId");
                String nome = resultSet.getString("name");
                String category = resultSet.getString("category");
                String brand = resultSet.getString("brand");
                String price = resultSet.getString("price");
                String qtd = resultSet.getString("quantity");
                String ship = resultSet.getString("free_shipping");
                String prestige = resultSet.getString("prestige");

                System.out.println( id + " - " + nome + " - " + category + " - " + brand + " - " + price + " - " + qtd + " - " + ship + " - " + prestige);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        connect();
    }
}
