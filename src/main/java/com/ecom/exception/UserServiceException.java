package com.ecom.exception;

import lombok.Data;

@Data
public class UserServiceException extends RuntimeException{
	private String errorCode;
	public UserServiceException(String message,String errorCode) {
		super(message);
		this.errorCode = errorCode;	
	}

}
