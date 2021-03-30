package com.livioneiva.cursos.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final Long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found id: "+id);
	}
}

/*
Diferença entre java.lang.RuntimeException e java.lang.Exception

Geralmente RuntimeExceptions são exceções que podem ser evitadas por meio de programação. Por exemplo NullPointerException, ArrayIndexOutOfBoundException. Se você procurar nullantes de chamar qualquer método, NullPointerExceptionisso nunca ocorrerá. Da mesma forma ArrayIndexOutOfBoundException, nunca ocorreria se você verificar o índice primeiro. RuntimeExceptionnão são verificados pelo compilador, portanto, é um código limpo.

Edição : Hoje em dia as pessoas favorecem RuntimeExceptionporque o código limpo que produz. É uma escolha totalmente pessoal.


Eu gosto desse ângulo de "exceções de tempo de execução poderiam ter sido evitadas pelo chamador". Isso significa que você (como chamador de um método) deve garantir que eles nem aconteçam. Considerando que as exceções verificadas são algo que você não pode evitar e, em vez disso, precisa lidar com elas após o fato. (E sim, como nem todos concordam com o conceito de exceções verificadas e muitas pessoas usam o RuntimeException para tudo, essa distinção ficou um pouco confusa). 


Suspeito que a verdadeira razão pela qual as pessoas percebem RuntimeExceptioné porque é simples e evita a necessidade de pensar nas diferenças entre exceções verificadas e não verificadas. Eu acho que pegar exceções de tempo de execução é uma péssima idéia, porque você vai pegar exceções irrecuperáveis, como NullPointerException
*/