/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Leandro Guina
 */
public class Cliente extends Pessoa {
    private String rg;
    private String cpf;
       
    public Cliente(String nome, String endereco, String rg, String cpf, Bairro bairro) {
        this.nome = nome;
        this.endereco = endereco;
        this.rg = rg;
        this.cpf = cpf;
        this.bairro = bairro;
    }

    public Cliente(String nome, String endereco, String rg, String cpf, Bairro bairro, List<Telefone> telefones) {
        this.nome = nome;
        this.endereco = endereco;
        this.rg = rg;
        this.cpf = cpf;
        this.bairro = bairro;
        this.telefones = telefones;
    }

    public Cliente(Integer id, String nome, String endereco, String rg, String cpf, Bairro bairro, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.rg = rg;
        this.cpf = cpf;
        this.bairro = bairro;
        this.telefones = telefones;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return getNome();
    }

        
}
