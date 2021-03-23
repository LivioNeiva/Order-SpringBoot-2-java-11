package com.livioneiva.cursos.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.livioneiva.cursos.entities.pk.OrderItemPK;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//id composto
	@EmbeddedId //chave composta através da anotação @Embeddable
	private OrderItemPK id = new OrderItemPK(); //uma dependencia com obj da classe auxiliar de chave primaria composta 
	//obs = se nao instanciar o obj id, o mesmo ficara null, e vai gerar um bug ao setar o id.setOrder() e id.setproduct()
	private Integer quant;
	private Double price;
	
	public OrderItem() {
		
	}
	//criamos dois objs tipo Order, Product
	public OrderItem(Order order, Product product, Integer quant, Double price) {
		super();
		id.setOrder(order); //com obj id, setamos as Order e prouct 
		id.setProduct(product);
		this.quant=quant;
		this.price=price;
	}
	//getOrder é uma dependencia da classe OrderItemPK q tem uma dependencia da classe Order
	@JsonIgnore //retira o loop feito pelo relacionamento entre as classes. leia a baixo a explicação
	public Order getOrder() {
		return id.getOrder();//retorna o order(pedido), q está na chave composta, @Jsonignore retira o loop
	}
	//setOrder add order da classe OrdemItemPK, é uma dependencia da classe Order
	public void setOrder(Order order) {
		id.setOrder(order);//seta uma Order(pedido) para chave composta id
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

/*
 //getOrder é uma dependencia da classe OrderItemPK q tem uma dependencia da classe Order
	@JsonIgnore //retira o loop feito pelo relacionamento entre as classes. leia a baixo a explicação
	public Order getOrder() {
		return id.getOrder();//retorna o order(pedido), q está na chave composta
	}
Explicando pq tivemos q colocar o @JsonIgnore no metodo get
o getOrder está chamando o pedido associado ao item de pedido, o pedido por sua vez
chamava o item de medido novamente fica no loop infinito

na classe Order temos um relacionamento entre Order e OrdemItem 1 p/ muitos
//"id.order" = na classe OrderItem temos o id, é o id composto, que está classe auxiliar(OrdemIemPK) se relacionando a classe Order, isso da um looop
@OneToMany(mappedBy = "id.order")
private Set<OrderItem> items = new HashSet<>();

na classe OrdemItem temos um getOrder que atraves do obj compposto id chama novamente
a order, isso forma um loop.
temos q colocar o @JsonIgnore no metodo getOrder da classe OrderItem
	
 */
