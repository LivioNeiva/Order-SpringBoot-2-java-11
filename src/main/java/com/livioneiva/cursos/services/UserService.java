package com.livioneiva.cursos.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livioneiva.cursos.entities.User;
import com.livioneiva.cursos.repositories.UserRepository;
import com.livioneiva.cursos.services.exceptions.DatabaseException;
import com.livioneiva.cursos.services.exceptions.ResourceNotFoundException;

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
		//o .get(), da a excepions 500 caso o id nao estivesse presente, vamor fazer a troca pelo orElseThrow
		//return obj.get();//get() = i retornar o obj do tipo user que estiver dentro do optional
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
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
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		/*
		500 Internal Server Error = acontece quando vc for apagar um registo
		que se relaciona com outras entidades, ex. o usuario com id 1, tem pedidos
		no nome dele. classe user se relaciona com a classe order existe registros nesse
		relacionamento
		500 Internal Server Error = O servidor encontrou uma condição inesperada que o 
		impediu de atender à solicitação. 

		 */
	}
	public User update(Long id, User obj) {
	/*
	getOne()ele vai instanciar o usuario, so q nao vai fazer a operação no dba, ele vai 
	deixar o obj monitorado pelo jpa, para que seja trabalhado com ele, e em seguida programador 
	possa eetuar alguma operação com dba. E melhor do q usar o findById, o findById
	vai no banco de dados e traz o obj para ser feita a operação. o getOne() nao, ele
	so prepara o obj monitorado para que seja feito as alterações e depois efetuar
	uma operação com dba. esse precesso é mais eiciente
	 */
		try {
			//vamos atualizar o obj entity com os dados q vinheram do argumento update(User obj)o id so para localizar obj
			User entity = repository.getOne(id);
			//criamos um metodo(função) chamada updateData
			updateData(entity, obj);//metodo q atualiza os dados do obj entity tipo User,baseado nos dados q chegaram do obj q tb é do tipo User
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	//metodo q atualiza os dados do obj entity tipo User, 
	//baseado nos dados q chegaram do obj q tb é do tipo User
	private void updateData(User entity, User obj) {//vamos atualizar os dados do entity,com base no q chegou do obj
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
