package com.livioneiva.cursos.services.exceptions;

public class DatabaseException extends RuntimeException {

	private static final Long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg);
	}
	
}
/*
java.lang.RuntimeException - o compilador não obriga a tratar ou propagar

Delega a lógica do erro para a classe responsável por conhecer as regras que
podem ocasionar o erro

*/