package com.curso.cursomc.services;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscar(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException ("Objeto não encontrado! id: " + id +", Tipo: " + Cliente.class.getName()));
    }
}
