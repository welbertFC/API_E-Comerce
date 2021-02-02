package com.curso.cursomc.domain;


import java.io.Serializable;
import java.util.Objects;


public class CategoriaSemProduto implements Serializable {

    private Integer id;
    private String nome;

    public CategoriaSemProduto(){

    }

    public CategoriaSemProduto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaSemProduto(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaSemProduto that = (CategoriaSemProduto) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
