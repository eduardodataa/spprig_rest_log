package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.algalog.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

	//grupo de validaçao padrão
	@NotNull(groups = ValidationGroups.ClientId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Anotation são desnecessárias se o nome da coluna for o mesmo da tabela
	 * @NotBlank - não permite nulo nem vazio
	 */
	@NotBlank
	@Size(max = 60)
	@Column(name = "nome")
	private String nome;

	@NotBlank
	@Email
	@Size(max = 255)
	private String email;

	@NotBlank
	@Column(name = "fone")
	@Size(max = 20)
	private String telefone;
	
	
	
}