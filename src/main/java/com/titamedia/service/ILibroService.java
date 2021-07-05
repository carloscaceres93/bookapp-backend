package com.titamedia.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.titamedia.model.Libro;

public interface ILibroService extends ICRUD<Libro, Integer> {

	Page<Libro> listarPaginado(Pageable pageable) throws Exception;

	Page<Libro> listarLibrosDisponibles(Pageable pageable) throws Exception;
	
	List<Libro> listarPorCategoria(Integer idCategoria)throws Exception;
}
