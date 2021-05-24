package com.curso.cursomc.resources;


import com.curso.cursomc.DTO.CategoriaSemProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<CategoriaSemProduto> listar() {
        List<CategoriaSemProduto> list = service.findAll();
        return list;

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaSemProduto categoriaSemProduto) {
        Categoria categoria = service.fromDTO(categoriaSemProduto);
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public  ResponseEntity<Categoria> update(@Valid @RequestBody CategoriaSemProduto categoriaSemProduto, @PathVariable Integer id){
        Categoria categoria = service.fromDTO(categoriaSemProduto);
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

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaSemProduto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome")String oderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {
        Page<Categoria> list = service.findPage(page,linesPerPage,oderBy,direction);
        Page<CategoriaSemProduto> listCategoriaSemProduto = list.map(categoria -> new CategoriaSemProduto(categoria));
        return ResponseEntity.ok().body(listCategoriaSemProduto);

    }

}

