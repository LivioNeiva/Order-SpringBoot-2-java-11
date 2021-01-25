package com.livioneiva.cursos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livioneiva.cursos.entities.User;

//usaremos a interface UserRepository para salvar os dados no banco de dados
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}


/*
 * o userRepository vai ser responsavel por fazer operações com a entidade User
 * para criarmos um userRepository reutilizando o jpaRepository basta vc fazer ele
 * extender para interface JpaRepository passando o tipo da entidade(User) q o mesmo vai acessar
 * passando e o tipo da chave(Long) dessa entidade
 */

/*
 * apenas com essa definição ja está pronto
 * se crianmos a interface, teremos q criar a implementação da interface????
 * Res. "NÃO"
 * porque o Spring data JPA ele ja tem uma implementação padrão para essa interface
 * se definirmos o JpaRepository<E,id) "generic" utilizando sua entidade "User" 
 * e id da entidade "Long" ja teremos uma implementaão padrao para esse tipo especifico
 * de definimos JpaRepository<User, Long>
 * so a com a definição tudo branco ja está pronto
 */
