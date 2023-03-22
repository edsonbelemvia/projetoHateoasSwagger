package br.com.via.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7895693172216644992L;

	public ProductNotFoundException(Integer id) {
		super("Product Nao Encontrado :" + id);
	}
}
