package com.livioneiva.cursos.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.livioneiva.cursos.services.exceptions.DatabaseException;
import com.livioneiva.cursos.services.exceptions.ResourceNotFoundException;
/*
com essa annotations a classe vai interceptar as exceptons q acontecerem para que
esse obj possa executar uma possivel tratamento.
 */
@ControllerAdvice
public class ResourceExceptionHandler {
	
	/*
	esse é primeiro tratamento usando a classe ResoucerNotFoundException
	com a annotations @ExceptionHandler eu estou informando q o metodo resourceNotFound 
	vai interceptar qualquer exceptions desse tipo ResourceNotFoundException quando for 
	lançada e vai fazer o tratamento para dentro dessa classe.
	@ExceptionHandler(ResourceNotFoundException.class) = essa annotations faz a classe ser
	capaz de interceptar as requisições q deu exceptions, a exceptions vai ser inserida
	nessa classe.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found"; //msg basica do erro "Recurso não encontrado"
		HttpStatus status = HttpStatus.NOT_FOUND; //status de uma resposta q deu essa exceptions ResourceNotFoundException = 404 Not Found = The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		//para o ResponseEntity da uma resposta com codigo personalizado, eu uso status(status)
		//eu chamo o body para passar o corpo da resposta
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		String error = "Data base errors erros constrain no dba";
		HttpStatus status = HttpStatus.BAD_REQUEST;//vamos lançar a exception correta, é 400 Bad Request, esta aparecendo a 500
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
}
/*
https://pt.wikipedia.org/wiki/Servlet
Servlet (servidorzinho em tradução livre) é uma classe Java usada para estender as 
funcionalidades de um servidor. Apesar dos servlets poderem responder a quaisquer tipos
 de requisições, eles normalmente são usados para estender as aplicações hospedadas por
  servidores web, desta forma eles podem ser imaginados como Applets Java que rodam em 
  servidores em vez de rodarem nos navegadores web. Estes tipos de servlets são os 
  equivalentes Java a outras tecnologias de conteúdo Web dinâmico, como PHP e ASP.NET.

Também pode ser definido como um componente semelhante um servidor, que gera dados HTML 
e XML para a camada de apresentação de uma aplicação Web. Ele processa dinamicamente 
requisições e respostas. 

Descrição

A API Java Servlet (do pacote javax.servlet) proporciona ao desenvolvedor a possibilidade de adicionar conteúdo dinâmico em um servidor web usando 
a plataforma Java.

Esta tecnologia disponibiliza ao programador da linguagem Java uma interface para o 
servidor web (ou servidor de aplicação), através de uma API. As aplicações baseadas no 
Servlet geram conteúdo dinâmico (normalmente HTML) e interagem com os clientes, 
utilizando o modelo requisição-resposta.

Os servlets normalmente utilizam o protocolo HTTP, apesar de não serem restritos a ele. Um Servlet necessita de um container Web para ser executado. 

*/