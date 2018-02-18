package com.sprinApi.resources.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4691390105558720489L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
