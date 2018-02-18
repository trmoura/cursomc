package com.sprinApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinApi.domain.Pedido;
import com.sprinApi.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {

		Pedido categoria = repo.findOne(id);

		if (categoria == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado para o  ID :" + id + " do Tipo: " + Pedido.class.getSimpleName());
		}

		return categoria;
	}

}
