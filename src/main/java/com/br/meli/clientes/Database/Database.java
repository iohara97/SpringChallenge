package com.br.meli.clientes.Database;

import com.br.meli.clientes.Entity.Cliente;
import com.br.meli.springchallenge.Entity.Produto;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Database {

    private Connection connect() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Cliente> getAllClientes() {
        Connection cn = this.connect();
        String query = "SELECT * FROM cliente;";
        try{
            List<Cliente> clientes = new ArrayList<>();
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Cliente c = new Cliente(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("endereco"),
                        rs.getString("cep")
                );
                clientes.add(c);
            }
            return clientes;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Cliente insertCliente(Cliente cliente){
        Connection cn = this.connect();

        try{
            String query = "INSERT INTO cliente (nome, cidade, estado, endereco, cep) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCidade());
            stmt.setString(3, cliente.getEstado());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getCep());


            int linhasCriadas = stmt.executeUpdate(); //executeQuery();
            ResultSet keysSet = stmt.getGeneratedKeys();
            Cliente clienteCriado;
            while (keysSet.next()) {
                int key = keysSet.getInt(1);
                PreparedStatement getStmt = cn.prepareStatement("Select * from cliente where id = ?");
                getStmt.setInt(1, key);
                ResultSet resultSet = getStmt.executeQuery();

                while (resultSet.next()) {
                    clienteCriado = new Cliente(
                            resultSet.getLong("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("cidade"),
                            resultSet.getString("estado"),
                            resultSet.getString("endereco"),
                            resultSet.getString("cep")
                    );
                    cn.close();
                    return clienteCriado;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
