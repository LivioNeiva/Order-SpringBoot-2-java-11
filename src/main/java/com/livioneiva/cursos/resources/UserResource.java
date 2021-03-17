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

//camada de recursos - onde fica os controladores rest, os controladores rest dependem da camada de serviços

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
	public ResponseEntity<List<User>> findAll() {//<List<user>> a resosta da requisião será uma lista de usuarios do tipo user
		List<User> list = services.findAll();
		return ResponseEntity.ok().body(list);
		
		/*
		User u = new User(1L, "Maria", "maria@gmail.com", "88888888", "123456");
		return ResponseEntity.ok().body(u);// .ok retorna a resposta com sucesso http, ,body retorna o corpo da resposta
		*/
	}
	
	//busca o usuario por id
	@GetMapping(value = "/{id}") //parametro da url. Ex. localhost:8080/users/2 "2 é o id"
	public ResponseEntity<User> findById(@PathVariable Long id){ //@PathVariable = para Spring aceitar o id como parametro
		User obj = services.findById(id); //chama o metodo findAllId, localiza registro por id
		return ResponseEntity.ok().body(obj);//ResponseEntity.ok() resposta de sucesso http(Status 200) / body(obj) é o corpo da requisição, é a listaa
		
	}

}
/*

@ResponseStatus  //  @ResponseEntity
diferença de ResponseStatus e ResponseEntity
uma resposta http é formado por um cabeçalho, status e corpo da resposta.
HttpStatus.CREATED = compoe a resposta http, status, cabeçalho, corpo e codigo. o codigo de status é CREATED 201
com RsponseEntity a gente consegue maipular a resposta como um todo, podemos add cabeçãlho, o corpo da resposta(body)
Ja anotação #ResponseStatus ela altera apenas o status code da nossa resposta, entao a proposta é simplesmente retornar
uma resposta especifica.
a vantagem de usar o ResponseEntity é ter uma maior flexibilidde de poder demanimular melhor os dados da resposta
*/

/*
@Component
Component Registron(Registro de componente)
quando vc tem um obj q ele vai poder ser injetado pelo pecanismo de ingejão de dependencia 
do spring,  classe de objeto, a classe de objeto está registrada no mecanismo de injeção
de dependencia, todo framework q iremos usar tanto para back-end como para front-end,
ele vai ter alguma forma de registrar algum serviço, alguma classe no mecanismo de injeção
de dependencia.
ex.
o userService service;
para funconar, ela tem está registrada como componente do Spring, temos q ir na classe 
userServices e registra-la como componente, o Sring tem algumas anotations q é so colocar
no começo q registra.
ex.

@Component
public class userServices{
}
@Component -> ela ja registra q sua classe como um componente do Spring
e ele vai poder ser injetado assim automaticamnete com o AutoWired.

*/