package com.sprinApi.services;

import org.springframework.mail.SimpleMailMessage;

import com.sprinApi.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmation(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
