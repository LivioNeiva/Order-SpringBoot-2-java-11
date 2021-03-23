package com.livioneiva.cursos.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.livioneiva.cursos.entities.Category;
import com.livioneiva.cursos.entities.Order;
import com.livioneiva.cursos.entities.OrderItem;
import com.livioneiva.cursos.entities.Product;
import com.livioneiva.cursos.entities.User;
import com.livioneiva.cursos.entities.enums.OrderStatus;
import com.livioneiva.cursos.repositories.CategoryRepository;
import com.livioneiva.cursos.repositories.OrderItemRepository;
import com.livioneiva.cursos.repositories.OrderRepository;
import com.livioneiva.cursos.repositories.ProductRepository;
import com.livioneiva.cursos.repositories.UserRepository;
//so roda a coniguração abaixo quando estiver no perfil de test
@Configuration //informa é classe especifica de configuração
@Profile("test")//informa q a classe é uma configuração especifica de teste."test" -> application.properties
public class TestConfig implements CommandLineRunner {
	
	// a classe de TestConfig tera ter uma dependencia do UserRepository
	//injeção de dependência, declarando a dependencia
	@Autowired // Sppring faz a dependencia e associar uma instancia do UserRepository no TestConfig
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryReposity;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	//database seeding -> povoar banco de dados
	@Override
	public void run(String... args) throws Exception {
		
		//alimentando a tabela Category
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers mesas");
		Category cat4 = new Category(null, "notbooks");
		
		//Alimentando a tabela product
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		Product p6 = new Product(null, "NotBook DEL 4560LN", "processador intel, HG 1TB, memoria 8gb", 2000.0, "");
		
		//são os meus obj repository q acessa os dados
		categoryReposity.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5, p6));
		
		/*
		estamos tabalhando com a paradigma orientado a obj, quando salvarmos
		no banco de dados iremos usar a paradigma relacional.
		*/
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		p6.getCategories().add(cat4);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "Livio Neiva", "livio@quality.com", "88888888", "555555");
		/*
		instanciando os objs do tipo Order
		estamos colocando uma data no formaoto iso 8601, define varias possibilidade de formatos data "2019-06-20T19:53:07Z"
		a letra "Z" informa q esse horario esta no padrão UTC é horario de greenwich, é greenwich timezone=GMT
		é muito importante a vc padronizar a forma que seu horario está sendo representado
		no formato de String, para q depois possa ser convertido para horario local
		*/
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAGO, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.AGUARDANDO_PAGAMENTO, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.AGUARDANDO_PAGAMENTO, u1);
		Order o4 = new Order(null, Instant.parse("2021-03-23T01:43:10Z"), OrderStatus.ENVIADO, u3);
		/*
		 * no dba vai aparecer o horario com 3hrs de atraso.
		 * Motivo: o computaor do usuario esta com horario do brasil, o horario de brasilia
		 * é 3hrs atrasado em relação ao horario UTC, O Banco de dados H2 está trablhando com
		 * horario local. resumo "HORARIO CORRETO"
		 */
				
		//userRepository é meu obj repository q acessa os dados
		userRepository.saveAll(Arrays.asList(u1,u2,u3));//salvando os obj no banco de dados
		orderRepository.saveAll(Arrays.asList(o1,o2,o3,o4));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		OrderItem oi5 = new OrderItem(o4, p6, 5, p6.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4,oi5));
		
	}
}
/*
acontecerar um loop

Biblioteca, API ou SDK: Conjunto de código pronto. Pode ser um conjunto de funções 
matemáticas, não precisa necessariamente orientada a objetos. ... Toolkit: Conjunto de 
classes ou funções cuja arquitetura não se baseia em extensão, mas sim em uso.

a biblioteca q faz a serialização para sistema, é jackson, o mesmo acusou um erro
por causa do loop, por causa da associação de mao dupla entre as 
classes User e Order ou seja, ambos se relacionam, User se relaciona com Order 
e Order se relaciona com user, e json q é a biblioteca de serialização, 
o loop é User chama Order,	e os pedidos chamam o usuario. a biblioteca q faz a
serialização para nossas classes é jackson e jackson acousou um erro de loop.
para resolver o problemas temos colocar uma annotation @JsonIgnote em um dos lados.
 */
/*
 * https://www.conversion.com.br/blog/lazy-loading/
 lazy Loading
 quando vc carrega a classe q tem o relacionamento 1 para muitos, o relacionamento
 muitos nao é exibido na requisição, para nao causar lentidão, para nao estourar a memoria
 e o trafico do computador, é feito lazy loading
 quando vc carrega a classe q tem relacionamento muitos para 1, o relacioamento é listado
 na requisição.
 as informações acima é padrão
 
 Lazy loading é um padrão de projeto de software, comumente utilizado em linguagens de 
 programação, para adiar a inicialização de um objeto até o ponto em que ele é 
 necessário. Isso pode contribuir para a eficiência no funcionamento de um programa, se 
 utilizado adequadamente[1]. O oposto do carregamento lento (lazy) é o carregamento 
 imediato (eager). Os ganhos de desempenho são especialmente significativos quando 
 inicializar um objeto é custoso, como no caso de acesso a serviços de rede e a listas 
 de objetos de banco de dados (ver JPA e Hibernate). Isso o torna ideal em casos onde o 
 conteúdo é acessado em um servidor de rede, como no caso de sistemas Web[2].
 
 spring.jpa.open-in-view=true
 #JPA. open-in-view=true = chama o jpa para pegar informações dos relacionamentos das 
 tabelas e exibir na requisição, ex. tenho por @JsonIgnore no relacionamento da classe
 para q possa ser exbido na requisição, isso so acontece pq colocamos no arquivo
 properdies essa linha de comando spring.jpa.open-in-view=true
 */

/*
 * classe de configuraão para instanciar o banco de dados
 */

/*
@ProFile de teste é um perfil do seu projeto especifico para fazer teste
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
