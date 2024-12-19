package com.idat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.idat.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e){
		log.error("GlobalExceptionHandler::handleException::", e.getMessage());
		return CommonUtil.createErrorResponseMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		//return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e){
		log.error("GlobalExceptionHandler::handleNullPointerException::", e.getMessage());
		return CommonUtil.createErrorResponseMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		//		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFounfException.class)
	public ResponseEntity<?> handleResourceNotFounfException(Exception e){
		log.error("GlobalExceptionHandler::handleResourceNotFounfException::", e.getMessage());
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		return CommonUtil.createErrorResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException e){
//		return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		return CommonUtil.createErrorResponse(e.getErrors()	, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistDataException.class)
	public ResponseEntity<?> handleExistDataException(ExistDataException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//		return CommonUtil.createErrorResponse(e.getErrors()	, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		return CommonUtil.createErrorResponse(e.getErrors()	, HttpStatus.BAD_REQUEST);
	}
}
