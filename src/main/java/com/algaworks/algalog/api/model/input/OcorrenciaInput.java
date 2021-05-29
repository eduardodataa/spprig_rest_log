package com.algaworks.algalog.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa o modelo para expor
 * @author duduc
 *
 */
@Getter
@Setter
public class OcorrenciaInput {

	@NotBlank
	private String descricao;
	
}
