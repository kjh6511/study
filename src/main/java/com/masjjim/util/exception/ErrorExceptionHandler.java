package com.masjjim.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler{

	//접근거부처리
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex,
															 WebRequest request){

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setMessage("Access denied");
		errorInfo.setStatus(HttpStatus.FORBIDDEN.value());
		errorInfo.setError(HttpStatus.FORBIDDEN.name());
		errorInfo.addDetailInfo("AccessDeniedException", ex.getMessage());

		log.info("handleAccessDeniedException"+ ex.toString());
		System.out.println("======== ERROR : "+ ex.toString());
		return super.handleExceptionInternal(ex, errorInfo, null, HttpStatus.FORBIDDEN, request);

	}

	//시스템예외처리
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request){

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setMessage("System error is occurred");
		errorInfo.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setError(HttpStatus.INTERNAL_SERVER_ERROR.name());
		errorInfo.addDetailInfo(ex.getCause().getMessage(), ex.toString());
		System.out.println("========== ERROR : "+ ex.toString());
		return super.handleExceptionInternal(ex, errorInfo, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
															 HttpHeaders headers, HttpStatus status, WebRequest request){

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setMessage("System error is occurred");
		errorInfo.setStatus(status.value());
		errorInfo.setError(status.name());
		errorInfo.addDetailInfo(ex.getCause().getMessage(), ex.toString());

		log.info("ERROR handleExceptionInternal : "+ ex.toString());
		System.out.println("======== ERROR : "+ ex.toString());
		return super.handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorInfo errorInfo = new ErrorInfo();
		StringBuffer sbMessage = new StringBuffer();

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		for(FieldError fieldError : fieldErrors) {
			String field = fieldError.getField();
			String defaultMessage = fieldError.getDefaultMessage();

			sbMessage.append(field + " : " + defaultMessage);
			sbMessage.append(" \r\n ");
		}

		errorInfo.setMessage(sbMessage.toString());
		errorInfo.setStatus(status.value());
		errorInfo.setError(status.name());

		log.info("handleMethodArgumentNotValid :" + sbMessage.toString());
		System.out.println("========== ERROR : "+  sbMessage.toString());
		return super.handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setMessage(ex.toString());
		errorInfo.setStatus(status.value());
		errorInfo.setError(status.name());

		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();

		for(ObjectError globalError : globalErrors) {
			errorInfo.addDetailInfo(globalError.getObjectName(), globalError.getDefaultMessage());
		}

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		for(FieldError fieldError : fieldErrors) {
			errorInfo.addDetailInfo(fieldError.getField(), fieldError.getDefaultMessage());
		}

		log.info("handleBindException" + ex.toString());
		System.out.println("========== ERROR : " + errorInfo);
		return super.handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorInfo errorInfo = new ErrorInfo();

		errorInfo.setStatus(status.value());
		errorInfo.setError(status.name());
		errorInfo.setMessage(ex.toString());

		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		if (!CollectionUtils.isEmpty(supportedMethods)) {
			headers.setAllow(supportedMethods);
		}
		errorInfo.addDetailInfo(supportedMethods.toString(), ex.getMessage());

		log.info("handleHttpRequestMethodNotSupported" + ex.toString());
		System.out.println("========== ERROR : " + ex.toString());
		return super.handleExceptionInternal(ex, errorInfo, headers, status, request);
	}
	
}
