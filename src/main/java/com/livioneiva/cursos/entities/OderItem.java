package com.livioneiva.cursos.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.livioneiva.cursos.entities.pk.OrderItemPK;

@Entity
@Table(name = "tb_order_item")
public class OderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//id composto
	@EmbeddedId
	private OrderItemPK id; //uma dependencia com obj da classe auxiliar de chave primaria composta 
	
	private Integer quant;
	private Double price;
	
	public OderItem() {
		
	}
	//criamos dois objs tipo Order, Product
	public OderItem(Order order, Product product, Integer quant, Double price) {
		super();
		id.setOrder(order); //com obj id, setamos as Order e prouct 
		id.setProduct(product);
		this.quant=quant;
		this.price=price;
	}
	//getOrder é uma dependencia da classe OrderItemPK q tem uma dependencia da classe Order
	public Order getOrder() {
		return id.getOrder();
	}
	//setOrder add order da classe OrdemItemPK, é uma dependencia da classe Order
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
		}
	
	public Integer getQuant() {
		return quant;
	}
	public void setQuant(Integer quant) {
		this.quant = quant;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
		OderItem other = (OderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
