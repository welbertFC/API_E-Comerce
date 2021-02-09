package com.curso.cursomc.resources;


import com.curso.cursomc.domain.CategoriaSemProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Categoria obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoriaSemProduto> listar() {
        List<CategoriaSemProduto> obj = service.buscarTodos();
        return obj;

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}

