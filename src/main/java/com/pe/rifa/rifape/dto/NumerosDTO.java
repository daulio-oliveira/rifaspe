package com.pe.rifa.rifape.dto;

import java.util.Date;

import com.pe.rifa.rifape.model.Numeros;
import com.pe.rifa.rifape.model.enums.TipoStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumerosDTO {

	private String numero;
	
	private TipoStatus status;
	
	private Date dtReserva;

	private CompradorDTO comprador;
	
	public NumerosDTO() {};
	
	public NumerosDTO(Numeros numeros) {
		this.numero = numeros.getNumero();
		this.status = numeros.getStatus().isEmpty()?TipoStatus.LIVRE:TipoStatus.getByValue(numeros.getStatus());
		this.dtReserva = numeros.getDtReserva();
		this.comprador = new CompradorDTO(numeros.getComprador());
	}
	
	public Numeros toEntity() {
		Numeros numeros = new Numeros();
		numeros.setNumero(this.numero);
		numeros.setStatus(this.status.toString());
		numeros.setDtReserva(this.dtReserva);
		numeros.setComprador(this.comprador.toEntity());
		
		return numeros;
	}
}
