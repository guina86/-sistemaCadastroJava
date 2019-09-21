/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import db.FornecedorDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
public class Testes {

    public static void main(String[] args) {

        Cidade cidade = new Cidade("Curitiba", new Estado(8, "Parana", "PR"));
        System.out.println(save(cidade));

    }

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
}
