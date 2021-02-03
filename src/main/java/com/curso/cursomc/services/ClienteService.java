package com.curso.cursomc.services;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.ClienteSemEndereco;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscar(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<ClienteSemEndereco> buscarTodos() {
        List<Cliente> clienteList = repo.findAll();

        List<ClienteSemEndereco> clienteSemEnderecoList = new ArrayList<>();

        clienteList.forEach(cliente -> {

            ClienteSemEndereco clienteSemEndereco = new ClienteSemEndereco();
            clienteSemEndereco.setId(cliente.getId());
            clienteSemEndereco.setNome(cliente.getNome());
            clienteSemEndereco.setEmail(cliente.getEmail());
            clienteSemEndereco.setCpfOuCnpj(cliente.getCpfOuCnpj());
            clienteSemEndereco.setTipoCliente(cliente.getTipoCliente().getCodigo());
            clienteSemEndereco.setTelefones(cliente.getTelefones());

            clienteSemEnderecoList.add(clienteSemEndereco);

        });

        return clienteSemEnderecoList;
    }
}
