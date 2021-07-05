package com.titamedia.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.titamedia.model.Libro;
import com.titamedia.model.Reserva;
import com.titamedia.model.Usuario;

public interface IReservaRepository extends IGenericRepository<Reserva, Integer> {

	List<Reserva> findByUsuario(Usuario usuario);

	List<Reserva> findByLibro(Libro libro);
	
	@Query("FROM Reserva r WHERE r.estado = true")
	Page<Reserva> listarReservasDisponibles(Pageable pageable);
}
