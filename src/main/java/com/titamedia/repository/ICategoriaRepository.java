package com.titamedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.titamedia.model.Categoria;

public interface ICategoriaRepository extends IGenericRepository<Categoria, Integer>{

	@Query("FROM Categoria c WHERE c.estado = true")
	Page<Categoria> listarPageable(Pageable pageable);
}
