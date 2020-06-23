package com.cs.rifas.rifasaju.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comprador {
	
	@Id
	private String cpf;
	
	private String nome;
	
	private String telefone;

}
