package com.livioneiva.cursos.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.livioneiva.cursos.entities.User;
import com.livioneiva.cursos.services.UserService;

//camada de recursos - onde fica os controladores rest, os controladores rest dependem da camada de serviços

@RestController // para informarmos q essa classe seja recurso web implementado por um controlador rest
@RequestMapping(value = "/users") // nome para recurso, acesso ao recurso
public class UserResource {
	
	@Autowired //dependencia
	UserService services;

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
	
	//mapear a url para q seja feita a requisição vinda do cliente
	//mapea esse metodo para uma requisição do cliente
	@PostMapping//inserir um novo recurso no dba,esse metodo vai receber um metodo post http
	public ResponseEntity<User> insert(@RequestBody User obj){//@RequestBody = temos q informar q o obj User vai ser um json q vai vim do corpo da requisição. Conversao  do obj json para objeto cliente
		obj = services.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")//vai pegar  a uri do id User,achar o recurso criado. No caso, essa linha está sendo montada, essa url encotra-se  no header do postman, em location
											.buildAndExpand(obj.getId()).toUri();//buildAndExpand(obj.getId()) = captura e iforma o id do obj e toUri() vai converter o obj para uma uri de uma url
		return ResponseEntity.created(uri).body(obj);
	/*created(uri) espera um obj uri no seu parametro, pelo fato q o padrão http
	quando vc vai retornar um resposta 201, é esperado q a resposta contenha um cabeçalho
	chamado location contendo o endereço do novo recurso q vc inseriu.
	buildAndExpand(obj.getId()).toUri(); = vai informar o ("/{id}" q foi inserido no
	parametro, no nosso caso foi obj.getId(). .toUri() = para converte para metodo URI
	A ESPOSTA DA REQUISIÇÃO: STATUS 201 CREATED = O status HTTP "201 Created" é utilizado 
	como resposta de sucesso, indica que a requisição foi bem sucedida e que um novo 
	recurso foi criado. Este novo recurso é efetivamente criado antes do retorno da 
	resposta e o novo recurso é enviado no corpo da mensagem (pode vir na URL ou na 
	header  Location).
	 */
		
	/*
	 (@RequestBody User obj) = para dizer q o obj do tipo User vai chegar no modo Json
	 e esse json vai ser desceralizado para o obj User, temos colocar a annotations @RequestBody
	 
	 */
	}

	@DeleteMapping(value = "/{id}")//Void pq nao vai retornar nada.
	public ResponseEntity<Void> delete(@PathVariable Long id){//@PathVariable = para serr econhecido como uma vaiavel da minha url
		services.delete(id);
		return ResponseEntity.noContent().build();//pelo fato de ser uma resposta sem corpo eu chamo noContent, e codigo http de uma resposta q nao tem conteudo é 204
		//noContent = O código de resposta HTTP de status de sucesso 204 No Content indica que a solicitação foi bem sucedida e o cliente não precisa sair da página atual. Uma resposta 204 é armazenada em cache por padrão. Um cabeçalho ETag está incluso na resposta
	}
	
	//metodo retorna User atualizado
	@PutMapping(value = "/{id}")//atualiza pela localização id
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj ) {
		obj = services.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
/*

@ResponseStatus  //  @ResponseEntity
diferença de ResponseStatus e ResponseEntity
uma resposta http é formado por um cabeçalho, status e corpo da resposta.
HttpStatus.CREATED = compoe a resposta http, status, cabeçalho, corpo e codigo. o codigo de status é CREATED 201
com RsponseEntity a gente consegue maipular a resposta como um todo, podemos add cabeçalho, o corpo da resposta(body)
Ja anotação #ResponseStatus ela altera apenas o status code da nossa resposta, entao a proposta é simplesmente retornar
uma resposta especifica.
a vantagem de usar o ResponseEntity é ter uma maior flexibilidde de poder demanimular melhor os dados da resposta
*/

/*
 A ESPOSTA DA REQUISIÇÃO: STATUS 201 CREATED = A solicitação foi atendida e resultou na 
 criação de um novo recurso. O recurso recém-criado pode ser referenciado pelo (s) URI (s)
  retornado (s) na entidade da resposta, com o URI mais específico para o recurso 
  fornecido por um campo de cabeçalho Location. 
 */

/*
 UriComponentsBuilder com métodos de fábrica estáticos adicionais para criar links com base no HttpServletRequest atual.

Nota: A partir de 5.1, os métodos nesta classe não extraem os cabeçalhos "Encaminhado" e 
"X-Encaminhado- *" que especificam o endereço de origem do cliente. Por favor, use 
ForwardedHeaderFilter, ou similar do servidor subjacente, para extrair e usar tais 
cabeçalhos, ou para descartá-los. 
 */
/*
 @PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){//@RequestBody = temos q informar q o obj User vai ser um json q vai vim do corpo da requisição. Conversao  do obj json para objeto cliente
		obj = services.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")//vai pegar uri do id User
											.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
		Charles Queiroz, [28.03.21 20:02]
@PostMapping = É a anotação do Spring que indica o framework q ele deve atender uma 
requição rest na URI informada com o verbo HTTP POST. OU seja, vc usa quando quer enviar 
alguma informação do cliente para o backend. No caso, está enviando um usuário no 
corpo da requisição. 

ResponseEntity<User> insert(@RequestBody User obj) = Nessa assinatura do método, tem 
algumas coisas. Primeiro, o retorno foi definido para ResponseEntity<User>, que é uma das
 abstrações usadas pelo Spring pra serializar a resposta (no caso desse exemplo, é do 
 tipo User) em um JSON (por padrao). Já no parametro, tem uma anotação que é a 
 @RequestBody que serve basicamente pra indicar o Spring que o body da requsição será 
 deserializado em um objeto do tipo User. 

obj = service.insert(obj); = Nessa linha, o desenvolvedor optou por salvar o usuário 
recebido (provavelmente no banco de dados) e esse código foi delegado a camada de 
serviços por questao de isolamento de responsabilidades (controler recebe a requisição, 
valida os dados e entrega pra camada de serviços q tem a lógica de negócio, que entrega 
pra camada de persistencia q salva no banco). Ao salvar com sucesso, a resposta é o mesmo 
objeto User só que com o ID (que foi gerado pelo banco de dados) preenchido. 


URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); = 
Aqui é a linha mais interessante do código. Como  esse metodo é um POST, e de acordo com 
as boas praticas de constução de API REST, ao criar um recurso (algo no banco de dados) 
vc deveria retornar um header chamado Localtion com um URI para, caso vc queira dar um 
get nela, achar o recurso criado. No caso, essa linha está "montando" essa url q no caso 
seria algo como Localtion: http://localhost:8080/app/api/user/1. 

return ResponseEntity.created(uri).body(obj); = Nessa linha, ele monta efetivamente a 
resposta devolvendo o usuário criado (junto com o ID preenchido) e também o header 
Localtion com a URI para dar um GET direto e achar esse recurso criado.

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