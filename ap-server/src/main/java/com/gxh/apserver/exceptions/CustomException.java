package com.gxh.apserver.exceptions;

public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6083911061066179195L;

	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(Throwable e) {
		super(e);
	}
	
}
