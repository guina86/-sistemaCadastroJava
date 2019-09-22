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
import modelo.Fornecedor;
import modelo.Telefone;
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
        String sql = "SELECT f.id, f.nome, f.cnpj, f.endereco, f.id_bairro, b.nome AS bairro, b.id_cidade, c.nome AS cidade, c.id_estado, e.nome AS estado, e.sigla "
                + "FROM fornecedores f JOIN bairros b ON f.id_bairro = b.id "
                + "JOIN cidades c ON b.id_cidade = c.id "
                + "JOIN estados e ON c.id_estado = e.id "
                + "WHERE f.id = ?";
        String sql2 = "SELECT id, numero FROM telefones JOIN telefone_fornecedor ON id = id_telefone WHERE id_fornecedor = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Fornecedor fornecedor = null;
            if (rs.next()) {
                ps2.setInt(1, id);
                ResultSet rs2 = ps2.executeQuery();
                List<Telefone> telefones = new ArrayList<>();
                while (rs2.next()) {
                    telefones.add(new Telefone(rs2.getInt("id"), rs2.getString("numero")));
                }
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                Bairro bairro = new Bairro(rs.getInt("id_bairro"), rs.getString("bairro"), cidade);
                fornecedor = new Fornecedor(rs.getInt("id"), rs.getString("nome"), rs.getString("cnpj"), rs.getString("endereco"), bairro, telefones);
                rs2.close();
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
        String sql = "SELECT f.id, f.nome, f.cnpj, f.endereco, b.id AS id_bairro, c.id AS id_cidade, e.id AS id_estado, b.nome AS bairro, c.nome AS cidade, e.nome AS estado, e.sigla "
                + "FROM fornecedores f JOIN bairros b ON f.id_bairro = b.id "
                + "JOIN cidades c ON b.id_cidade = c.id "
                + "JOIN estados e ON c.id_estado = e.id ";
        String sql2 = "SELECT id, numero FROM telefones JOIN telefone_fornecedor ON id = id_telefone WHERE id_fornecedor = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql2);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Fornecedor> fornecedores = new ArrayList<>();
            while (rs.next()) {
                ps.setInt(1, rs.getInt("id"));
                ResultSet rs2 = ps.executeQuery();
                List<Telefone> telefones = new ArrayList<>();
                while (rs2.next()) {
                    telefones.add(new Telefone(rs2.getInt("id"), rs2.getString("numero")));
                }
                Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("estado"), rs.getString("sigla"));
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("cidade"), estado);
                Bairro bairro = new Bairro(rs.getInt("id_bairro"), rs.getString("bairro"), cidade);
                fornecedores.add(new Fornecedor(rs.getInt("id"), rs.getString("nome"), rs.getString("cnpj"), rs.getString("endereco"), bairro, telefones));
                rs2.close();
            }
            return fornecedores;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores (nome, cnpj, endereco, id_bairro) VALUES (?, ?, ?, ?)";
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
            ps.setInt(4, fornecedor.getBairro().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            if (!fornecedor.getTelefones().isEmpty()) {
                for (Telefone telefone : fornecedor.getTelefones()) {
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
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public void update(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedores SET nome = ?, cnpj = ?, endereco = ?, id_bairro = ? WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEndereco());
            ps.setInt(4, fornecedor.getBairro().getId());
            ps.setInt(5, fornecedor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Fornecedor fornecedor) {
        String sql = "DELETE FROM fornecedores WHERE id = ?";
        String sql2 = "DELETE FROM telefones WHERE id = ?";
        String sql3 = "DELETE FROM  telefone_fornecedor WHERE id_fornecedor = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                PreparedStatement ps3 = conn.prepareStatement(sql3)) {
            ps.setInt(1, fornecedor.getId());
            ps.executeUpdate();
            if (!fornecedor.getTelefones().isEmpty()) {
                for (Telefone telefone : fornecedor.getTelefones()) {
                    ps2.setInt(1, telefone.getId());
                    ps2.executeUpdate();
                }
                ps3.setInt(1, fornecedor.getId());
                ps3.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Telefone> telefones(Fornecedor fornecedor) {
        String sql = "SELECT id, numero FROM telefones JOIN telefone_fornecedor ON id = id_telefone WHERE id_fornecedor = ? ORDER BY numero";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fornecedor.getId());
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

    public List<Bairro> bairros(Cidade cidade) {
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

    public List<Cidade> cidades(Estado estado) {
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
