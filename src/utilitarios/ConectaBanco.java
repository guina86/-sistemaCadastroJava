/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.sql.*;

/**
 *
 * @author Leandro Guina
 */
public class ConectaBanco {

    private String driver = "com.mysql.jdbc.Drivers";
    private String caminho = "jdbc:mysql://localhost:3306/sistemavideoaula?useTimezone=true&serverTimezone=UTC"; //
    private String usuario = "root";
    private String senha = "";

    
    public static Connection getConexao(){
        String url = "jdbc:mysql://localhost:3306/sistemavideoaula?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void close(Connection connection) {
        try{
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void executaSql(String sql) {
        try (Connection conn = ConectaBanco.getConexao(); Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
