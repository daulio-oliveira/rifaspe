package com.cs.rifas.rifasaju.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Premio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_premio", updatable = false)
	private Long id;
	
	private String descricao;
	
	private int nuPremio;
	
	private String detalhe1;
	
	private String detalhe2;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtSorteio;
	
	private Double valor;
	
	@OneToMany(mappedBy = "numero", fetch = FetchType.EAGER)
	private List<Numeros> numeros;
	
	private String numeroSorteado;
	
	private String cpfGanhador;
	

}
