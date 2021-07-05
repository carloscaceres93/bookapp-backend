package com.titamedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
