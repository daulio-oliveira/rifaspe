package br.rifas.repository;

import br.rifas.model.Numeros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumerosRepository extends JpaRepository<Numeros, Long> {
}
