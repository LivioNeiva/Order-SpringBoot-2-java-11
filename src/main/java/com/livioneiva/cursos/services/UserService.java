package com.livioneiva.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livioneiva.cursos.entities.User;
import com.livioneiva.cursos.repositories.UserRepository;

//@Component = registra a classe como um componente do Spring, para que seja injetado como dependencia @Autowired
@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	//recupera o usuario por id
	//Optional<T> pode exixtir um objeto ou nao, retorna um obj optinal
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();//get() = i retornar o obj do tipo user que estiver dentro do optional
	}
	
	/*
	//Optional<T> pode exixtir um objeto ou nao, retorna um obj optinal
	@GetMapping("{id}")
	public Optional<Cliente> acharPorId(@PathVariable Integer id) {
		return repository.findById(id);
	}
	*/
	
	public User insert(User obj) {
		return repository.save(obj);
	}
}
