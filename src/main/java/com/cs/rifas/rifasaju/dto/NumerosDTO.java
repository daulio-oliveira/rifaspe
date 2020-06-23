package com.cs.rifas.rifasaju.dto;

import java.util.Date;

import com.cs.rifas.rifasaju.model.Numeros;
import com.cs.rifas.rifasaju.model.enums.TipoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumerosDTO {

	private Long id;
	
	private String numero;
	
	private TipoStatus status;
	
	private Date dtReserva;

	private CompradorDTO comprador;
	
	public NumerosDTO() {};
	
	public NumerosDTO(Numeros numeros) {
		this.id = numeros.getId();
		this.numero = numeros.getNumero();
		this.status = numeros.getStatus().isEmpty()?TipoStatus.LIVRE:TipoStatus.getByValue(numeros.getStatus());
		this.dtReserva = numeros.getDtReserva();
		this.comprador = new CompradorDTO(numeros.getComprador());
	}
	
	public Numeros toEntity() {
		Numeros numeros = new Numeros();
		numeros.setId(this.id);
		numeros.setNumero(this.numero);
		numeros.setStatus(this.status.toString());
		numeros.setDtReserva(this.dtReserva);
		numeros.setComprador(this.comprador.toEntity());
		
		return numeros;
	}
}
