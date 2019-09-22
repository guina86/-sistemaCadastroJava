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

    public TelefoneDao() {
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

    public int save(Telefone telefone,Integer ownerId, String tipo) {
        String sql1 = "INSERT INTO telefones (numero) VALUES (?)";
        String sql2 = "";
        if (tipo.equals("Cliente")) {
            sql2 = "INSERT INTO telefone_cliente (id_telefone, id_cliente) VALUES (?, ?)";
        } else {
            sql2 = "INSERT INTO telefone_fornecedor (id_telefone, id_fornecedor) VALUES (?, ?)";
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
            ps2.setInt(2, ownerId);
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

}
