package com.titamedia.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.titamedia.model.Libro;

public interface ILibroRepository extends IGenericRepository<Libro, Integer>{

	@Query("FROM Libro l where l.cantidadDisponible > 0 AND l.estado = true")
	Page<Libro> listarLibrosDisponibles(Pageable pageable);
	
	@Query("FROM Libro l WHERE l.categoria.idCategoria = :idCategoria")
	List<Libro> listarPorCategoria(@Param("idCategoria") Integer idCategoria);
}
