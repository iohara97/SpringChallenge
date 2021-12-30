package com.br.meli.springchallenge.Database;

import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Database {

    private Connection connect() {
        try {

            Connection connection = DriverManager.getConnection("jdbc:sqlite:desafio1.db");

            return connection;
//            System.out.println("Conexão realizada !!!!");
//            Statement statement = connection.createStatement();

            // criando uma tabela
            //statement.execute("CREATE TABLE IF NOT EXISTS teste( ID INTEGER, NOME VARCHAR )");

            // inserindo registros
            //statement.execute("INSERT INTO teste( ID, NOME) VALUES (1, 'Lucian'), (2, 'JAVA')");

            // lendo os registros
//            PreparedStatement stmt = connection.prepareStatement("select * from produto where category like('Roupas') order by category desc");
//            ResultSet resultSet = stmt.executeQuery();
//
//            while (resultSet.next()) {
//
//                String id = resultSet.getString("productId");
//                String nome = resultSet.getString("name");
//                String category = resultSet.getString("category");
//                String brand = resultSet.getString("brand");
//                String price = resultSet.getString("price");
//                String qtd = resultSet.getString("quantity");
//                String ship = resultSet.getString("free_shipping");
//                String prestige = resultSet.getString("prestige");
//
//                System.out.println( id + " - " + nome + " - " + category + " - " + brand + " - " + price + " - " + qtd + " - " + ship + " - " + prestige);
//            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Produto> getAllProdutos() {
        return queryProduto("select * from produto order by name;");
    }

    public List<Produto> getAllProdutosByCategory(String category) {
        return queryProduto("select * from produto where category = " + category);
    }

    public List<Produto> getAllProdutos(String category) {
        return queryProduto("select * from produto where category = " + category);
    }



    private List<Produto> queryProduto(String query) {
        try {
            Connection cn = connect();

            System.out.println("Conexão realizada !!!!");
            Statement statement = cn.createStatement();

            // lendo os registros
            PreparedStatement stmt = cn.prepareStatement(query);

            // tentar implementar a parametrização da query
//            PreparedStatement stmt = cn.prepareStatement("Select * from produtos where produtId = ?");
//            stmt.setInt(1, 4);

            ResultSet resultSet = stmt.executeQuery();

            List<Produto> produtos = new ArrayList<Produto>();

            while (resultSet.next()) {
                // Tentando fazer o cast da linha para o objeto Produto
//                Produto produto = (Produto) resultSet.getRow();

                Produto produto = new Produto(
                    resultSet.getLong("productId"),
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getString("brand"),
                    resultSet.getBigDecimal("price"),
                    resultSet.getInt("quantity"),
                    resultSet.getBoolean("free_shipping"),
                    resultSet.getString("prestige")
                );
                produtos.add(produto);
            }

            return produtos;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void customQuery(String query) {
        try {
            Connection cn = connect();

            Statement statement = cn.createStatement();

            PreparedStatement stmt = cn.prepareStatement(query);

            stmt.execute(); //executeQuery();

            System.out.println("Query executada com sucesso em customQuery()");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


//    public static List<Pedido> getAllPedidos() {
//        try {
//            Connection cn = connect();
//
//            System.out.println("Conexão realizada !!!!");
//            Statement statement = cn.createStatement();
//
//            // lendo os registros
//            PreparedStatement stmt = cn.prepareStatement("select * from produto order by name");
//            ResultSet resultSet = stmt.executeQuery();
//
//            List<Pedido> pedidos = new ArrayList<Pedido>();
//
//            while (resultSet.next()) {
//                // Tentando fazer o cast da linha para o objeto Pedido
////                Pedido pedido = (Pedido) resultSet.getRow();
//
//                Pedido pedido = new Pedido(
//                        resultSet.getInt("productId"),
//                        resultSet.getString("name"),
//                        resultSet.getString("category"),
//                        resultSet.getString("brand"),
//                        resultSet.getBigDecimal("price"),
//                        resultSet.getInt("quantity"),
//                        resultSet.getBoolean("free_shipping"),
//                        resultSet.getString("prestige")
//                );
//                pedidos.add(pedido);
//            }
//
//            return pedidos;
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public static void main(String[] args) {
//        connect();
//    }

    public List<Produto> insertProduto(List<Produto> produtos) {
        try {
            Connection cn = connect();

            String query = "INSERT INTO produto (name, category, brand, price, quantity, free_shipping, prestige) VALUES ";
//            String subquery = "";
            List<Integer> keysList = new ArrayList<>();
            List<Produto> produtosA = new ArrayList<Produto>();

            for (Produto elem : produtos) {

                String subquery = query +

                        " ('" + elem.getName() + "', " +
                        "'" + elem.getCategory() + "', " +
                        "'" + elem.getBrand() + "', " +
                        elem.getPrice().toString() + ", " +
                        elem.getQuantity() + ", " +
                        elem.getFreeShipping().toString() + ", " +
                        "'" + elem.getPrestige() + "')";

                PreparedStatement stmt = cn.prepareStatement(subquery, Statement.RETURN_GENERATED_KEYS);

                int i = stmt.executeUpdate(); //executeQuery();

                ResultSet keysSet = stmt.getGeneratedKeys();

                while (keysSet.next()) {
                    int key = keysSet.getInt(1);

                    PreparedStatement getStmt = cn.prepareStatement("Select * from produto where productId = ?");
                    getStmt.setInt(1, key);
                    ResultSet resultSet = getStmt.executeQuery();

                    while (resultSet.next()) {
                        // Tentando fazer o cast da linha para o objeto Produto
//                Produto produto = (Produto) resultSet.getRow();

                        Produto produto = new Produto(
                                resultSet.getLong("productId"),
                                resultSet.getString("name"),
                                resultSet.getString("category"),
                                resultSet.getString("brand"),
                                resultSet.getBigDecimal("price"),
                                resultSet.getInt("quantity"),
                                resultSet.getBoolean("free_shipping"),
                                resultSet.getString("prestige")
                        );
                        produtosA.add(produto);
                    }


                }

            }

            return produtosA;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
            // Retirando o último caracter
//            StringBuilder sb = new StringBuilder(query);
//            sb.deleteCharAt(query.length() - 1);

//        System.out.println(sb.toString());

//            sb.append(" RETURNING *");
//            String query1 = sb.toString();
            // lendo os registros
            //Statement statement = cn.createStatement();

//            Statement statement = cn.createStatement();


            // tentar implementar a parametrização da query
//            PreparedStatement stmt = cn.prepareStatement("Select * from produtos where produtId = ?");
//            stmt.setInt(1, 4);

//             ResultSet resultSet = statement.executeQuery(query1);

//            PreparedStatement stmt = cn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
//
//            ResultSet resultSet = stmt.executeUpdate(); //executeQuery();
//
//            ResultSet keysSet = null;
//            keysSet = statement.getGeneratedKeys();
//
//
//            String getQuery = "Select * from produtos where productId in (";
//
//            while (keysSet.next()) {
//                getQuery += keysSet.getInt(1) + ", ";
//            }
//
//            StringBuilder sb1 = new StringBuilder(getQuery);
//            sb1.deleteCharAt(sb1.length() - 1);
//            sb1.deleteCharAt(sb1.length() - 1);
//
//            sb1.append(") order by name;");
//
//            // lendo os registros
//            PreparedStatement getStmt = cn.prepareStatement(sb1.toString());
//            ResultSet resultSet = getStmt.executeQuery();
//
//
//            List<Produto> produtosA = new ArrayList<Produto>();
//
//            while (resultSet.next()) {
//                // Tentando fazer o cast da linha para o objeto Produto
////                Produto produto = (Produto) resultSet.getRow();
//
//                Produto produto = new Produto(
//                        resultSet.getLong("productId"),
//                        resultSet.getString("name"),
//                        resultSet.getString("category"),
//                        resultSet.getString("brand"),
//                        resultSet.getBigDecimal("price"),
//                        resultSet.getInt("quantity"),
//                        resultSet.getBoolean("free_shipping"),
//                        resultSet.getString("prestige")
//                );
//                produtosA.add(produto);
//            }

//            return produtosA;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
