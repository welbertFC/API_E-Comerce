package com.curso.cursomc.DTO;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.services.validation.ClienteUpdate;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ClienteUpdate
public class ClienteDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 5, max = 120, message = "Tamanho Invalido")
    private String nome;
    @Email(message = "Email Invalido")
    private String email;

    public ClienteDTO  (){

    }

    public ClienteDTO(Cliente cliente){
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
    }


}
