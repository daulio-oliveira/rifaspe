package br.rifas.controller;

import br.rifas.dto.PremioDTO;
import br.rifas.dto.PremioSemCartelaDTO;
import br.rifas.exception.ApiException;
import br.rifas.service.PremioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/premio")
@CrossOrigin
public class PremioController {

    private PremioService service;

    public PremioController(PremioService service) {
        this.service = service;
    }

    @PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> salvarPremio(@RequestBody @Valid PremioDTO dto)
            throws Exception {

        PremioDTO premioDTO = service.savePremio(dto);
        return new ResponseEntity<>(premioDTO, HttpStatus.CREATED);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PremioDTO>> getListarTodos() {
        List<PremioDTO> premiosDTO = service.findAllPremios();

        return new ResponseEntity<>(premiosDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PremioDTO> findByIdPremio(@PathVariable Long id) throws ApiException {

        PremioDTO find = service.findByIdPremio(id);
        return new ResponseEntity<>(find, HttpStatus.OK);
    }

    @GetMapping(value = "/premiosComGanhadores", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PremioSemCartelaDTO>> getListarPremiosComGanhadores() {
        List<PremioSemCartelaDTO> premiosDTO = service.getPremiosComGanhadores();

        return new ResponseEntity<>(premiosDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/premiosSemGanhadores", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PremioSemCartelaDTO>> getPremioSorteadosSemGanhadores() {
        List<PremioSemCartelaDTO> premiosDTO = service.getPremioSorteadosSemGanhadores();

        return new ResponseEntity<>(premiosDTO, HttpStatus.OK);
    }
}
