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
import modelo.Estado;
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
        String sql = "SELECT b.id, b.nome, b.id_cidade, c.nome as cidade, c.id_estado, e.nome as estado, e.sigla "
                + "FROM bairros b JOIN cidades c ON b.id_cidade = c.id JOIN estados e ON c.id_estado = e.id WHERE b.id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Bairro bairro = null;
            if (rs.next()) {
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                bairro = new Bairro(rs.getInt("id"), rs.getString("nome"), cidade);
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
        String sql = "SELECT b.id, b.nome, b.id_cidade, c.nome as cidade, c.id_estado, e.nome as estado, e.sigla "
                + "FROM bairros b JOIN cidades c ON b.id_cidade = c.id JOIN estados e ON c.id_estado = e.id ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Bairro> list = new ArrayList<>();
            while (rs.next()) {
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                list.add(new Bairro(rs.getInt("id"), rs.getString("nome"), cidade));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Bairro> getAllFrom(Cidade cidade) {
        String sql = "SELECT b.id, b.nome, b.id_cidade, c.nome as cidade, c.id_estado, e.nome as estado, e.sigla "
                + "FROM bairros b JOIN cidades c ON b.id_cidade = c.id JOIN estados e ON c.id_estado = e.id WHERE id_cidade = ? ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cidade.getId());
            ResultSet rs = ps.executeQuery();
            List<Bairro> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Bairro(rs.getInt("id"), rs.getString("nome"), cidade));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Bairro bairro) {
        String sql = "INSERT INTO bairros (nome, id_cidade) VALUES (?, ?)"; 
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, bairro.getNome());
            ps.setInt(2, bairro.getCidade().getId());
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
        String sql = "UPDATE bairros SET nome = ?, id_cidade = ? WHERE id = ?";
        try(Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, bairro.getNome());
            ps.setInt(2, bairro.getCidade().getId());
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

    public List<Cidade> cidades(){
        String sql = "SELECT c.id, c.nome, c.id_estado, e.nome AS estado, e.sigla FROM cidades c JOIN estados e ON c.id_estado = e.id  ORDER BY nome";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Cidade> lista = new ArrayList<>();
            while (rs.next()) {
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                lista.add(new Cidade(rs.getInt("id"), rs.getString("nome"), estado));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
 
}
