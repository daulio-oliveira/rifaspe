package br.rifas.repository;

import br.rifas.model.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompradorRepository extends JpaRepository<Comprador, String> {
}
