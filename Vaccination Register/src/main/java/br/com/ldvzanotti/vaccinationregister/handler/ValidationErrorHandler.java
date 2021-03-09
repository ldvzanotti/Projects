package br.com.ldvzanotti.vaccinationregister.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorDto> handle(MethodArgumentNotValidException exception) {
		List<ErrorDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorDto errorDto = new ErrorDto(e.getField(), message);
			dto.add(errorDto);
		});
		return dto;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDto> handle(HttpMessageNotReadableException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).
				body(new ErrorDto(exception.getMostSpecificCause().getLocalizedMessage(),
						"Invalid format. Date format must be 'yyyy-MM-dd'."));
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDto> handle(ConstraintViolationException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).
				body(new ErrorDto(exception.getLocalizedMessage(),
				"Invalid field data."));
	}
}
