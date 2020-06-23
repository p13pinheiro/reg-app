package com.app.reg.springbootregapp.config.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.reg.springbootregapp.config.error.dto.ErrorFormularioDTO;
import com.app.reg.springbootregapp.config.error.dto.ErrorUsuarioDTO;
import com.app.reg.springbootregapp.config.error.exception.ParticipanteEventoException;
import com.mongodb.MongoWriteException;

@RestControllerAdvice
public class BeanValidationHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorFormularioDTO> handleFormulario(MethodArgumentNotValidException exception){
		List<ErrorFormularioDTO> erros = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorFormularioDTO dto = new ErrorFormularioDTO(e.getField(), mensagem);
			
			erros.add(dto);
		});
		
		return erros;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ParticipanteEventoException.class)
	public ErrorUsuarioDTO handleParticipanteNaoEncontrado(ParticipanteEventoException exception) {
		String mensagem = exception.getMessage();

		return new ErrorUsuarioDTO(mensagem);
	}
	
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(MongoWriteException.class)
	public ErrorUsuarioDTO hadleParticipanteJaAdicionadoNoEvento(MongoWriteException exception) {
		
		return new ErrorUsuarioDTO("Usuário já cadastrado no sistema. Esqueceu a senha?");
	}
}
