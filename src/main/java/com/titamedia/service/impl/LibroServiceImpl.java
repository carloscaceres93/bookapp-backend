package com.titamedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.titamedia.exception.ModeloNotFoundException;
import com.titamedia.model.Libro;
import com.titamedia.repository.IGenericRepository;
import com.titamedia.repository.ILibroRepository;
import com.titamedia.service.ILibroService;

@Service
public class LibroServiceImpl extends CRUDImpl<Libro, Integer> implements ILibroService {

	@Autowired
	private ILibroRepository libroRepo;

	@Override
	protected IGenericRepository<Libro, Integer> getRepo() {
		return libroRepo;
	}

	@Override
	public Page<Libro> listarPaginado(Pageable pageable) throws Exception {
		Page<Libro> libros = libroRepo.findAll(pageable);

		if (libros.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron libros disponibles");
		}
		return libros;
	}

	@Override
	public Page<Libro> listarLibrosDisponibles(Pageable pageable) throws Exception {
		Page<Libro> libros = libroRepo.listarLibrosDisponibles(pageable);

		if (libros.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron libros disponibles");
		}

		return libros;
	}

	@Override
	public List<Libro> listarPorCategoria(Integer idCategoria) throws Exception {
		List<Libro> libros = libroRepo.listarPorCategoria(idCategoria);

		if (libros.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron libros disponibles");
		}

		return libros;
	}
}
