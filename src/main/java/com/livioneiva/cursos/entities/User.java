package com.livioneiva.cursos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity; //javax.persistence -> é a especificação do jpa, temos q dar preferencia a especificação
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tab_user")
public class User implements Serializable {
	//Serializable, serve para ser os objetos sejam transformado em cadeia de bytes, para que o 
	//objeto trafeque na rede, para que o objeto possa ser gravado em arquivos...
	private static final long serialVersionUID = 1L;
	
	@Id //informamos o campo vai ser a chave primaria da tabela user
	@GeneratedValue(strategy = GenerationType.IDENTITY)//indetificando campo como auto-incremento
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@OneToMany(mappedBy = "client") // relacionamento muitos para 1
	private List<Order> order = new ArrayList<>();
	
	public User() {
		
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrder() {
		return order;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id= " + id + ", name= " + name + ", email= " + email + ", phone= " + phone + ", password=" + password
				+ "]";
	}

	
}
