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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //gera uma entidade na tabela
@Table(name = "tab_user") //nome da tabela se chamara tab_user
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
	
	/*
	 exite um relacionamento entre a tabela order(pedido) e User(Cliente), 1 cliente pode 
	 ter muitos pedidos, mais 1 pedido so pertence a 1 cliente, esse mapeamento é opcinal,
	  caso queira acessar o obj do tipo User, e acessar os pedidos do cliente	 
	 */
	@JsonIgnore //retira o loop feito pelo relacionamento entre as classes,não será exibido os itens do relacionamento Order. Colocamos no relacionamento muitos
	@OneToMany(mappedBy = "client") // relacionamento muitos para 1, 1 cliente tem muitos pedidos, temos mapear o nome da chave estrangeira está tabela User,(mappedBy = "client"), client é nome da variavel q está na classe Order
	private List<Order> order = new ArrayList<>(); // instanciando a coleção new ArrayList<>()
	
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
	//um array de list so colocamos o get, pelo fato de nao trocarmos a lista em nenhum momento, e sim apagamos ou inserimos elementos na lista(coleção)
	//se é uma coleção, usamos somente o gets
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
