package com.curso.cursomc.DTO;

import com.curso.cursomc.services.validation.ClienteInsert;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ClienteInsert
public class ClienteNewDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 5, max = 120, message = "Tamanho Invalido")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Email(message = "Email Invalido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String cpfOuCnpj;

    private Integer tipo;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String senha;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String logradouro;
    private String numero;
    private String complemento;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String cep;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String telefone1;

    private String telefone2;
    private String telefone3;

    private Integer cidadeId;

    public ClienteNewDTO(){

    }
}
