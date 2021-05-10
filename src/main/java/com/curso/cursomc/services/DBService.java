package com.curso.cursomc.services;

import com.curso.cursomc.domain.*;
import com.curso.cursomc.domain.enums.EstadoPagamento;
import com.curso.cursomc.domain.enums.Perfil;
import com.curso.cursomc.domain.enums.TipoCliente;
import com.curso.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void instantiateTestDataBase() throws ParseException {

        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");
        Categoria cat3 = new Categoria(null, "Cama Mesa e Banho");
        Categoria cat4 = new Categoria(null, "Jardinagem");
        Categoria cat5 = new Categoria(null, "Decoração");
        Categoria cat6 = new Categoria(null, "Perfumaria");
        Categoria cat7 = new Categoria(null, "Eletronicos");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com.br", "12312312323", TipoCliente.PESSOAFISICA, passwordEncoder.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("3791125658", "37989898781"));

        Cliente cli2 = new Cliente(null, "Ana Da silva", "Ana@gmail.com.br", "12312312323", TipoCliente.PESSOAFISICA, passwordEncoder.encode("123"));
        cli2.getTelefones().addAll(Arrays.asList("37989898989", "37999999"));
        cli2.addPerfil(Perfil.ADMIN);

        Endereco e1 = new Endereco(null, "Rua das mangabeiras", "32", "Casa de dois andades", "Centro", "35519000", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Jardim", "379898989", cli1, c2);
        Endereco e3 = new Endereco(null, "Ana Matos", "105", "sala 800", "Jardim", "379898989", cli2, c2);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cli2.getEnderecos().addAll(Arrays.asList(e3, e2));


        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));


        ItemPedido ip1 = new ItemPedido(p1, ped1, 0.00, 1, BigDecimal.valueOf(2200.00));
        ItemPedido ip2 = new ItemPedido(p3,ped1,0.00, 2, BigDecimal.valueOf(80.00));
        ItemPedido ip3 = new ItemPedido(p2, ped2, 100.00,1, BigDecimal.valueOf(800.00));

        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3,cat4,cat5,cat6,cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
        pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

    }
}
