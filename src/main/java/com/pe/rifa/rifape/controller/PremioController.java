package com.pe.rifa.rifape.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.rifa.rifape.dto.PremioDTO;
import com.pe.rifa.rifape.dto.PremioSemCartelaDTO;
import com.pe.rifa.rifape.service.PremioService;

@RestController()
@RequestMapping("/premio")
public class PremioController {

	@Autowired private PremioService service;
	
	//Salva premio
	@PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> salvarPremio(@RequestBody @Valid PremioDTO dto) 
			throws Exception {
		
		PremioDTO premioDTO = service.savePremio(dto);
		return new ResponseEntity<>(premioDTO, HttpStatus.CREATED);
	}
	
	//Lista todos os prêmios
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PremioDTO>> getListarTodos(){
		List<PremioDTO> premiosDTO = service.findAllPremios();
		
		return new ResponseEntity<>(premiosDTO, HttpStatus.OK);
	}
	
	//Busca Premios por ID
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PremioDTO> findByIdPremio(@PathVariable Long id) {
		
		PremioDTO find = service.findByIdPremio(id);
		return new ResponseEntity<>(find, HttpStatus.OK);
	}
	
	//Lista todos os prêmios que tem ganhadores
	@GetMapping(value = "/premiosComGanhadores", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PremioSemCartelaDTO>> getListarPremiosComGanhadores(){
		List<PremioSemCartelaDTO> premiosDTO = service.getPremiosComGanhadores();
		
		return new ResponseEntity<>(premiosDTO, HttpStatus.OK);
	}
	
	//Lista prêmios com números soteados sem ganhadores
	@GetMapping(value = "/premiosSemGanhadores", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PremioSemCartelaDTO>> getPremioSorteadosSemGanhadores(){
		List<PremioSemCartelaDTO> premiosDTO = service.getPremioSorteadosSemGanhadores();
		
		return new ResponseEntity<>(premiosDTO, HttpStatus.OK);
	}
}
