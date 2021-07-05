package com.titamedia.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.titamedia.model.Libro;
import com.titamedia.model.Reserva;
import com.titamedia.model.Usuario;

public interface IReservaService extends ICRUD<Reserva, Integer>{

	List<Reserva> findByUsuario(Usuario usuario) throws Exception;

	List<Reserva> findByLibro(Libro libro)throws Exception;
	
	Page<Reserva> listarPaginado(Pageable pageable)throws Exception;
	
	Reserva registarTransacional(Reserva reserva)throws Exception;
}
