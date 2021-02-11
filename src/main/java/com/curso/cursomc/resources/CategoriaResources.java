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
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {

        Categoria obj = service.find(id);
        return ResponseEntity.ok().body(obj);

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoriaSemProduto> listar() {
        List<CategoriaSemProduto> obj = service.findAll();
        return obj;

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public  ResponseEntity<Categoria> update(@RequestBody Categoria categoria, @PathVariable Integer id){
        categoria.setId(id);
        categoria = service.update(categoria);
        return ResponseEntity.ok().body(categoria);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestBody Categoria categoria, @PathVariable Integer id){
        categoria.setId(id);
        service.delete(id);
        String msg = "A categoria " + categoria.getNome() + " deletada com sucesso";
        return ResponseEntity.ok().body(msg);

    }

}

