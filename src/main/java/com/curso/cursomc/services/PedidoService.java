package com.curso.cursomc.services;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.Pedido;
import com.curso.cursomc.repositories.PedidoRepository;
import com.curso.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido buscar(Integer id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

}
