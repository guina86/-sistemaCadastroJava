/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Leandro Guina
 */
public class Telefone {
    private Integer id;
    private String numero;

    public Telefone() {
    }

    public Telefone(String numero) {
        this.numero = numero;
    }

    public Telefone(Integer id, String numero) {
        this.id = id;
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return getNumero();
    }
    
    
}
