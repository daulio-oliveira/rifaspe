package com.pe.rifa.rifape.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pe.rifa.rifape.dto.CompradorDTO;
import com.pe.rifa.rifape.dto.NumerosDTO;
import com.pe.rifa.rifape.dto.PremioDTO;
import com.pe.rifa.rifape.dto.PremioSemCartelaDTO;
import com.pe.rifa.rifape.exception.ApiException;
import com.pe.rifa.rifape.model.Premio;
import com.pe.rifa.rifape.model.enums.TipoStatus;
import com.pe.rifa.rifape.repository.PremioRepository;

@Service
public class PremioService {
	
	@Autowired private PremioRepository premioRepository;
	
	public List<PremioDTO> findAllPremios() throws ApiException{
		List<Premio> premios = premioRepository.findAll();
		
		if(premios.size() == 0) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Nenhum prêmio encontrado!"); 
		}
		
		List<PremioDTO> premiosDTO = new ArrayList<PremioDTO>();
		for (Premio premio : premios) {
			premiosDTO.add(new PremioDTO(premio));
		}
		return premiosDTO;
	}
	
	public PremioDTO findByIdPremio(Long id) throws ApiException {
		Optional<Premio> find = premioRepository.findById(id);
		if(find.isEmpty()) {
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
	
	public List<PremioSemCartelaDTO> getPremiosComGanhadores() throws ApiException{
		List<Premio> premioComGanhadores = premioRepository.findByCpfGanhadorIsNotNull();
		
		if(premioComGanhadores.size() == 0) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Nenhum prêmio com ganhador foi encontrado!"); 
		}
		
		List<PremioSemCartelaDTO> ganhadores =  new ArrayList<PremioSemCartelaDTO>();
		for (Premio premio : premioComGanhadores) {
			ganhadores.add(new PremioSemCartelaDTO(premio));
		}
		
		return ganhadores;
	}
	
	public List<PremioSemCartelaDTO> getPremioSorteadosSemGanhadores() throws ApiException{
		List<Premio> premioSemGanhadores = premioRepository.findByNumeroSorteadoIsNotNullAndCpfGanhadorIsNull();
		
		if(premioSemGanhadores.size() == 0) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Nenhum prêmio sorteado sem ganhador foi encontrado!"); 
		}
		
		List<PremioSemCartelaDTO> premios =  new ArrayList<PremioSemCartelaDTO>();
		for (Premio premio : premioSemGanhadores) {
			premios.add(new PremioSemCartelaDTO(premio));
		}
		
		return premios;
	}

}
