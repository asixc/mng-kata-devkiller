package com.mango.customer.handlerexception;

import com.mango.customer.exceptions.MaxNumberSlogans;
import com.mango.customer.exceptions.SloganNotFoundException;
import com.mango.customer.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class SloganHandlerException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SloganNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(
		SloganNotFoundException ex, HandlerMethod handlerMethod, HttpServletRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("method", request.getMethod());
		body.put("message", ex.getMessage());
		body.put("path", handlerMethod.getMethod().getName());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MaxNumberSlogans.class)
	public ResponseEntity<Object> handleUserNotFoundException(
		MaxNumberSlogans ex, HandlerMethod handlerMethod, HttpServletRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("method", request.getMethod());
		body.put("message", ex.getMessage());
		body.put("path", handlerMethod.getMethod().getName());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
