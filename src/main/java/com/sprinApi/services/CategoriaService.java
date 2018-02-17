package com.sprinApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinApi.domain.Categoria;
import com.sprinApi.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {

		Categoria categoria = repo.findOne(id);

		if (categoria == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado para o  ID :" + id + " do Tipo: " + Categoria.class.getSimpleName());
		}

		return categoria;
	}

}
