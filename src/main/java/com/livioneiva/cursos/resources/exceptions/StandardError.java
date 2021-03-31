package com.livioneiva.cursos.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * para garantir q meu Instant seja mostrado la no json no formato de string no
	 * iso 8601 teremos q acrscentar uma annotation abaixo para formatar json
	 */
 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timeTemp;
	private Integer status;
	private String errors;
	private String messages;
	private String path;
	
	public StandardError() {
	}

	public StandardError(Instant timeTemp, Integer status, String errors, String messages, String path) {
		this.timeTemp = timeTemp;
		this.status = status;
		this.errors = errors;
		this.messages = messages;
		this.path = path;
	}

	public Instant getTimeTemp() {
		return timeTemp;
	}

	public void setTimeTemp(Instant timeTemp) {
		this.timeTemp = timeTemp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
/*
 A MENSSAGEM DE ERRO QUANDO VC ERRA A INFORMAÇÃO SOLICITADA. EX. LOCALIZAR UM ID NAO EXISTE
 NA CLASSE ACIMA VAMOS MUDAR ESSA MSG.
{
    "timestamp": "2021-03-30T07:33:35.550+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "",
    "path": "/users/10"
}
*/
