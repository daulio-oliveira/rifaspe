package com.cs.rifas.rifasaju.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cs.rifas.rifasaju.dto.CompradorDTO;
import com.cs.rifas.rifasaju.dto.NumerosDTO;
import com.cs.rifas.rifasaju.dto.PremioDTO;
import com.cs.rifas.rifasaju.dto.PremioSemCartelaDTO;
import com.cs.rifas.rifasaju.exception.ApiException;
import com.cs.rifas.rifasaju.model.Premio;
import com.cs.rifas.rifasaju.model.enums.TipoStatus;
import com.cs.rifas.rifasaju.repository.PremioRepository;


@Service
public class PremioService {
	
	@Autowired private PremioRepository premioRepository;
	
	public List<PremioDTO> findAllPremios(){
		List<Premio> premios = premioRepository.findAll();	
		List<PremioDTO> premiosDTO = new ArrayList<PremioDTO>();
		for (Premio premio : premios) {
			premiosDTO.add(new PremioDTO(premio));
		}
		return premiosDTO;
	}
	
	public PremioDTO findByIdPremio(Long id) throws ApiException {
		Optional<Premio> find = premioRepository.findById(id);
		if(!find.isPresent()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Prêmio não encontrado!"); 
		}
		
		return new PremioDTO(find.get());
	}
	
	public PremioDTO savePremio(PremioDTO dto) {
		dto.setNumeros(geraCartela());
		return new PremioDTO(premioRepository.save(dto.toEntity()));
	}
	
	public PremioDTO editPremio(PremioDTO dto) {
		return new PremioDTO(premioRepository.save(dto.toEntity()));
	}
	
	public List<NumerosDTO> geraCartela(){
		List<NumerosDTO> numeros =  new ArrayList<NumerosDTO>();
		for (int i = 0; i < 1000; i++) {
			NumerosDTO num = new NumerosDTO();
			CompradorDTO comprador = new CompradorDTO();
			num.setNumero(String.format("%04d", i));
			num.setComprador(comprador);
			num.setStatus(TipoStatus.LIVRE);
			numeros.add(num);
		}
		
		return numeros;
	}
	
	public List<PremioSemCartelaDTO> getPremiosComGanhadores(){
		List<Premio> premioComGanhadores = premioRepository.findByCpfGanhadorIsNotNull();
		
		List<PremioSemCartelaDTO> ganhadores =  new ArrayList<PremioSemCartelaDTO>();
		for (Premio premio : premioComGanhadores) {
			ganhadores.add(new PremioSemCartelaDTO(premio));
		}
		
		return ganhadores;
	}
	
	public List<PremioSemCartelaDTO> getPremioSorteadosSemGanhadores(){
		List<Premio> premioSemGanhadores = premioRepository.findByNumeroSorteadoIsNotNullAndCpfGanhadorIsNull();

		List<PremioSemCartelaDTO> premios =  new ArrayList<PremioSemCartelaDTO>();
		for (Premio premio : premioSemGanhadores) {
			premios.add(new PremioSemCartelaDTO(premio));
		}
		
		return premios;
	}

}
