package com.algaworks.algalog.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa o modelo para expor
 * @author duduc
 *
 */
@Getter
@Setter
public class DestinatarioModel {

	private String nome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	
}
