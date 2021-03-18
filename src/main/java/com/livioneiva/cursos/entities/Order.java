package com.livioneiva.cursos.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.livioneiva.cursos.entities.enums.OrderStatus;

//clsse order é a classe pedido
//padrão iso 8601 é padrão para representar data emforma de texto

@Entity
@Table(name = "tab_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento sera feito pelo banco de dados
	private Long id;

	/*
	 * para garantir q meu Instant seja mostrado la no json no formato de string no
	 * iso 8601 teremos q acrscentar uma annotation abaixo para formatar json
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment; // Instant representa um instante q o pedido foi feito pelo client

	Integer orderStatus;
	
	// exite um relacionamento entre a tabela order e tabela User, 1 cliente pode
	// ter muitos pedidos, mais o pedido so pertence a 1 cliente
	@ManyToOne // relacionamento muitos para 1
	@JoinColumn(name = "client_id") // nome da chave estrangeira vai ficar na tabela order
	private User client;

	public Order() {

	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	//retorna code do OrderStatus 
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);//retorna o enum equivalente ao codigo passado variavel iIneger orderStatus
		//o metodo valueOf foi criado na classe OrderStatus para testar se valor code é valido
	}

	//
	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();//getCode() retorna um inteiro do tipo int
		}
		
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
