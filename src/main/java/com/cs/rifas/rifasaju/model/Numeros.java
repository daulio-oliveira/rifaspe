package com.cs.rifas.rifasaju.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Numeros {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy = "increment")
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
