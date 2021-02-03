package com.curso.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.curso.cursomc.domain.CategoriaSemProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.repositories.CategoriaRepository;
import com.curso.cursomc.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public List<CategoriaSemProduto> buscarTodos() {
        List<Categoria> categoriasList = repo.findAll();
        return categoriasList.stream().map(CategoriaSemProduto::new).collect(Collectors.toList());
    }

}
