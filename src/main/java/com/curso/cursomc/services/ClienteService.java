package com.curso.cursomc.services;

import com.curso.cursomc.DTO.ClienteDTO;
import com.curso.cursomc.DTO.ClienteNewDTO;
import com.curso.cursomc.DTO.ClienteSemEndereco;
import com.curso.cursomc.domain.Cidade;
import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.Endereco;
import com.curso.cursomc.domain.enums.TipoCliente;
import com.curso.cursomc.repositories.CidadeRepository;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.repositories.EnderecoRepository;
import com.curso.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Cliente find(Integer id) {
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

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = repo.save(cliente);
        cliente.getEnderecos().forEach(endereco ->
        {
         Optional<Cidade> cidade  = cidadeRepository.findById(endereco.getCidade().getId());
         if(cidade.isPresent()){
             endereco.setCidade(cidade.get());
         }
        });
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        Cliente newCliente = find(cliente.getId());
        updateData(newCliente, cliente);
        return repo.save(newCliente);
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possivel excluir cliente");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String oderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), oderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDTO) {
        Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()),passwordEncoder.encode(objDTO.getSenha()) );
        Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDTO.getTelefone1());
        if (objDTO.getTelefone2() != null) {
            cli.getTelefones().add(objDTO.getTelefone2());
        }
        if (objDTO.getTelefone3() != null) {
            cli.getTelefones().add(objDTO.getTelefone3());
        }

        return cli;
    }

}
