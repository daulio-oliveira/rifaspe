package br.rifas.repository;

import br.rifas.model.Premio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PremioRepository extends JpaRepository<Premio, Long> {

    List<Premio> findByCpfGanhadorIsNotNull();

    List<Premio> findByNumeroSorteadoIsNotNullAndCpfGanhadorIsNull();

}
