package com.pe.rifa.rifape.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pe.rifa.rifape.model.Premio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PremioSemCartelaDTO {

	private Long id;
	
	private String descricao;
	
	private int nuPremio;
	
	private String detalhe1;
	
	private String detalhe2;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtSorteio;
	
	private Double valor;
	
	private String numeroSorteado;
	
	private String cpfGanhador;
	
	public PremioSemCartelaDTO(Premio premio) {
		
		this.descricao = premio.getDescricao();
		this.nuPremio = premio.getNuPremio();
		this.detalhe1 = premio.getDetalhe1();
		this.detalhe2 = premio.getDetalhe2();
		this.dtSorteio = premio.getDtSorteio();
		this.valor = premio.getValor();
		this.numeroSorteado = premio.getNumeroSorteado();
		this.cpfGanhador = premio.getCpfGanhador();
	}
	
	public Premio toEntity() {
		
		Premio premio = new Premio();
		premio.setDescricao(descricao);
		premio.setDetalhe1(detalhe1);
		premio.setDetalhe2(detalhe2);
		premio.setDtSorteio(dtSorteio);
		premio.setId(id);
		premio.setNuPremio(nuPremio);
		premio.setValor(valor);
		premio.setNumeroSorteado(numeroSorteado);
		premio.setCpfGanhador(cpfGanhador);
		
		return premio;
	}
}
