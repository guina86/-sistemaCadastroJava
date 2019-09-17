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
        String sql = "SELECT c.id as id, c.nome as nome, e.nome as estado FROM cidades c JOIN estados e ON c.id_estado = e.id WHERE c.id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Cidade cidade = null;
            if (rs.next()) {
                cidade = new Cidade(rs.getInt("id"), rs.getString("nome"), rs.getString("estado"));
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
        String sql = "SELECT c.id as id, c.nome as nome, e.nome as estado FROM cidades c JOIN estados e ON c.id_estado = e.id";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Cidade> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Cidade(rs.getInt("id"), rs.getString("nome"), rs.getString("estado")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Cidade cidade) {
        String sql = "INSERT INTO cidades (nome, id_estado) VALUES (?, (SELECT id FROM estados WHERE nome = ?))";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cidade.getNome());
            ps.setString(2, cidade.getEstado());
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
        String sql = "UPDATE cidades SET nome = ?, id_estado = (SELECT id FROM estados WHERE nome = ?) WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cidade.getNome());
            ps.setString(2, cidade.getEstado());
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

    public List<String> estados() {
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
