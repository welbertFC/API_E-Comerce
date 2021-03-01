package com.curso.cursomc.DTO;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.enums.TipoCliente;

import java.io.Serializable;
import java.util.*;


public class ClienteSemEndereco implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente;

    private Set<String> telefones = new HashSet<>();

    public ClienteSemEndereco() {

    }

    public ClienteSemEndereco(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpfOuCnpj = cliente.getCpfOuCnpj();
        this.tipoCliente = cliente.getTipoCliente().getCodigo();
        this.telefones = cliente.getTelefones();
    }

    public ClienteSemEndereco(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = tipoCliente.getCodigo();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteSemEndereco that = (ClienteSemEndereco) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(cpfOuCnpj, that.cpfOuCnpj) && Objects.equals(tipoCliente, that.tipoCliente) && Objects.equals(telefones, that.telefones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, cpfOuCnpj, tipoCliente, telefones);
    }
}
