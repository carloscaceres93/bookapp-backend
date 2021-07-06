package com.titamedia.service;

import java.util.List;

import com.titamedia.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario, Integer> {

	List<Usuario> findByEstado(Boolean estado)throws Exception;
	Usuario findOneByUsername(String username)throws Exception;	
}
