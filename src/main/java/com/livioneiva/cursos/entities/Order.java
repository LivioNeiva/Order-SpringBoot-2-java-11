package com.livioneiva.cursos.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.livioneiva.cursos.entities.enums.OrderStatus;

//clsse order é a classe pedido
//padrão iso 8601 é padrão para representar uma data em forma de texto

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

	Integer orderStatus;//tipo inteiro
	
	// exite um relacionamento entre a tabela order e tabela User, 1 cliente pode
	// ter muitos pedidos, mais o pedido so pertence a 1 cliente
	@ManyToOne // relacionamento muitos para 1, muitos Order para 1 user
	@JoinColumn(name = "client_id") // nome da chave estrangeira vai ficar na tabela order
	private User client;

	/*
	associação com a classe OrderItem, motivo -> na classe Order(pedido) onde fica os pedido,
	nos temos uma associação com varios itens, dentro da classe Order(pedido), eu quero
	uma operação getItem para retornar os OrderItems associados ao Order(pedido)
	 */
	/*
	haverá um bug se nao colocarmos o @JsonIgnore no metodo getOrder() da classe OrdemItem
	"id.order" = na classe OrderItem temos o id, é o id composto, que está classe 
	auxiliar(OrdemIemPK) se relacionando a classe Order, isso da um looop
	*/
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	public Order() {

	}
	//o id estácom auto-incremento da tabela, a data é instanciada no momento faz o pedido, o orderStatus é a forma esta o pagamento, e user é cliente fez o pedido
	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);//setorderStatus() seta um Integer, quando eu informo o inteiro para obj orderStatus é do tipo ENUM, o mesmo retorna o ENUM relacionado ao numero foi passado
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
	public OrderStatus getOrderStatus() {//o orderStatus está no argumento é um valor inteiro
		return OrderStatus.valueOf(orderStatus);//retorna o enum equivalente ao codigo passado variavel iIneger orderStatus
		//o metodo valueOf foi criado na classe OrderStatus para testar se valor code é valido e retornar o ENUM equivalente ao inteiro está no argumento
	}

	//no argumento eu tenho um obj tipo OrderStatus, o inteiro orderStatus vai receber o codigo
	//equivalente ao OrderStatus do ENUM
	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();//getCode() retorna um inteiro equivalente ao ENUM foi passado no argumento
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
	
	public Set<OrderItem> getItem(){
		return items;
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
