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
import utilitarios.ConectaBanco;

/**
 *
 * @author Leandro Guina
 */
public class BairroDao implements Dao<Bairro> {

    public BairroDao() {

    }
    
    @Override
    public Bairro get(Integer id) {
        String sql = "SELECT b.id as id, b.nome as nome, c.nome as cidade FROM bairros b JOIN cidades c ON b.id_cidade = c.id WHERE b.id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Bairro bairro = null;
            if (rs.next()) {
                bairro = new Bairro(rs.getInt("id"), rs.getString("nome"), rs.getString("cidade"));
            }
            rs.close();
            return bairro;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bairro> getAll() {
        String sql = "SELECT b.id as id, b.nome as nome, c.nome as cidade FROM bairros b JOIN cidades c ON b.id_cidade = c.id";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Bairro> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Bairro(rs.getInt("id"), rs.getString("nome"), rs.getString("cidade")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Bairro bairro) {
        String sql = "INSERT INTO bairros (nome, id_cidade) VALUES (?, (SELECT id FROM cidades WHERE nome = ?))"; 
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, bairro.getNome());
            ps.setString(2, bairro.getCidade());
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
    public void update(Bairro bairro) {
        String sql = "UPDATE bairros SET nome = ?, id_cidade = (SELECT id FROM cidades WHERE nome = ?) WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, bairro.getNome());
            ps.setString(2, bairro.getCidade());
            ps.setInt(3, bairro.getId());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Bairro bairro) {
        String sql = "DELETE FROM bairros WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, bairro.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM bairros WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
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
 
}
