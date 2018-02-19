package com.sprinApi.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.sprinApi.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto p, Date dataPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		p.setDataVencimento(cal.getTime());
	}

}
