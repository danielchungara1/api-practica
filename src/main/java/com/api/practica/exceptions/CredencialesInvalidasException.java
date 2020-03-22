package com.api.practica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class CredencialesInvalidasException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CredencialesInvalidasException() {
        super();
    }
    public CredencialesInvalidasException(String message, Throwable cause) {
        super(message, cause);
    }
    public CredencialesInvalidasException(String message) {
        super(message);
    }
    public CredencialesInvalidasException(Throwable cause) {
        super(cause);
    }
		
}
