package com.cs.rifas.rifasaju.dto;

import com.cs.rifas.rifasaju.model.Comprador;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompradorDTO {

	private String cpf;
	
	private String nome;
	
	private String telefone;
	
	public CompradorDTO() {};
	
	public CompradorDTO(Comprador comprador) {
		
		this.cpf = comprador.getCpf();
		this.nome = comprador.getNome();
		this.telefone = comprador.getTelefone();
	}
	
	public Comprador toEntity() {
		Comprador comprador = new Comprador();
		comprador.setCpf(this.cpf);
		comprador.setNome(this.nome);
		comprador.setTelefone(this.telefone);
		return comprador;
	}
}
