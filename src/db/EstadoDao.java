/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import modelo.Estado;
import utilitarios.ConectaBanco;

/**
 *
 * @author Leandro Guina
 */
public class EstadoDao implements Dao<Estado> {

    @Override
    public Estado get(Integer id) {
        String sql = "SELECT * FROM estados WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Estado estado = null;
            if (rs.next()) {
                estado = new Estado(rs.getInt("id"), rs.getString("nome"), rs.getString("sigla"));
            }
            rs.close();
            return estado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Estado> getAll() {
        String sql = "SELECT * FROM estados ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Estado> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Estado(rs.getInt("id"), rs.getString("nome"), rs.getString("sigla")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public int save(Estado estado) {
        String sql = "INSERT INTO estados (nome, sigla) VALUES (?, ?)"; 
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, estado.getNome());
            ps.setString(2, estado.getSigla());
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
    public void update(Estado estado) {
        String sql = "UPDATE estados SET nome = ?, sigla = ? WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, estado.getNome());
            ps.setString(2, estado.getSigla());
            ps.setInt(3, estado.getId());
            ps.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Estado estado) {
        String sql = "DELETE FROM estados WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, estado.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM estados WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
