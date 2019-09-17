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
public class Cliente {
    private Integer id;
    private String nome;
    private String endereco;
    private String rg;
    private String cpf;
    private String bairro;
    private String cidade;
    private String Estado;
    
    public Cliente() {
        
    }

    public Cliente(Integer id, String nome, String endereco, String rg, String cpf, String bairro, String cidade, String Estado) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.rg = rg;
        this.cpf = cpf;
        this.bairro = bairro;
        this.cidade = cidade;
        this.Estado = Estado;
    }
   
    public Cliente(String nome, String endereco, String rg, String cpf, String bairro, String cidade, String Estado) {
        this.nome = nome;
        this.endereco = endereco;
        this.rg = rg;
        this.cpf = cpf;
        this.bairro = bairro;
        this.cidade = cidade;
        this.Estado = Estado;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", rg=" + rg + ", cpf=" + cpf + ", bairro=" + bairro + ", cidade=" + cidade + ", Estado=" + Estado + '}';
    }

        
}
