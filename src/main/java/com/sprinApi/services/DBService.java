package com.sprinApi.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sprinApi.domain.Categoria;
import com.sprinApi.domain.Cidade;
import com.sprinApi.domain.Cliente;
import com.sprinApi.domain.Endereco;
import com.sprinApi.domain.Estado;
import com.sprinApi.domain.EstadoPagamento;
import com.sprinApi.domain.ItemPedido;
import com.sprinApi.domain.Pagamento;
import com.sprinApi.domain.PagamentoComBoleto;
import com.sprinApi.domain.PagamentoComCartao;
import com.sprinApi.domain.Pedido;
import com.sprinApi.domain.Perfil;
import com.sprinApi.domain.Produto;
import com.sprinApi.domain.TipoCliente;
import com.sprinApi.repositories.CategoriaRepository;
import com.sprinApi.repositories.CidadeRepository;
import com.sprinApi.repositories.ClienteRepository;
import com.sprinApi.repositories.EnderecoRepository;
import com.sprinApi.repositories.EstadoRepository;
import com.sprinApi.repositories.ItemPedidoRepository;
import com.sprinApi.repositories.PagamentoRepository;
import com.sprinApi.repositories.PedidoRepository;
import com.sprinApi.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository PedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BCryptPasswordEncoder encodePass;

	public void instatiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.00));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.00));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80.00));
		Produto p4 = new Produto(null, "Mesa de escritório", new BigDecimal(300.00));
		Produto p5 = new Produto(null, "Toalha", new BigDecimal(50.00));
		Produto p6 = new Produto(null, "Colcha", new BigDecimal(200.00));
		Produto p7 = new Produto(null, "TV true color", new BigDecimal(1200.00));
		Produto p8 = new Produto(null, "Roçadeira", new BigDecimal(800.00));
		Produto p9 = new Produto(null, "Abajour", new BigDecimal(100.00));
		Produto p10 = new Produto(null, "Pendente", new BigDecimal(180.00));
		Produto p11 = new Produto(null, "Shampoo", new BigDecimal(90.00));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais", "MG");
		Estado est2 = new Estado(null, "São Pualo", "SP");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));

		Cliente cl1 = new Cliente(null, "Maria Silva", "trmoura@yahoo.com.br", "75487655766", TipoCliente.PESSOA_FISICA,
				encodePass.encode("123"));
		Cliente cl2 = new Cliente(null, "Tiago Moura", "tiaguitomoreno@gmail.com", "87289253272", TipoCliente.PESSOA_FISICA,
				encodePass.encode("123"));
		cl2.addPerfil(Perfil.ADM);

		cl1.getTelefones().addAll(Arrays.asList("32476645", "998767654"));
		cl2.getTelefones().addAll(Arrays.asList("34676985", "997657699"));

		Endereco e1 = new Endereco(null, "Rua da Mata", "2", "Bem ali", "Centro", "66777888", cl1, c1);
		Endereco e2 = new Endereco(null, "Rua do Céu", "7", "Bem ali", "Centro", "77777777", cl1, c2);
		Endereco e3 = new Endereco(null, "Rua da Mata", "7", "Bem Aculá", "Centro", "77777777", cl2, c2);

		cl1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cl2.getEnderecos().addAll(Arrays.asList(e3));

		clienteRepository.save(Arrays.asList(cl1, cl2));
		enderecoRepository.save(Arrays.asList(e1, e2, e3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		PedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, new BigDecimal(0.0), 1, new BigDecimal(2000.00));
		ItemPedido ip2 = new ItemPedido(ped1, p3, new BigDecimal(0.0), 2, new BigDecimal(80.00));
		ItemPedido ip3 = new ItemPedido(ped2, p2, new BigDecimal(0.0), 1, new BigDecimal(800.00));

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));

	}

}
