package com.livioneiva.cursos.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.livioneiva.cursos.entities.User;
import com.livioneiva.cursos.repositories.UserRepository;
//so roda a coniguração abaixo quando estiver no perfil de test
@Configuration //informa é classe especifica de configuração
@Profile("test")//informa q a classe é uma configuração especifica de teste."test" -> application.properties
public class TestConfig implements CommandLineRunner {
	
	// a classe de TestConfig tera ter uma dependencia do UserRepository
	//injeção de dependência, declarando a dependencia
	@Autowired // Sppring faz a dependencia e associar uma instancia do UserRepository no TestConfig
	private UserRepository userRepository;

	//database seeding -> povoar banco de dados
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//userRepository é meu obj repository q acessa os dados
		userRepository.saveAll(Arrays.asList(u1,u2));//salvando os obj no banco de dados
	}
}

/*
 * classe de configuraão para instanciar o banco de dados
 */

/*
pro file de teste é um perfil do seu projeto especifico para fazer teste
/*
 * configurar o nosso banco de dados de teste, que vai ser o banco de dados H2
 * muito utiliado em java para fazer testes em aplicações, és um banco de dados em memoria
 * nao precisa fazer instalação, ele é integrado ao projeto, é so rodar o projeto q o 
 * mesmo irar funcionar
 */
/*
JPA repository
é um subframework do Spring.
A Java Persistence API (JPA) é a maneira padrão de persistir objetos Java em bancos de 
dados relacionais. O JPA consiste em duas partes: um subsistema de mapeamento para mapear 
classes em tabelas relacionais, bem como uma API EntityManager para acessar os objetos, 
definir e executar consultas e muito mais.
*/
