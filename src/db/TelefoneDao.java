/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import modelo.Telefone;
import utilitarios.ConectaBanco;

/**
 *
 * @author Leandro Guina
 */
public class TelefoneDao implements Dao<Telefone> {

//    public final Map<String, Integer> map;
    public TelefoneDao() {
//        map = new HashMap<>(getMap());
    }

    @Override
    public Telefone get(Integer id) {
        String sql = "SELECT id, numero FROM telefones WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Telefone telefone = null;
            if (rs.next()) {
                telefone = new Telefone(rs.getInt("id"), rs.getString("numero"));
            }
            rs.close();
            return telefone;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Telefone> getAll() {
        String sql = "SELECT id, numero FROM telefones";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            List<Telefone> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Telefone(rs.getInt("id"), rs.getString("numero")));
            }
            rs.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Telefone telefone) {
        String sql = "INSERT INTO telefones (numero) VALUES (?)";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, telefone.getNumero());
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

    public int save(Telefone telefone, String nome, String tipo) {
        String sql1 = "INSERT INTO telefones (numero) VALUES (?)";
        String sql2 = "";
        if (tipo.equals("Cliente")) {
            sql2 = "INSERT INTO telefone_cliente (id_telefone, id_cliente) VALUES (? , (SELECT id FROM clientes WHERE nome = ?))";
        } else {
            sql2 = "INSERT INTO telefone_fornecedor (id_telefone, id_fornecedor) VALUES (? , (SELECT id FROM fornecedores WHERE nome = ?))";
        }
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            conn.setAutoCommit(false);
            ps1.setString(1, telefone.getNumero());
            ps1.executeUpdate();
            ResultSet rs = ps1.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            ps2.setInt(1, id);
            ps2.setString(2, nome);
            ps2.executeUpdate();
            rs.close();
            conn.commit();
            conn.setAutoCommit(true);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void update(Telefone telefone) {
        String sql = "UPDATE telefones SET numero = ? WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, telefone.getNumero());
            ps.setInt(2, telefone.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(Telefone telefone, boolean tipo) {
        String sql = "UPDATE telefones SET numero = ? WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, telefone.getNumero());
            ps.setInt(2, telefone.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Telefone telefone) {
        String sql = "DELETE FROM telefones WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, telefone.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id, String tipo) {
        String sql1 = "DELETE FROM telefones WHERE id = ?";
        String sql2 = "";
        if (tipo.equals("Cliente")) {
            sql2 = "DELETE FROM telefone_cliente WHERE id_telefone = ?";
        } else {
            sql2 = "DELETE FROM telefone_fornecedor WHERE id_telefone = ?";
        }
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            conn.setAutoCommit(false);
            ps1.setInt(1, id);
            ps2.setInt(1, id);
            ps1.executeUpdate();
            ps2.executeUpdate();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> clientes() {
        String sql = "SELECT nome FROM clientes";
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

    public List<String> fornecedores() {
        String sql = "SELECT nome FROM fornecedores";
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

    public List<Telefone> numeros(String nome, String tipo) {
        String sql = "";
        if (tipo.equals("Cliente")) {
            sql = "SELECT t.id, t.numero FROM telefones as t JOIN telefone_cliente as tc ON t.id = tc.id_telefone "
                    + "JOIN clientes as c ON tc.id_cliente = c.id WHERE c.nome = ?";
        } else {
            sql = "SELECT t.id, t.numero FROM telefones as t JOIN telefone_fornecedor as tc ON t.id = tc.id_telefone "
                    + "JOIN fornecedor as f ON tc.id_cliente = f.id WHERE f.nome = ?";
        }
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            List<Telefone> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Telefone(rs.getInt("id"), rs.getString("numero")));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
