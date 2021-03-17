package com.livioneiva.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livioneiva.cursos.entities.Order;
import com.livioneiva.cursos.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	//Optional<T> pode exixtir um objeto ou nao, retorna um obj Optional
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
	
	/*
	//Optional<T> pode exixtir um objeto ou nao, retorna um obj optinal
	@GetMapping("{id}")
	public Optional<Cliente> acharPorId(@PathVariable Integer id) {
		return repository.findById(id);
	}
	*/
	
}
