package com.titamedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.titamedia.model.Menu;
import com.titamedia.repository.IGenericRepository;
import com.titamedia.repository.IMenuRepository;
import com.titamedia.service.IMenuService;

@Service
public class MenuServiceImpl extends CRUDImpl<Menu, Integer> implements IMenuService{

	@Autowired
	private IMenuRepository menuRepo;

	@Override
	protected IGenericRepository<Menu, Integer> getRepo() {
		return menuRepo;
	}
	
	@Override
	public List<Menu> listarMenuPorUsuario(String nombre) throws Exception{
		return menuRepo.listarMenuPorUsuario(nombre);
	}

}
