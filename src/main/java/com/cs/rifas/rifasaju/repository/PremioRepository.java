package com.cs.rifas.rifasaju.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.rifas.rifasaju.model.Premio;

public interface PremioRepository extends JpaRepository<Premio, Long> {
	
	List<Premio> findByCpfGanhadorIsNotNull();
	
	List<Premio> findByNumeroSorteadoIsNotNullAndCpfGanhadorIsNull();

}
