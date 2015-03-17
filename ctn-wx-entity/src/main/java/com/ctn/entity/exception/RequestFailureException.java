package com.ctn.entity.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

public class RequestFailureException extends AuthenticationServiceException{
	private static final long serialVersionUID = -4151691300664588624L;

	public RequestFailureException(String message){
		super(message);
	}
}
