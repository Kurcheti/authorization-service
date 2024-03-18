package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleProductServiceException(UserServiceException userServiceException){
		                     ErrorResponse errorResponse = ErrorResponse.builder()
		                    		                       .message(userServiceException.getMessage())
		                    		                       .errorCode(userServiceException.getErrorCode())
		                    		                       .build();
		    return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
