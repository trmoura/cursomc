package com.sprinApi;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sprinApi.domain.Categoria;
import com.sprinApi.domain.Cidade;
import com.sprinApi.domain.Cliente;
import com.sprinApi.domain.Endereco;
import com.sprinApi.domain.Estado;
import com.sprinApi.domain.EstadoPagamento;
import com.sprinApi.domain.Pagamento;
import com.sprinApi.domain.PagamentoComBoleto;
import com.sprinApi.domain.PagamentoComCartao;
import com.sprinApi.domain.Pedido;
import com.sprinApi.domain.Produto;
import com.sprinApi.domain.TipoCliente;
import com.sprinApi.repositories.CategoriaRepository;
import com.sprinApi.repositories.CidadeRepository;
import com.sprinApi.repositories.ClienteRepository;
import com.sprinApi.repositories.EnderecoRepository;
import com.sprinApi.repositories.EstadoRepository;
import com.sprinApi.repositories.PagamentoRepository;
import com.sprinApi.repositories.PedidoRepository;
import com.sprinApi.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Infomática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.00));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.00));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80.00));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais", "MG");
		Estado est2 = new Estado(null, "São Pualo", "SP");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));

		Cliente cl1 = new Cliente(null, "Maria SIlva", "maria@gmail.com", "89787654532", TipoCliente.PESSOA_FISICA);

		cl1.getTelefones().addAll(Arrays.asList("32476645", "998767654"));

		Endereco e1 = new Endereco(null, "Rua da Mata", "2", "Bem ali", "Centro", "66777888", cl1, c1);
		Endereco e2 = new Endereco(null, "Rua do Céu", "7", "Bem ali", "Centro", "77777777", cl1, c2);

		cl1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.save(cl1);
		enderecoRepository.save(Arrays.asList(e1, e2));

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

	}
}
