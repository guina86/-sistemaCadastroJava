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
import modelo.Cidade;
import modelo.Cliente;
import modelo.Estado;
import modelo.Telefone;
import utilitarios.ConectaBanco;

/**
 *
 * @author Leandro Guina
 */
public class ClienteDao implements Dao<Cliente> {


    public ClienteDao() {

    }

    @Override
    public Cliente get(Integer id) {
        String sql = "SELECT c.id, c.nome, c.endereco, c.rg, c.cpf, c.id_bairro, b.nome AS bairro, b.id_cidade, ci.nome AS cidade, ci.id_estado, e.nome AS estado, e.sigla "
                + "FROM clientes c JOIN bairros b ON c.id_bairro = b.id "
                + "JOIN cidades ci ON b.id_cidade = ci.id "
                + "JOIN estados e ON ci.id_estado = e.id "
                + "WHERE c.id = ?";
        String sql2 = "SELECT id, numero FROM telefones JOIN telefone_cliente ON id = id_telefone WHERE id_cliente = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Cliente cliente = null;
            if (rs.next()) {
                ps2.setInt(1, id);
                ResultSet rs2 = ps2.executeQuery();
                List<Telefone> telefones = new ArrayList<>();
                while(rs2.next()){
                    telefones.add(new Telefone(rs2.getInt("id"), rs2.getString("numero")));
                }
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                Bairro bairro = new Bairro(rs.getInt("id_bairro"), rs.getString("bairro"), cidade);
                cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("rg"), rs.getString("cpf"), bairro, telefones);
                rs2.close();
            }
            rs.close();
            return cliente;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cliente> getAll() {
        String sql = "SELECT c.id, c.nome, c.endereco, c.rg, c.cpf, c.id_bairro, b.nome AS bairro, b.id_cidade, ci.nome AS cidade, ci.id_estado, e.nome AS estado, e.sigla "
                + "FROM clientes c JOIN bairros b ON c.id_bairro = b.id "
                + "JOIN cidades ci ON b.id_cidade = ci.id "
                + "JOIN estados e ON ci.id_estado = e.id ";
        String sql2 = "SELECT id, numero FROM telefones JOIN telefone_cliente ON id = id_telefone WHERE id_cliente = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql2);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {
                ps.setInt(1, rs.getInt("id"));
                ResultSet rs2 = ps.executeQuery();
                List<Telefone> telefones = new ArrayList<>();
                while(rs2.next()){
                    telefones.add(new Telefone(rs2.getInt("id"), rs2.getString("numero")));
                }
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                Bairro bairro = new Bairro(rs.getInt("id_bairro"), rs.getString("bairro"), cidade);
                clientes.add(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("rg"), rs.getString("cpf"), bairro, telefones));
                rs2.close();
            }
            rs.close();
            return clientes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, endereco, rg, cpf, id_bairro) VALUES (?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO telefones (numero) VALUES (?)";
        String sql3 = "INSERT INTO telefone_cliente (id_telefone, id_cliente) VALUES (? , ?)";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement ps2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement ps3 = conn.prepareStatement(sql3)) {
            conn.setAutoCommit(false);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getCpf());
            ps.setInt(5, cliente.getBairro().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            if (!cliente.getTelefones().isEmpty()) {
                for (Telefone telefone : cliente.getTelefones()) {
                    ps2.setString(1, telefone.getNumero());
                    ps2.executeUpdate();
                    ResultSet rs2 = ps2.getGeneratedKeys();
                    int idTel = 0;
                    if (rs2.next()) {
                        idTel = rs2.getInt(1);
                    }
                    ps3.setInt(1, idTel);
                    ps3.setInt(2, id);
                    ps3.executeUpdate();
                    rs2.close();
                }
            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    

    @Override
    public void update(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, endereco = ?, rg = ?, cpf = ?, id_bairro = ? WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getCpf());
            ps.setInt(5, cliente.getBairro().getId());
            ps.setInt(6, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Cliente cliente) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        String sql2 = "DELETE FROM telefones WHERE id = ?";
        String sql3 = "DELETE FROM  telefone_cliente WHERE id_cliente = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                PreparedStatement ps3 = conn.prepareStatement(sql3)) {
            ps.setInt(1, cliente.getId());
            ps.executeUpdate();
            if (!cliente.getTelefones().isEmpty()) {
                for (Telefone telefone : cliente.getTelefones()) {
                    ps2.setInt(1, telefone.getId());
                    ps2.executeUpdate();
                }
                ps3.setInt(1, cliente.getId());
                ps3.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public List<Telefone> telefones(Cliente cliente) {
        String sql = "SELECT numero FROM telefones JOIN telefone_cliente ON id = id_telefone WHERE id_cliente = ? ORDER BY numero";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cliente.getId());
            ResultSet rs = ps.executeQuery();
            List<Telefone> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Telefone(rs.getInt("id"),rs.getString("numero")));
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Bairro> bairros(Cidade cidade){
        String sql = "SELECT * FROM bairros WHERE id_cidade = ? ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cidade.getId());
            ResultSet rs = ps.executeQuery();
            List<Bairro> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Bairro(rs.getInt("id"), rs.getString("nome"), cidade));
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
      public List<Cidade> cidades(Estado estado){
        String sql = "SELECT * FROM cidades WHERE id_estado = ? ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, estado.getId());
            ResultSet rs = ps.executeQuery();
            List<Cidade> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Cidade(rs.getInt("id"), rs.getString("nome"), estado));
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    public List<Estado> estados(){
        String sql = "SELECT * FROM estados ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Estado> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Estado(rs.getInt("id"), rs.getString("nome"), rs.getString("sigla")));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }

}
