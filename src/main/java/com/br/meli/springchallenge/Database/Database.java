package com.br.meli.springchallenge.Database;

import com.br.meli.exception.CustomException;
import com.br.meli.springchallenge.Entity.Pedido;
import com.br.meli.springchallenge.Entity.Produto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe onde é feita a conexão direta com o banco de dados
 */
@Component
public class Database {

    List<Pedido> pedidos = new ArrayList<>();

    /**
     * Método para conectar com o banco de dados
     */
    private Connection connect() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Método com uma query para listar todos os produtos
     * @return uma lista de produtos ordenada por nome
     */
    public List<Produto> getAllProdutos() throws SQLException {
        return queryProduto("select * from produto order by name;");
    }

    /**
     * Método com uma query para listar uma lista de produtos conforme categoria
     * @deprecated
     * @param category
     * @return lista de produtos conforme categoria
     */
    @SneakyThrows
    public List<Produto> getAllProdutosByCategory(String category)  {
        return queryProduto("select * from produto where category = " + category);
    }

    // resolvendo conflito
    //public List<Produto> getAllProdutosId(String ids) {
    //    return queryProduto("select * from produto where productId in (" + ids + ")");
    // Revisar

    /**
     * Método para listar produtos com filtros múltiplos
     * @param filters
     * @return uma lista de produtos
     */
    public List<Produto> getAllProdutosByFilters(HashMap<String, String> filters) throws SQLException {

        String sqlQuery = "select * from produto where "; // category = '\" + category + \"'\"";

        for (Map.Entry<String, String> entry : filters.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equals("price")) {
                sqlQuery += key + " = " + value + " and ";
            } else if(key.equals("freeShipping")){
                int parsedValue = value.equalsIgnoreCase("true") ? 1 : 0;
                sqlQuery += " free_shipping =  " + parsedValue + " AND ";
            } else if(!key.equals("order")){
                sqlQuery += key + " LIKE '" + value + "' and ";
            }
        }

        // retira o último and da query
        StringBuilder sb = new StringBuilder(sqlQuery);
//        for (int i = 0; i <= 4; i++) {
//            sb.deleteCharAt(sb.length() - 1);
//        }
        sb.delete(sb.length()-5, sb.length()-1);

        String orderBy = " ORDER BY 1";

        if (filters.containsKey("order")) {
            String orderStr = filters.get("order");
            int orderNum = Integer.parseInt(orderStr);

            switch (orderNum) {
                case 0:
                    orderBy = " ORDER BY name";
                    break;
                case 1:
                    orderBy = " ORDER BY name DESC";
                    break;
                case 2:
                    orderBy = " ORDER BY price DESC";
                    break;
                case 3:
                    orderBy = " ORDER BY price ASC";
                    break;
            }
        }
        sb.append(orderBy);

        return queryProduto(sb.toString());
    }

    /**
     * Método que executa uma query
     * @param query
     * @return uma lista de produtos
     * @exception SQLException
     */
    private List<Produto> queryProduto(String query) throws SQLException {
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

        cn.close();
        return produtos;
    }

    /**
     * Método que executa uma query de teste
     * @param query
     * @exception SQLException
     */
    public void customQuery(String query) {
        try {
            Connection cn = connect();

            Statement statement = cn.createStatement();

            PreparedStatement stmt = cn.prepareStatement(query);

            stmt.execute(); //executeQuery();

            System.out.println("Query executada com sucesso em customQuery()");
            cn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método para salvar os produtos de uma lista
     * @param produtos
     * @return lista de produtos cadastrados
     */
    public List<Produto> insertProdutoList(List<Produto> produtos) throws SQLException {
        List<Produto> listaProdutosCadastrados = new ArrayList<>();
        for (Produto elem : produtos) {
            listaProdutosCadastrados.add(insertProdutoSingle(elem));
        }
        return listaProdutosCadastrados;
    }

    /**
    * Método para salvar um produto
    * @param produto
    * @return produto
    * @exception SQLException
    */
    private Produto insertProdutoSingle(Produto produto) throws SQLException {
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
        return null;
    }

    /**
     * Método para atualizar o banco de dados e devolver um pedido com seus produtos e realizando uma baixa no estoque
     * @param produtos
     * @return pedido
     */
    public Pedido criarPedido(List<Produto> produtos){
        Pedido pedido = new Pedido();
        List<Produto> listaProdutos = new ArrayList<>();
        for (Produto p: produtos){
            listaProdutos.add(criarPedidoPorProduto(p));
        }
        pedido.setId(listaProdutos.size());
        pedido.setProdutos(listaProdutos);
        return pedido;
    }

    /**
     * Método para dar baixa no estoque de um produto especifico
     * @param p
     * @return produto atualizado
     * @exception SQLException
     */
    private Produto criarPedidoPorProduto(Produto p){
        try {
            Connection cn = this.connect();
            //String query = "SELECT productId, name, category, brand, price, " + p.getQuantity() + " AS quantity , free_shipping, prestige FROM produto WHERE productId = ?";
            String query = "UPDATE produto SET quantity = quantity - ? WHERE productId = ?";
            try {
                PreparedStatement stmt = cn.prepareStatement(query);
                stmt.setLong(1, p.getQuantity());
                stmt.setLong(2, p.getProductId());
                int linhasAlteradas = stmt.executeUpdate(); //executeQuery();


                    query = "SELECT * FROM produto WHERE productId = ?";
                    stmt = cn.prepareStatement(query);
                    stmt.setLong(1, p.getProductId());
                    ResultSet rs = stmt.executeQuery();


                    Produto newProduto = new Produto(
                            rs.getLong("productId"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getString("brand"),
                            rs.getBigDecimal("price"),
                            rs.getInt("quantity"),
                            rs.getBoolean("free_shipping"),
                            rs.getString("prestige")
                    );
                    cn.close();
                    return newProduto;
                } catch (SQLiteException e){
                    if(e.getErrorCode() == 19){
                        return null;
                    }
                    throw new CustomException("Erro ao acessar o banco de dados, refaça a solicitação.");
                }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}