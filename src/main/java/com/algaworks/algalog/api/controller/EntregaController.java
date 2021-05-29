package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	private FinalizacaoEntregaService finalizacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	
	/**
	 * @RequestBody - transforma o json em objeto
	 * @param entrega
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput){
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		return entregaAssembler.toModel(solicitacaoEntregaService.solicitarEntrega(novaEntrega));
	}
	
	@GetMapping
	public List<EntregaModel> listar(){
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	
	@PutMapping("{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)//deu tudo certo mas não retorna nenhum conteudo
	public void finalizacao(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}
	
	@GetMapping("/{idEntrega}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long idEntrega){
		return entregaRepository.findById(idEntrega).map(entrega -> 
			// forma de retornar o objeto com lâmbida
			//		return entregaRepository.findById(idEntrega).map(ResponseEntity::ok)
			//				.orElse(ResponseEntity.notFound().build());
//			EntregaModel entregaModel = new EntregaModel();
//			entregaModel.setId(idEntrega);
//			entregaModel.setNomeCliente(entrega.getCliente().getNome());
//			entregaModel.setDestinatario(new DestinatarioModel());
//			entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
//			entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
//			entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
//			entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
//			entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
//			entregaModel.setTaxa(entrega.getTaxa());
//			entregaModel.setStatus(entrega.getStatus());
//			entregaModel.setDataPedido(entrega.getDataPedido());
//			entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());
			ResponseEntity.ok(entregaAssembler.toModel(entrega))
		).orElse(ResponseEntity.notFound().build());
	}

}
