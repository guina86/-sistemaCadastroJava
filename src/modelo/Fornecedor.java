/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author Leandro Guina
 */
public class Fornecedor {

    private Integer id;
    private String nome;
    private String cnpj;
    private String endereco;
    private Bairro bairro;
    private List<Telefone> telefones;

    public Fornecedor() {
    }

    public Fornecedor(String nome, String cnpj, String endereco, Bairro bairro) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.bairro = bairro;
    }

    public Fornecedor(String nome, String cnpj, String endereco, Bairro bairro, List<Telefone> telefones) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.bairro = bairro;
        this.telefones = telefones;
    }
  
    public Fornecedor(Integer id, String nome, String cnpj, String endereco, Bairro bairro, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.bairro = bairro;
        this.telefones = telefones;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", endereco=" + endereco + ", bairro=" + bairro + ", telefones=" + telefones + '}';
    }

    

  
    
}
