package med.voll.api.infra.exception;

import med.voll.api.domain.consulta.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadordeErros {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		var erro = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erro.stream().map(DadosErroValidacao::new).toList());
	}


	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity tratarErroValidacao(ValidacaoException ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	private record DadosErroValidacao(String campo, String mensagem) {
		
		DadosErroValidacao(FieldError erro){
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
