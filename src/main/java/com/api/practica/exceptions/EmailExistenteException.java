package com.api.practica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailExistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmailExistenteException() {
        super();
    }
    public EmailExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmailExistenteException(String message) {
        super(message);
    }
    public EmailExistenteException(Throwable cause) {
        super(cause);
    }

}
