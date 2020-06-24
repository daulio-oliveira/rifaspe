package br.rifas.service;

import br.rifas.dto.CompradorDTO;
import br.rifas.dto.NumerosDTO;
import br.rifas.dto.PremioDTO;
import br.rifas.dto.PremioSemCartelaDTO;
import br.rifas.exception.ApiException;
import br.rifas.model.Premio;
import br.rifas.model.enums.TipoStatus;
import br.rifas.repository.NumerosRepository;
import br.rifas.repository.PremioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PremioService {

    private PremioRepository premioRepository;
    private NumerosRepository numerosRepository;

    public PremioService(PremioRepository premioRepository, NumerosRepository numerosRepository) {
        this.premioRepository = premioRepository;
        this.numerosRepository = numerosRepository;
    }

    public List<PremioDTO> findAllPremios(){
        List<Premio> premios = premioRepository.findAll();
        List<PremioDTO> premiosDTO = new ArrayList<PremioDTO>();
        for (Premio premio : premios) {
            PremioDTO premioDTO = new PremioDTO(premio);
            premioDTO.setNumeros(null);
            premiosDTO.add(premioDTO);
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

        Premio p = premioRepository.save(dto.toEntity());
        this.numerosRepository.saveAll(p.getNumeros());
        return new PremioDTO(p);
    }

    public PremioDTO editPremio(PremioDTO dto) {
        return new PremioDTO(premioRepository.save(dto.toEntity()));
    }

    public List<NumerosDTO> geraCartela(){
        List<NumerosDTO> numeros =  new ArrayList<NumerosDTO>();
        for (int i = 1; i < 1000; i++) {
            NumerosDTO num = new NumerosDTO();
            num.setNumero(String.format("%04d", i));
            num.setStatus(TipoStatus.LIVRE.value);
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
