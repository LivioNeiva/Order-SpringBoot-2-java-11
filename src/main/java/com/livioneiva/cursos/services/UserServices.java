package com.livioneiva.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livioneiva.cursos.entities.User;
import com.livioneiva.cursos.repositories.UserRepository;

//@Component = registra a classe como um componente do Spring, para que seja injetado como dependencia @Autowired
@Service
public class UserServices {

	@Autowired
	private UserRepository repository;
	
	public List<User> localizar(){
		return repository.findAll();
	}
	//recupera o usuario por id
	public User localizarId(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	
}
