package com.springdatajparest.exampl.SpringBootDataJpaRest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler
	public String handledInvalidFiledException(InvalidFieldException e){
		return e.toString();
	}
}
