package com.livioneiva.cursos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livioneiva.cursos.entities.Order;
import com.livioneiva.cursos.services.OrderService;

//camada de recurcos da classe Order, onde fica os controladores rest, dependem da camada de serviços

@RestController //para informar q essa classe seja de recurso web, implementado por um controlador rest
@RequestMapping(value = "/order") //acesso e o nome para recurso
public class OrderResource {

	@Autowired
	OrderService services; //dependencia com a classe services
	
	@GetMapping //informa q o metodo responde ao tipo de requisição http
	public ResponseEntity<List<Order>> findAll(){//a classe ResponseEntity retorna uma arrayList
		List<Order> list = services.findAll();//metodo localizar da classe orderService retorna todas as ordens cadastrada
		return ResponseEntity.ok().body(list);// ResponseEntity.ok() = o satus será sempre ok(status 200) / body(list) é corpo da resposta
	}
	
	//buscar order por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order obj = services.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
