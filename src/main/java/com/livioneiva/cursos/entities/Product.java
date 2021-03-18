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
import javax.persistence.Table;
import javax.persistence.Transient;

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

	// para garantir q o mesmo produto nao vai se repetir, usaremos uma collections
	// usaremos o Set. RELACIONAMENTO MUITOS PARA MUITOS. O Set é uma interface nao
	// pode ser instanciado, por isso instanciamos HashSet<>()
	// dentro da category, eu tenho um conj. de product, e dentro do product eu
	// tenho conj. Category
	// @Transient //vai impedir q o jpa tente interpretar esse obj
	@ManyToMany // muitos para muitos,
	@JoinTable(name = "tb_product_category", // @JoinTable é o nome da tabela de associação no banco de dados
			joinColumns = @JoinColumn(name = "product_id"), // JoinColumn é nome do campo chave-estrangeira do product
			inverseJoinColumns = @JoinColumn(name = "category_id")) // vamos definir a chave estrangeira da outra
																	// entidade, chama-se Category
	private Set<Category> categories = new HashSet<>();// instanciamos para arantir q a coleção nao começe com null e
														// sim vazia

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
