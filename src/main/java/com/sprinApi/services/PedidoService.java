package com.sprinApi.services;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinApi.domain.EstadoPagamento;
import com.sprinApi.domain.ItemPedido;
import com.sprinApi.domain.PagamentoComBoleto;
import com.sprinApi.domain.Pedido;
import com.sprinApi.repositories.ClienteRepository;
import com.sprinApi.repositories.ItemPedidoRepository;
import com.sprinApi.repositories.PagamentoRepository;
import com.sprinApi.repositories.PedidoRepository;
import com.sprinApi.repositories.ProdutoRepository;
import com.sprinApi.resources.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EmailService emailService;

	public Pedido find(Integer id) {

		Pedido categoria = repo.findOne(id);

		if (categoria == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado para o  ID :" + id + " do Tipo: " + Pedido.class.getSimpleName());
		}

		return categoria;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setDataPedido(new Date());
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			this.boletoService.preencherPagamentoComBoleto(pagto, obj.getDataPedido());
		}

		obj = repo.save(obj);
		this.pagamentoRepository.save(obj.getPagamento());

		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(BigDecimal.ZERO);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}

		this.itemPedidoRepository.save(obj.getItens());
		this.emailService.sendOrderConfirmation(obj);

		return obj;

	}

}
