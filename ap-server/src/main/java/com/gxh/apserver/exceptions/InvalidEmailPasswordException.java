package com.gxh.apserver.exceptions;

public class InvalidEmailPasswordException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -897667343818192794L;

	public InvalidEmailPasswordException() {
		super();
	}
	
	public InvalidEmailPasswordException(String message) {
		super(message);
	}
	
	public InvalidEmailPasswordException(Throwable e) {
		super(e);
	}
}
