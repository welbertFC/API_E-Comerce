package com.curso.cursomc.services.validation;

import com.curso.cursomc.DTO.ClienteNewDTO;
import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.enums.TipoCliente;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.resources.exception.FieldMessage;
import com.curso.cursomc.services.validation.utils.Br;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    public ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann){

    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

      if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !Br.isValidCPF(objDto.getCpfOuCnpj())){
          list.add(new FieldMessage("CpfOuCnpj", "CPF invalido"));
      }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !Br.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("CpfOuCnpj", "CNPJ invalido"));
        }

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if(aux != null){
            list.add(new FieldMessage("email", "Email já existe"));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage()).addConstraintViolation();
        }

        return list.isEmpty();
    }

}
