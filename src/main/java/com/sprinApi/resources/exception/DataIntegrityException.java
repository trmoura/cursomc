package com.sprinApi.resources.exception;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 4691390105558720489L;

	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
