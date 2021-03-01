package com.curso.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.curso.cursomc.DTO.CategoriaSemProduto;
import com.curso.cursomc.services.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.repositories.CategoriaRepository;
import com.curso.cursomc.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public List<CategoriaSemProduto> findAll() {
        List<Categoria> categoriasList = repo.findAll();
        return categoriasList.stream().map(categoria -> new CategoriaSemProduto(categoria)).collect(Collectors.toList());
        //categoriasList.stream().map(CategoriaSemProduto::new).collect(Collectors.toList());
    }

    public Categoria insert(Categoria categoria){
        categoria.setId(null);
        return repo.save(categoria);
    }

    public Categoria update(Categoria categoria){
        find(categoria.getId());
        return repo.save(categoria);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir categoria com produtos");
        }

    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String oderBy, String direction){

        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), oderBy);
        return repo.findAll(pageRequest);
    }
}
