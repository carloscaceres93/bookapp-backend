package com.titamedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.titamedia.exception.ModeloNotFoundException;
import com.titamedia.model.Categoria;
import com.titamedia.repository.ICategoriaRepository;
import com.titamedia.repository.IGenericRepository;
import com.titamedia.service.ICategoriaService;

@Service
public class CategoriaServiceImpl extends CRUDImpl<Categoria, Integer> implements ICategoriaService {

	@Autowired
	private ICategoriaRepository CategoriaRepo;

	@Override
	protected IGenericRepository<Categoria, Integer> getRepo() {
		return CategoriaRepo;
	}

	@Override
	public Page<Categoria> listarPageable(Pageable pageable) throws Exception {
		Page<Categoria> categorias = CategoriaRepo.findAll(pageable);
		
		if(categorias.isEmpty()) {
			throw new ModeloNotFoundException("No se ecnontraron datos"); 
		}
		return categorias;
	}

}
