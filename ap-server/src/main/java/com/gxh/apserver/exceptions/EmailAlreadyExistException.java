package com.gxh.apserver.exceptions;

public class EmailAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistException () {
		super();
	}
	
	public EmailAlreadyExistException(String message){
		super(message);
	}

	public EmailAlreadyExistException(Throwable e){
			super(e);
	}
	
}
