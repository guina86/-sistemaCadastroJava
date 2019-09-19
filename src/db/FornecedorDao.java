/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import modelo.Fornecedor;
import utilitarios.ConectaBanco;

/**
 *
 * @author Leandro Guina
 */
public class FornecedorDao implements Dao<Fornecedor> {


    public FornecedorDao() {

    }

    @Override
    public Fornecedor get(Integer id) {
        String sql = "SELECT f.id, f.nome, f.cnpj, f.endereco, b.nome AS bairro, c.nome AS cidade, e.nome AS estado "
                + "FROM fornecedores f JOIN bairros b ON f.id_bairro = b.id "
                + "JOIN cidades c ON f.id_cidade = c.id "
                + "JOIN estados e ON f.id_estado = e.id "
                + "WHERE f.id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Fornecedor fornecedor = null;
            if (rs.next()) {
                fornecedor = new Fornecedor(rs.getInt("id"), rs.getString("nome"),rs.getString("cnpj"), rs.getString("endereco"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("estado"));
            }
            rs.close();
            return fornecedor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Fornecedor> getAll() {
        String sql = "SELECT f.id, f.nome, f.cnpj, f.endereco, b.nome AS bairro, c.nome AS cidade, e.nome AS estado "
                + "FROM fornecedores f JOIN bairros b ON f.id_bairro = b.id "
                + "JOIN cidades c ON f.id_cidade = c.id "
                + "JOIN estados e ON f.id_estado = e.id ";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Fornecedor> lista = new ArrayList<>();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(rs.getInt("id"), rs.getString("nome"),rs.getString("cnpj"), rs.getString("endereco"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("estado"));
                lista.add(fornecedor);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores (nome, cnpj, endereco, id_bairro, id_cidade, id_estado) "
                + "VALUES (?, ?, ?,(SELECT id FROM bairros WHERE nome = ?), "
                + "(SELECT id FROM cidades WHERE nome = ?), "
                + "(SELECT id FROM estados WHERE nome = ?))";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEndereco());
            ps.setString(4, fornecedor.getBairro());
            ps.setString(5, fornecedor.getCidade());
            ps.setString(6, fornecedor.getEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int save(Fornecedor fornecedor, List<String> telefones) {
        String sql = "INSERT INTO fornecedores (nome, cnpj, endereco, id_bairro, id_cidade, id_estado) "
                + "VALUES (?, ?, ?,(SELECT id FROM bairros WHERE nome = ?), "
                + "(SELECT id FROM cidades WHERE nome = ?), "
                + "(SELECT id FROM estados WHERE nome = ?))";
        String sql2 = "INSERT INTO telefones (numero) VALUES (?)";
        String sql3 = "INSERT INTO telefone_fornecedor (id_telefone, id_fornecedor) VALUES (? , ?)";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement ps2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement ps3 = conn.prepareStatement(sql3)) {
            conn.setAutoCommit(false);
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEndereco());
            ps.setString(4, fornecedor.getBairro());
            ps.setString(5, fornecedor.getCidade());
            ps.setString(6, fornecedor.getEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                 id = rs.getInt(1);
            }
            rs.close();
            for(String telefone : telefones){
                ps2.setString(1, telefone);
                ps2.executeUpdate();
                ResultSet rs2 = ps2.getGeneratedKeys();
                int idTel = 0;
                if(rs2.next()){
                    idTel = rs2.getInt(1);
                }
                ps3.setInt(1, idTel);
                ps3.setInt(2, id);
                ps3.executeUpdate();
                rs2.close();
            }
            conn.commit();
            conn.setAutoCommit(true);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void update(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedores SET nome = ?, cnpj = ?, endereco = ?, "
                + "id_bairro = (SELECT id FROM bairros WHERE nome = ?), "
                + "id_cidade = (SELECT id FROM cidades WHERE nome = ?), "
                + "id_estado = (SELECT id FROM estados WHERE nome = ?) "
                + "WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEndereco());
            ps.setString(4, fornecedor.getBairro());
            ps.setString(5, fornecedor.getCidade());
            ps.setString(6, fornecedor.getEstado());
            ps.setInt(7, fornecedor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Fornecedor fornecedor) {
        String sql = "DELETE FROM fornecedores WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fornecedor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM fornecedores WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> telefones() {
        String sql = "SELECT numero FROM telefones JOIN telefone_fornecedor ON id = id_telefone ORDER BY numero";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<String> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getString("numero"));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<String> telefones(Fornecedor fornecedor) {
        String sql = "SELECT numero FROM telefones JOIN telefone_fornecedor ON id = id_telefone WHERE id_fornecedor = ? ORDER BY numero";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fornecedor.getId());
            ResultSet rs = ps.executeQuery();
            List<String> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getString("numero"));
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> bairros(){
        String sql = "SELECT nome FROM bairros ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<String> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    public List<String> bairros(String cidade){
        String sql = "SELECT nome FROM bairros WHERE id_cidade = (SELECT id FROM cidades WHERE nome = ?) ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cidade);
            ResultSet rs = ps.executeQuery();
            List<String> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    
    public List<String> cidades(){
        String sql = "SELECT nome FROM cidades ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<String> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    
    public List<String> cidades(String estado){
        String sql = "SELECT nome FROM cidades WHERE id_estado = (SELECT id FROM estados WHERE nome = ?) ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            List<String> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    
    public List<String> estados(){
        String sql = "SELECT nome FROM estados ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<String> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }

}
