package com.sprinApi.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sprinApi.domain.Cliente;
import com.sprinApi.repositories.ClienteRepository;
import com.sprinApi.resources.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Autowired
	private EmailService emailservice;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(passEncoder.encode(newPass));

		this.clienteRepository.save(cliente);
		this.emailservice.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);

		if (opt == 0) { // gera digito baseado na tabela unicode onde ZERO = 48 e NOVE = 57
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char) (rand.nextInt(26) + 65); // gera letra Maiúscula baseado na tabela unicode onde A = 65 e são
													// 26 letras possiveis
		} else {
			return (char) (rand.nextInt(26) + 97); // gera letra Minúsculas baseado na tabela unicode onde a = 97 e são
			// 26 letras possiveis
		}

	}

}
