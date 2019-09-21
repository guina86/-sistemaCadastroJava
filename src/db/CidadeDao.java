/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import modelo.Cidade;
import modelo.Estado;
import utilitarios.ConectaBanco;

/**
 *
 * @author Leandro Guina
 */
public class CidadeDao implements Dao<Cidade> {

    public CidadeDao() {

    }

    @Override
    public Cidade get(Integer id) {
        String sql = "SELECT c.id, c.nome, c.id_estado, e.nome as estado, e.sigla FROM cidades c JOIN estados e ON c.id_estado = e.id WHERE c.id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Cidade cidade = null;
            if (rs.next()) {
                cidade = new Cidade(rs.getInt("id"), rs.getString("nome"), new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla")));
            }
            rs.close();
            return cidade;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cidade> getAll() {
        String sql = "SELECT c.id, c.nome, c.id_estado, e.nome as estado, e.sigla FROM cidades c JOIN estados e ON c.id_estado = e.id ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Cidade> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Cidade(rs.getInt("id"), rs.getString("nome"), new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"))));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cidade> getAllFrom(Estado estado) {
        String sql = "SELECT c.id, c.nome, c.id_estado, e.nome as estado, e.sigla FROM cidades c JOIN estados e ON c.id_estado = e.id WHERE id_estado = ? ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, estado.getId());
            ResultSet rs = ps.executeQuery();
            List<Cidade> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Cidade(rs.getInt("id"), rs.getString("nome"), new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"))));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Cidade cidade) {
        String sql = "INSERT INTO cidades (nome, id_estado) VALUES (?, ?)";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cidade.getNome());
            ps.setInt(2, cidade.getEstado().getId());
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

    @Override
    public void update(Cidade cidade) {
        String sql = "UPDATE cidades SET nome = ?, id_estado = ? WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cidade.getNome());
            ps.setInt(2, cidade.getEstado().getId());
            ps.setInt(3, cidade.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Cidade cidade) {
        String sql = "DELETE FROM cidades WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cidade.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM cidades WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Estado> estados() {
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
