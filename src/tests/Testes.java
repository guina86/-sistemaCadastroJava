/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import db.ProdutoDao;
import java.util.List;
import modelo.Produto;

/**
 *
 * @author Leandro Guina
 */
public class Testes {

    public static void main(String[] args) {

        ProdutoDao dao = new ProdutoDao();
        Produto find = dao.find("8");
        System.out.println(find);
        
    }

    
}
