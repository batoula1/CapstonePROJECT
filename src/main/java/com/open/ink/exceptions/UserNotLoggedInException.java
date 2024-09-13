package com.open.ink.exceptions;

public class UserNotLoggedInException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotLoggedInException() {
		super("User is Not Logged In");				
	}
	
}
