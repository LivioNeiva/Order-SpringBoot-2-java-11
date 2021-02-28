package com.livioneiva.cursos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livioneiva.cursos.entities.User;
import com.livioneiva.cursos.services.UserServices;

//camada de recursos - onde fica os controladores rest, os controladores rest, dependem da camada de serviços

@RestController // para informarmos q essa classe seja recurso web implementado por um controlador rest
@RequestMapping(value = "/users") // nome para recurso, acesso ao recurso
public class UserResource {
	
	@Autowired //dependencia
	UserServices services;

	/*
	 *  metodo para acessar usuarios da classe User
	 *  ResponseEntity - retorna resposta de requisiçoes web, o mesmo é generico e
	 *   retorna um tipo<T>
	 */

	@GetMapping // informa q o metodo responde a requizição do tipo get do http
	public ResponseEntity<List<User>> localizar() {
		List<User> list = services.localizar();
		return ResponseEntity.ok().body(list);
		
		/*
		User u = new User(1L, "Maria", "maria@gmail.com", "88888888", "123456");
		return ResponseEntity.ok().body(u);// .ok retorna a resposta com sucesso http, ,body retorna o corpo da resposta
		*/
	}
	
	//busca o usuario por id
	@GetMapping(value = "/{id}") //parametro da url. Ex. localhost:8080/users/2 "2 é o id"
	public ResponseEntity<User> findById(@PathVariable Long id){ //@PathVariable = para Spring aceitar o id como parametro
		User obj = services.localizarId(id);
		return ResponseEntity.ok().body(obj);
		
	}

}