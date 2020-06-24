package br.rifas;

import br.rifas.dto.PremioDTO;
import br.rifas.model.Premio;
import br.rifas.service.PremioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class RifasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RifasApplication.class, args);
	}

	@Autowired private PremioService premioService;

	@Override
	public void run(String... args) throws Exception {
		Premio premio = new Premio();

		premio.setDescricao("Teste de descriçao");
		premio.setDtSorteio(new Date());
		premio.setNuPremio(200);
		premio.setValor(300.0);

		PremioDTO premioDTO = new PremioDTO(premio);

		this.premioService.savePremio(premioDTO);
	}
}
