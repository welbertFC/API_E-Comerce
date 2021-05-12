package com.curso.cursomc.resources;


import com.curso.cursomc.DTO.ClienteDTO;
import com.curso.cursomc.DTO.ClienteNewDTO;
import com.curso.cursomc.DTO.ClienteSemEndereco;
import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {

        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);


    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ClienteSemEndereco> listar() {

        List<ClienteSemEndereco> obj = service.buscarTodos();
        return obj;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO obtDTO) {
        Cliente obj = service.fromDTO(obtDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
        Cliente cliente = service.fromDTO(clienteDTO);
        cliente.setId(id);
        cliente = service.update(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestBody Cliente cliente, @PathVariable Integer id) {
        cliente.setId(id);
        service.delete(id);
        String msg = "O cliente " + cliente.getNome() + " deletada com sucesso";
        return ResponseEntity.ok().body(msg);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String oderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> list = service.findPage(page, linesPerPage, oderBy, direction);
        Page<ClienteDTO> listClienteDTO = list.map(cliente -> new ClienteDTO(cliente));
        return ResponseEntity.ok().body(listClienteDTO);

    }

    @RequestMapping(value = "/{id}/picture", method = RequestMethod.POST)
    public ResponseEntity<Void> uploadProfilePicture(@PathVariable Integer id, @RequestParam(name = "file") MultipartFile multipartFile) {
        URI uri = service.uploadProfilePicture(id, multipartFile);
        return ResponseEntity.created(uri).build();
    }


}

