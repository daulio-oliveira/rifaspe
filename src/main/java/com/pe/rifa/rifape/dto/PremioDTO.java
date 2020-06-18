package com.pe.rifa.rifape.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pe.rifa.rifape.model.Numeros;
import com.pe.rifa.rifape.model.Premio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PremioDTO {

private Long id;
	
	private String descricao;
	
	private int nuPremio;
	
	private String detalhe1;
	
	private String detalhe2;
	
	private Date dtSorteio;
	
	private Double valor;
	
	private List<NumerosDTO> numeros;
	
	public PremioDTO(Premio premio) {
		this.descricao = premio.getDescricao();
		this.nuPremio = premio.getNuPremio();
		this.detalhe1 = premio.getDetalhe1();
		this.detalhe2 = premio.getDetalhe2();
		this.dtSorteio = premio.getDtSorteio();
		this.valor = premio.getValor();
		this.numeros = new ArrayList<NumerosDTO>();
		for (Numeros num : premio.getNumeros()) {
			NumerosDTO numDto = new NumerosDTO(num);
			this.numeros.add(numDto);
		}
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
		List<Numeros> nums = new ArrayList<Numeros>();
		for (NumerosDTO numerosDTO : numeros) {
			Numeros num = numerosDTO.toEntity();
			nums.add(num);
		}
		
		premio.setNumeros(nums);
		
		return premio;
	}
}
