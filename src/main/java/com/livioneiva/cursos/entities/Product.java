package com.livioneiva.cursos.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tab_Product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String description;
	private Double price;
	private String imgUrl;

	/*
	para criar uma tabela com relacionamentos muitos para muitos entre a table product
	e Category, eu escolhi ciar a table "tb_product_category" na classe product.
	Para garantir q o mesmo produto nao vai se repetir, usaremos uma collections
	usaremos o Set. RELACIONAMENTO MUITOS PARA MUITOS. O Set é uma interface nao
	pode ser instanciado, por isso instanciamos HashSet<>().
	Dentro da category, eu tenho um conj. de product, e dentro do product eu
	tenho conj. Category
	@Transient //vai impedir q o jpa tente interpretar esse obj
	*/
	@ManyToMany // muitos para muitos,
	@JoinTable(name = "tb_product_category", // @JoinTable é o nome da tabela de associação no banco de dados
			joinColumns = @JoinColumn(name = "product_id"), // JoinColumn é nome do chave-estrangeira trferente a tabela product
			inverseJoinColumns = @JoinColumn(name = "category_id")) // vamos definir a chave estrangeira da outra
																	// entidade, chama-se Category
	private Set<Category> categories = new HashSet<>();// instanciamos para garantir q a coleção nao começe com null e
														// sim vazia
	
	/*
	 * precisamos pegar as Order no qual o product pertence. primeiro entramos no
	 * nos OrderItem q Product, e com metodo getOrders(), chegaremos as Ordes do product
	 * o obj items é uma collections q armazenará os OrderItem na qual o produto pertence
	 */
	@OneToMany(mappedBy = "id.product")//"id.product" = o id pertence a classe OrderItem, o nome product tem ser igual ao obj tipo Product está na classe composta OrddemItemPK
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() {

	}

	public Product(Long id, String nome, String description, Double price, String imgUrl) {
		this.id = id;
		this.nome = nome;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	/*
	 * eu vou varrer toda a collection de OrderItem q está associado ao produto e para 
	 * cada orderItem eu vou associar o obj Order q está associado ao obj OrderItem
	 */
	@JsonIgnore //nao será listado as Orders que pertence ao produto e sim o produto q pertence as Order
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();//criamos um Set para receber coletions Order
			for(OrderItem x : this.items) {//obj Order x recebe as Order está collection items
				set.add(x.getOrder());
			}
		return set;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
