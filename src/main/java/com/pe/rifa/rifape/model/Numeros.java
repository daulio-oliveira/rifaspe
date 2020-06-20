package com.pe.rifa.rifape.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Numeros {
	
	@Id
	private Long id;
	
	private String numero;
	
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtReserva;
	
	@OneToOne
	private Comprador comprador;
	
	@ManyToOne
    @JoinColumn(name = "id_premio")
	private Premio premio;


}
