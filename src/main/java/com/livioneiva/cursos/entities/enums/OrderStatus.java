package com.livioneiva.cursos.entities.enums;

public enum OrderStatus {
	
	/*
	aparti do momento q vamos colocando os enums, uma ordem vai ser criando para cada enum
	0,1,2,3... para boa pratica temos q ordenar manualmente nossos enums para q nao
	aconteça uma auto dependencia com as classes estao se relacionando caso precisamos por
	um novo enum, seguiremos a ordem pela enumeraçao.
	 */

	AGUARDANDO_PAGAMENTO(1),
	PAGO(2),
	ENVIADO(3),
	DELIVERID(4),
	CANCELADO(5);
	
	//varicavel representa o code enum
	private int code;
	
	//construtor privado
	private OrderStatus(int code) {
		this.code = code;
	}
	//retorna o code do enum
	public int getCode() {
		return code;
	}
	//reorna os codigos dos enum valido
	public static OrderStatus valueOf(int code) {
		//values() -> é um arrys q retorna todos os enums da classe
		for(OrderStatus value : OrderStatus.values()) {//os enums sao inserido no value
			if(value.getCode() == code) {// se value for igual ao int code passado pelo usuario
				return value; //retorne o valor do enum. se code for 1 retorna aguardando_pagamento
			}
		}//se nao, retorne a mensagem de erro
		throw new IllegalArgumentException("INVALID ORDER STATUS CODE");
	}
}

/*
	WAITING_PAGAMENT, PAGAMENTO_ESPERA
	PAID, PAGO
	SHIPPED, ENVIADO
	DELIVERID, 
	CANCELED, cancelado
*/