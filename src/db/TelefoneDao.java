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
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, telefone.getNumero());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void update(Telefone telefone) {
        String sql = "UPDATE telefones SET numero = ? WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, telefone.getNumero());
            ps.setInt(2, telefone.getId());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Telefone telefone) {
        String sql = "DELETE FROM telefones WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, telefone.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM telefones WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
     
}
