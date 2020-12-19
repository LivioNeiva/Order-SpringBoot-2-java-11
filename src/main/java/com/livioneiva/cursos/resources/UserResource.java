package com.livioneiva.cursos.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livioneiva.cursos.entities.User;

//camada de recursos - onde fica os controladores rest, os controladores rest, dependem da camada de serviços

@RestController // para informarmos q seja recurso web implementado por um controlador web
@RequestMapping(value = "/users") // nome para recurso, acesso ao recurso
public class UserResource {

	// metodo para acessar usuarios da classe User
	// ResponseEntity - retorna resposta de requisiçoes web, o mesmo é generico e
	// retorna um tipo<T>
	@GetMapping // informa q o metodo responde a requizição do tipo get do http
	public ResponseEntity<User> findAll() {
		User u = new User(1L, "Maria", "maria@gmail.com", "88888888", "123456");
		return ResponseEntity.ok().body(u);// .ok retorna a resposta com sucesso http, ,body retorna o corpo da resposta
	}

}