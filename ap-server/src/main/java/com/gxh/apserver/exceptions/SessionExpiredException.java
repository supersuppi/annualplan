package com.gxh.apserver.exceptions;

public class SessionExpiredException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2767645863459351861L;

	public SessionExpiredException(){
		super();
	}
	
	public SessionExpiredException(String message){
		super(message);
	}
	
	public SessionExpiredException(Throwable e){
		super(e);
	}
}
