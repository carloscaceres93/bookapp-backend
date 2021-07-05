package com.titamedia.service;

import java.util.List;

import com.titamedia.model.Menu;

public interface IMenuService extends ICRUD<Menu, Integer>{
	
	List<Menu> listarMenuPorUsuario(String nombre) throws Exception;

}
