package com.sprinApi.resources.exception;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 4691390105558720489L;

	public AuthorizationException(String msg) {
		super(msg);
	}

	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
