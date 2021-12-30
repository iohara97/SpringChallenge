package com.br.meli.springchallenge.Database;

import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Database {

    List<Pedido> pedidos = new ArrayList<>();

    private Connection connect() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:desafio1.db");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Produto> getAllProdutos() {
        return queryProduto("select * from produto order by name;");
    }

    public List<Produto> getAllProdutosByCategory(String category) {
        return queryProduto("select * from produto where category = '" + category + "'");
    }

    public List<Produto> getAllProdutosId(String ids) {
        return queryProduto("select * from produto where productId in (" + ids + ")");
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

    public List<Produto> insertProdutoList(List<Produto> produtos) {
            List<Produto> listaProdutosCadastrados = new ArrayList<>();
            for (Produto elem : produtos) {
                listaProdutosCadastrados.add(insertProdutoSingle(elem));
            }
            return listaProdutosCadastrados;
    }

    private Produto insertProdutoSingle(Produto produto) {
        try {
            Connection cn = connect();
            String subquery = "INSERT INTO produto (name, category, brand, price, quantity, free_shipping, prestige) VALUES (?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement stmt = cn.prepareStatement(subquery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, produto.getName());
            stmt.setString(2, produto.getCategory());
            stmt.setString(3, produto.getBrand());
            stmt.setBigDecimal(4, produto.getPrice());
            stmt.setInt(5, produto.getQuantity());
            stmt.setBoolean(6, produto.getFreeShipping());
            stmt.setString(7, produto.getPrestige());

            int linhasCriadas = stmt.executeUpdate(); //executeQuery();
            ResultSet keysSet = stmt.getGeneratedKeys();
            Produto produtoCriado;
            while (keysSet.next()) {
                int key = keysSet.getInt(1);

                PreparedStatement getStmt = cn.prepareStatement("Select * from produto where productId = ?");
                getStmt.setInt(1, key);
                ResultSet resultSet = getStmt.executeQuery();

                while (resultSet.next()) {
                    // Tentando fazer o cast da linha para o objeto Produto
                    //                Produto produto = (Produto) resultSet.getRow();

                    produtoCriado = new Produto(
                            resultSet.getLong("productId"),
                            resultSet.getString("name"),
                            resultSet.getString("category"),
                            resultSet.getString("brand"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getInt("quantity"),
                            resultSet.getBoolean("free_shipping"),
                            resultSet.getString("prestige")
                    );
                    cn.close();
                    return produtoCriado;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Pedido getAllProdutosComTotal(List<Produto> produtos) {
        Pedido pedido = new Pedido();
        pedido.setId((long) pedidos.size() + 1);

        StringBuilder ids = new StringBuilder();
        for(Produto p : produtos) {
            ids.append(p.getProductId()).append(",");
        }
        ids.deleteCharAt(ids.length()-1);

        List<Produto> produtosA =  getAllProdutosId(ids.toString());

        // pegando preços dos produtos do banco de dados
        List<Double> precos = new ArrayList<>();
        produtosA.stream().forEach(p -> {
            precos.add(p.getPrice().doubleValue());
        });

        // pegando quantidades do payload
        List<Double> quantidades = new ArrayList<>();
        produtos.stream().forEach(p -> {
            quantidades.add(p.getQuantity().doubleValue());
        });

        double total = 0;

        // multiplicando quantidades e preços
        for(int i = 0; i < precos.size(); i++) {
            total += precos.get(i) * quantidades.get(i);
        }

        pedido.setTotal(total);

        pedidos.add(pedido);

        return pedido;
    }
}