package com.algaworks.algalog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	/**
	 * o Nome na assinatura é visto pelo spring data e o resto ele faz, mas é com o nome exato.
	 * @param nome
	 * @return
	 */
	List<Cliente> findByNome(String nome);

	/**
	 * Pesquisa por clientes que CONTEM o nome.
	 * @param nome
	 * @return
	 */
	List<Cliente> findByNomeContaining(String nome);
	
	Optional<Cliente> findByEmail(String email);
}
