package com.sprinApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinApi.domain.Cliente;
import com.sprinApi.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {

		Cliente Cliente = repo.findOne(id);

		if (Cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado para o  ID :" + id + " do Tipo: " + Cliente.class.getSimpleName());
		}

		return Cliente;
	}

}
