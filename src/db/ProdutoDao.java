/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import modelo.Bairro;
import modelo.Produto;
import modelo.Cidade;
import modelo.Estado;
import modelo.Fornecedor;
import modelo.Telefone;
import utilitarios.ConectaBanco;

/**
 *
 * @author Leandro Guina
 */
public class ProdutoDao implements Dao<Produto> {

    public ProdutoDao() {

    }
    
    @Override
    public Produto get(Integer id) {
        String sql = "SELECT p.id, p.nome, p.preco_compra, p.preco_venda, p.quantidade, p.id_fornecedor, f.nome AS fornecedor, f.cnpj, f.endereco, f.id_bairro, b.nome AS bairro, b.id_cidade, c.nome AS cidade, c.id_estado, e.nome AS estado, e.sigla  "
                + "FROM produtos p JOIN fornecedores f ON p.id_fornecedor = f.id "
                + "JOIN bairros b ON f.id_bairro = b.id "
                + "JOIN cidades c ON b.id_cidade = c.id "
                + "JOIN estados e ON c.id_estado = e.id "
                + "WHERE p.id = ?";
        String sql2 = "SELECT id, numero FROM telefones JOIN telefone_fornecedor ON id = id_telefone WHERE id_fornecedor = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Produto produto = null;
            if (rs.next()) {
                ps2.setInt(1, rs.getInt("id_fornecedor"));
                ResultSet rs2 = ps2.executeQuery();
                List<Telefone> telefones = new ArrayList<>();
                while (rs2.next()) {
                    telefones.add(new Telefone(rs2.getInt("id"), rs2.getString("numero")));
                }
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                Bairro bairro = new Bairro(rs.getInt("id_bairro"), rs.getString("bairro"), cidade);
                Fornecedor fornecedor = new Fornecedor(rs.getInt("id_fornecedor"), rs.getString("fornecedor"), rs.getString("cnpj"), rs.getString("endereco"), bairro, telefones);
                produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco_compra"), rs.getDouble("preco_venda"), rs.getInt("quantidade"), fornecedor );
                rs2.close();
            }
            rs.close();
            return produto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Produto> getAll() {
        String sql = "SELECT p.id, p.nome, p.preco_compra, p.preco_venda, p.quantidade, p.id_fornecedor, f.nome AS fornecedor, f.cnpj, f.endereco, f.id_bairro, b.nome AS bairro, b.id_cidade, c.nome AS cidade, c.id_estado, e.nome AS estado, e.sigla  "
                + "FROM produtos p JOIN fornecedores f ON p.id_fornecedor = f.id "
                + "JOIN bairros b ON f.id_bairro = b.id "
                + "JOIN cidades c ON b.id_cidade = c.id "
                + "JOIN estados e ON c.id_estado = e.id";
        String sql2 = "SELECT id, numero FROM telefones JOIN telefone_fornecedor ON id = id_telefone WHERE id_fornecedor = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql2);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                ps.setInt(1, rs.getInt("id_fornecedor"));
                ResultSet rs2 = ps.executeQuery();
                List<Telefone> telefones = new ArrayList<>();
                while (rs2.next()) {
                    telefones.add(new Telefone(rs2.getInt("id"), rs2.getString("numero")));
                }
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                Bairro bairro = new Bairro(rs.getInt("id_bairro"), rs.getString("bairro"), cidade);
                Fornecedor fornecedor = new Fornecedor(rs.getInt("id_fornecedor"), rs.getString("fornecedor"), rs.getString("cnpj"), rs.getString("endereco"), bairro, telefones);
                produtos.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco_compra"), rs.getDouble("preco_venda"), rs.getInt("quantidade"), fornecedor ));
                rs2.close();
            }
            return produtos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Produto> getAllFrom(Fornecedor fornecedor) {
        String sql = "SELECT * FROM produtos WHERE id_fornecedor = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fornecedor.getId());
            ResultSet rs = ps.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                produtos.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco_compra"), rs.getDouble("preco_venda"), rs.getInt("quantidade"), fornecedor ));
            }
            rs.close();
            return produtos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco_compra, preco_venda, quantidade, id_fornecedor) VALUES (?, ?, ?, ?, ?)"; 
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPrecoCompra());
            ps.setDouble(3, produto.getPrecoVenda());
            ps.setInt(4, produto.getQuantidade());
            ps.setInt(5, produto.getFornecedor().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            return id;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void update(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco_compra = ?, preco_venda = ?, quantidade = ?, id_fornecedor = ? WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPrecoCompra());
            ps.setDouble(3, produto.getPrecoVenda());
            ps.setInt(4, produto.getQuantidade());
            ps.setInt(5, produto.getFornecedor().getId());
            ps.setInt(6, produto.getId());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Produto produto) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, produto.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<Produto> search(String nome) {
        String sql = "SELECT p.id, p.nome, p.preco_compra, p.preco_venda, p.quantidade, p.id_fornecedor, f.nome AS fornecedor, f.cnpj, f.endereco, f.id_bairro, b.nome AS bairro, b.id_cidade, c.nome AS cidade, c.id_estado, e.nome AS estado, e.sigla  "
                + "FROM produtos p JOIN fornecedores f ON p.id_fornecedor = f.id "
                + "JOIN bairros b ON f.id_bairro = b.id "
                + "JOIN cidades c ON b.id_cidade = c.id "
                + "JOIN estados e ON c.id_estado = e.id "
                + "WHERE p.nome LIKE ?";
        String sql2 = "SELECT id, numero FROM telefones JOIN telefone_fornecedor ON id = id_telefone WHERE id_fornecedor = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            ps.setString(1, '%'+nome+'%');
            ResultSet rs = ps.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                ps2.setInt(1, rs.getInt("id_fornecedor"));
                ResultSet rs2 = ps2.executeQuery();
                List<Telefone> telefones = new ArrayList<>();
                while (rs2.next()) {
                    telefones.add(new Telefone(rs2.getInt("id"), rs2.getString("numero")));
                }
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                Bairro bairro = new Bairro(rs.getInt("id_bairro"), rs.getString("bairro"), cidade);
                Fornecedor fornecedor = new Fornecedor(rs.getInt("id_fornecedor"), rs.getString("fornecedor"), rs.getString("cnpj"), rs.getString("endereco"), bairro, telefones);
                produtos.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco_compra"), rs.getDouble("preco_venda"), rs.getInt("quantidade"), fornecedor ));
                rs2.close();
            }
            rs.close();
            return produtos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Produto find(String nome) {
        String sql = "SELECT p.id, p.nome, p.preco_compra, p.preco_venda, p.quantidade, p.id_fornecedor, f.nome AS fornecedor, f.cnpj, f.endereco, f.id_bairro, b.nome AS bairro, b.id_cidade, c.nome AS cidade, c.id_estado, e.nome AS estado, e.sigla  "
                + "FROM produtos p JOIN fornecedores f ON p.id_fornecedor = f.id "
                + "JOIN bairros b ON f.id_bairro = b.id "
                + "JOIN cidades c ON b.id_cidade = c.id "
                + "JOIN estados e ON c.id_estado = e.id "
                + "WHERE p.nome LIKE ?";
        String sql2 = "SELECT id, numero FROM telefones JOIN telefone_fornecedor ON id = id_telefone WHERE id_fornecedor = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            ps.setString(1, '%'+nome+'%');
            ResultSet rs = ps.executeQuery();
            Produto produto = null;
            if (rs.next()) {
                ps2.setInt(1, rs.getInt("id_fornecedor"));
                ResultSet rs2 = ps2.executeQuery();
                List<Telefone> telefones = new ArrayList<>();
                while (rs2.next()) {
                    telefones.add(new Telefone(rs2.getInt("id"), rs2.getString("numero")));
                }
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                Bairro bairro = new Bairro(rs.getInt("id_bairro"), rs.getString("bairro"), cidade);
                Fornecedor fornecedor = new Fornecedor(rs.getInt("id_fornecedor"), rs.getString("fornecedor"), rs.getString("cnpj"), rs.getString("endereco"), bairro, telefones);
                produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco_compra"), rs.getDouble("preco_venda"), rs.getInt("quantidade"), fornecedor );
                rs2.close();
            }
            rs.close();
            return produto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
 
}
