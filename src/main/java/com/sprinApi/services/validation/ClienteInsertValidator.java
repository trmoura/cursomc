package com.sprinApi.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sprinApi.domain.TipoCliente;
import com.sprinApi.dto.ClienteNewDTO;
import com.sprinApi.resources.exception.FieldMessage;
import com.sprinApi.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo())
				&& !BR.isValidCPF(objDto.getDocumentoFiscal())) {
			list.add(new FieldMessage("documentoFiscal", "CPF Inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo())
				&& !BR.isValidCNPJ(objDto.getDocumentoFiscal())) {
			list.add(new FieldMessage("documentoFiscal", "CNPJ Inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}