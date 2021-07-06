package com.titamedia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.titamedia.model.Categoria;

public interface ICategoriaService extends ICRUD<Categoria, Integer>{

	Page<Categoria> listarPageable(Pageable pageable)throws Exception;
}
