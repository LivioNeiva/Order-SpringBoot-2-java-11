package com.livioneiva.cursos.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tab_payment")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	a classe dependente é a classe Payment, pq a classe Order pode ter 0 Payment, o
	Order pode entrar no dba sem ter Payment. A classe Order é um classe independente
	e a classe Payment é uma classe dependente. Order pode existir sem Payment, mais nao 
	exite um Payment sem a Order	
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant moment;
	
	@JsonIgnore//lista os pagamento nas order
	@OneToOne
	@MapsId
	private Order order;
	
	public Payment() {
		
	}
	
	public Payment(Long id, Instant moment, Order order) {
		super();
		this.id=id;
		this.moment=moment;
		this.order=order;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
/*
 @MapsId
 Designa um atributo de relacionamento ManyToOne ou OneToOne que fornece o mapeamento para
  uma chave primária EmbeddedId, um atributo dentro de uma chave primária EmbeddedId ou 
  uma chave primária simples da entidade pai. O elemento de valor especifica o atributo 
  em uma chave composta à qual o atributo de relacionamento corresponde. Se a 
  chave primária da entidade for do mesmo tipo Java que a chave primária da entidade 
  referenciada pelo relacionamento, o atributo de valor não será especificado
*/