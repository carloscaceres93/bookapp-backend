package com.titamedia.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.titamedia.exception.ModeloNotFoundException;
import com.titamedia.model.Libro;
import com.titamedia.model.Reserva;
import com.titamedia.model.Usuario;
import com.titamedia.repository.IGenericRepository;
import com.titamedia.repository.ILibroRepository;
import com.titamedia.repository.IReservaRepository;
import com.titamedia.repository.IUsuarioRepository;
import com.titamedia.service.IReservaService;

@Service
public class ReservaServiceImpl extends CRUDImpl<Reserva, Integer> implements IReservaService {

	@Autowired
	private IReservaRepository reservaRepo;

	@Autowired
	private ILibroRepository libroRepo;

	@Autowired
	private IUsuarioRepository usuarioRepo;

	@Override
	protected IGenericRepository<Reserva, Integer> getRepo() {
		return reservaRepo;
	}

	@Override
	public List<Reserva> findByUsuario(Usuario usuario) throws Exception {

		Optional<Usuario> usuarioOpt = usuarioRepo.findById(usuario.getIdUsuario());
		List<Reserva> reservas = reservaRepo.findByUsuario(usuarioOpt.get());

		if (!usuarioOpt.isPresent()) {
			throw new ModeloNotFoundException("El usuario no existe en la base de datos: " + usuario.getIdUsuario());
		}

		if (reservas.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron reservas disponibles");
		}

		return reservas;
	}

	@Override
	public List<Reserva> findByLibro(Libro libro) throws Exception {
		Optional<Libro> usuarioOpt = libroRepo.findById(libro.getIdLibro());
		List<Reserva> reservas = reservaRepo.findByLibro(usuarioOpt.get());

		if (!usuarioOpt.isPresent()) {
			throw new ModeloNotFoundException("El usuario no existe en la base de datos: " + libro.getIdLibro());
		}

		if (reservas.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron reservas disponibles");
		}

		return reservas;
	}

	@Override
	public Page<Reserva> listarPaginado(Pageable pageable) throws Exception {
		Page<Reserva> reservas = reservaRepo.findAll(pageable);
		
		if(reservas.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron reservas disponibles");
		}
		return reservas;
	}

	@Override
	public Reserva registarTransacional(Reserva reserva) throws Exception {
		Integer idLibro = reserva.getLibro().getIdLibro();
		Integer idUsuario = reserva.getUsuario().getIdUsuario();
		Integer numLibrosDisponibles = null;
		
		Optional<Libro> libro = libroRepo.findById(idLibro);
		Optional<Usuario> usuario = usuarioRepo.findById(idUsuario);
		numLibrosDisponibles = libro.get().getCantidadDisponible();
		
		if(!libro.isPresent()) {
			throw new ModeloNotFoundException("No se ha podido encontrar el libro");
		}
		
		if(!usuario.isPresent()) {
			throw new ModeloNotFoundException("No se ha podido encontrar el usuario");
		}
		
		if(reserva.getFechaDevolucion().compareTo(LocalDate.now())<= 0) {
			throw new ModeloNotFoundException("La fecha de devolución no puede ser menor a la fecha de reserva");
		}
		
		if(reserva.getCantidad() > numLibrosDisponibles) {
			throw new ModeloNotFoundException("No hay esa cantidad de libros disponibles");
		}
		
		libro.get().setCantidadDisponible(numLibrosDisponibles - reserva.getCantidad());
		libro.get().setCantidadReservada(libro.get().getCantidadReservada() + reserva.getCantidad());
		
		libroRepo.save(libro.get());
		
		return reservaRepo.save(reserva);
	}
}
