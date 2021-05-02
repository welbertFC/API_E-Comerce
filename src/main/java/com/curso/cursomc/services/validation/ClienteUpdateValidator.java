package com.curso.cursomc.services.validation;

import com.curso.cursomc.DTO.ClienteDTO;
import com.curso.cursomc.DTO.ClienteNewDTO;
import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.enums.TipoCliente;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.resources.exception.FieldMessage;
import com.curso.cursomc.services.validation.utils.Br;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    public ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate ann){

    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context){

        Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if(aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email já existe"));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage()).addConstraintViolation();
        }

        return list.isEmpty();
    }

}
