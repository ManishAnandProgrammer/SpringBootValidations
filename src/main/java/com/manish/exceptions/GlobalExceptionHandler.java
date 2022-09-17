package com.manish.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	public static final String CHECK_ERROR_FIELD_MESSAGE = "Validation error. Check 'errors' field for details.";

	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	protected ResponseEntity<?> handleMethodArgumentNotValid(BindException ex,
															 WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(
				new Date(), UNPROCESSABLE_ENTITY.value(),
				CHECK_ERROR_FIELD_MESSAGE, request.getDescription(false)
		);

		BindingResult bindingResult = ex.getBindingResult();
		bindingResult.getFieldErrors().forEach(errorResponse::addValidationError);

		LOGGER.error("BindException :: ", ex);
		return ResponseEntity.unprocessableEntity().body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
		ErrorResponse errorResponse =
				new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
						exception.getMessage(), request.getDescription(false));
		LOGGER.error("Internal System Exception ::", exception);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleInvalidFormatException(HttpMessageNotReadableException exception,
														  WebRequest request) {
		LOGGER.error("Exception In Binding Result ", exception);

		ErrorResponse errorResponse =
					new ErrorResponse(new Date(),
						UNPROCESSABLE_ENTITY.value(),
						exception.getMessage(),
						request.getDescription(false)
				);
		return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(errorResponse);
	}
}