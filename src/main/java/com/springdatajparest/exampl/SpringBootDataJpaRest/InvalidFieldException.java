package com.springdatajparest.exampl.SpringBootDataJpaRest;

public class InvalidFieldException extends RuntimeException{
	
	public static final long  serialVersionUID=1L;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public InvalidFieldException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String toString() {
		return "InvalidFieldException [message=" + message + "]";
	}
	

}
