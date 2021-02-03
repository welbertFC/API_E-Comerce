package com.curso.cursomc.resources;


import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.ClienteSemEndereco;
import com.curso.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Cliente obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);


    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ClienteSemEndereco> listar() {

        List<ClienteSemEndereco> obj = service.buscarTodos();
        return obj;
    }

}

