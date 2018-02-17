package com.sprinApi;

import java.math.BigDecimal;
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
import com.sprinApi.domain.Produto;
import com.sprinApi.domain.TipoCliente;
import com.sprinApi.repositories.CategoriaRepository;
import com.sprinApi.repositories.CidadeRepository;
import com.sprinApi.repositories.ClienteRepository;
import com.sprinApi.repositories.EnderecoRepository;
import com.sprinApi.repositories.EstadoRepository;
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

	}
}
