/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoprojeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import utilitarios.ConectaBanco;

/**
 *
 * @author Leandro Guina
 */
public class VideoProjeto {

    public static void main(String[] args) {

        long inicio1 = System.currentTimeMillis();
        long inicio = System.currentTimeMillis();
        List<Cliente> all2 = getAll2();
        long split = System.currentTimeMillis();
        List<Cliente> all = getAll();
        long fim = System.currentTimeMillis();
        List<Cliente> all3 = getAll2();
        long fim2 = System.currentTimeMillis();
        List<Cliente> all4 = getAll();
        long fim3 = System.currentTimeMillis();
        System.out.println(all);
        System.out.println("");
        System.out.println(all2);
        System.out.println("");
        System.out.println(all3);
        System.out.println("");
        System.out.println(all4);
        
        

    }

    public static int save(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, endereco, rg, cpf, id_bairro, id_cidade, id_estado) "
                + "VALUES (?, ?, ?, ?,(SELECT id FROM bairros WHERE nome = ?), "
                + "(SELECT id FROM cidades WHERE nome = ?), "
                + "(SELECT id FROM estados WHERE nome = ?))";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getCpf());
            ps.setString(5, cliente.getBairro());
            ps.setString(6, cliente.getCidade());
            ps.setString(7, cliente.getEstado());
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

    public static List<Cliente> getAll2() {
        String sql = "SELECT c.id, c.nome, c.endereco, c.rg, c.cpf, b.nome AS bairro, ci.nome AS cidade, e.nome AS estado "
                + "FROM clientes c JOIN bairros b ON c.id_bairro = b.id "
                + "JOIN cidades ci ON c.id_cidade = ci.id "
                + "JOIN estados e ON c.id_estado = e.id ";
        try (Connection conn = ConectaBanco.getConexao();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            List<Cliente> lista = new ArrayList<>();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("rg"), rs.getString("cpf"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("estado"));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Cliente> getAll() {
        String sql = "SELECT c.id, c.nome, c.endereco, c.rg, c.cpf, b.nome AS bairro, ci.nome AS cidade, e.nome AS estado "
                + "FROM clientes c JOIN bairros b ON c.id_bairro = b.id "
                + "JOIN cidades ci ON c.id_cidade = ci.id "
                + "JOIN estados e ON c.id_estado = e.id ";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            List<Cliente> lista = new ArrayList<>();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("rg"), rs.getString("cpf"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("estado"));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, endereco = ?, rg = ?, cpf = ?, "
                + "id_bairro = (SELECT id FROM bairros WHERE nome = ?), "
                + "id_cidade = (SELECT id FROM cidades WHERE nome = ?), "
                + "id_estado = (SELECT id FROM estados WHERE nome = ?)"
                + "WHERE id = ?";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getCpf());
            ps.setString(5, cliente.getBairro());
            ps.setString(6, cliente.getCidade());
            ps.setString(7, cliente.getEstado());
            ps.setInt(8, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<String> telefones(Cliente cliente) {
        String sql = "SELECT numero FROM telefones JOIN telefone_cliente ON id = id_telefone WHERE id_cliente = ? ORDER BY numero";
        try (Connection conn = ConectaBanco.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cliente.getId());
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
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        
//        SwingUtilities.invokeLater(()-> {
////            Principal janela = new Principal();
//            CadastroEstado janela = new CadastroEstado();
//            janela.setVisible(true);
//        });
//    }

}
