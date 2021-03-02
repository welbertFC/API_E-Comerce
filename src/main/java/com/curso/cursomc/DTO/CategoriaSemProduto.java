package com.curso.cursomc.DTO;


import com.curso.cursomc.domain.Categoria;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;


public class CategoriaSemProduto implements Serializable {

    private Integer id;
    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(min=5, max =80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriaSemProduto() {

    }

    public CategoriaSemProduto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaSemProduto(Categoria categoria) {
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

}
