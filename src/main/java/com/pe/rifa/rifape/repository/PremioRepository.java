package com.pe.rifa.rifape.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pe.rifa.rifape.model.Premio;

public interface PremioRepository extends JpaRepository<Premio, Long> {
	
	List<Premio> findByCpfGanhadorIsNotNull();
	
	List<Premio> findByNumeroSorteadoIsNotNullAndCpfGanhadorIsNull();

}
