package com.titamedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.titamedia.model.Tarifa;

public interface ITarifaRepository extends IGenericRepository<Tarifa, Integer>{

	@Query("FROM Tarifa t WHERE t.estado = true")
	Page<Tarifa> listarPageable(Pageable pageable);
}
