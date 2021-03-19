package com.livioneiva.cursos.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.livioneiva.cursos.entities.Order;
import com.livioneiva.cursos.entities.Product;

/*
 * http://www.universidadejava.com.br/materiais/jpa-chave-primaria-composta/
é uma classe auxiliar de chave primaria composta
No JPA quando precisamos definir uma chave composta precisamos criar uma classe separada 
apenas com os atributos que fazem parte da chave composta e precisamos utilizar a 
anotaçãojavax.persistence.Embeddable.
 */

@Embeddable //informa que está classe será adicionado em outra entidade.
public class OrderItemPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne //relacionamento muitos para um
	@JoinColumn(name = "order_id") //o nome da chave estrangeira na tabela
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/*
	 * o hashCode e equals = usaremos os dois objs order e product para fazer a compraração
	 * e por em ordem crescente.	 
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}

/*
 http://www.universidadejava.com.br/materiais/jpa-chave-primaria-composta/
 
annotations para classes auxiliar de chave primaria composta

 - JPA 2.0 - Especificar chave primaria composta

A chave composta define que vários atributos serão utilizados para definir a chave de uma entidade, com isso, acabamos tendo uma restrição onde os atributos da chave composta não podem ser repetidos.

No JPA quando precisamos definir uma chave composta precisamos criar uma classe separada apenas com os atributos que fazem parte da chave composta e precisamos utilizar a anotação:

javax.persistence.Embeddable.
@Embeddable

*/
