package com.sprinApi.services;

import org.springframework.mail.SimpleMailMessage;

import com.sprinApi.domain.Cliente;
import com.sprinApi.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmation(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
