package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Problema.Campo> campos = new ArrayList<>();
		for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
			//erro.getDefaultMessage()
			String message = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			campos.add(new Problema.Campo(((FieldError) erro).getField(),message));
		}
		
		Problema problema = criarObjetoProblema("Um ou mais campos estão inválido. Faça o preenchimento correto e tente novamente", status);
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex,problema , headers, status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> hangleEntidadeNaoEncontrada(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problema problema = criarObjetoProblema(ex.getMessage(), status);
		return handleExceptionInternal(ex,problema , new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> hangleNegocio(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problema problema = criarObjetoProblema(ex.getMessage(), status);
		return handleExceptionInternal(ex,problema , new HttpHeaders(), status, request);
	}

	private Problema criarObjetoProblema(String titulo, HttpStatus status) {
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDatahora(LocalDateTime.now());
		problema.setTitulo(titulo);
		return problema;
	}

}
