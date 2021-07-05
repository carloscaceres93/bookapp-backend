package com.titamedia.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.titamedia.model.Reserva;
import com.titamedia.service.IReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaRestController {

	@Autowired
	private IReservaService reservaService;

	@GetMapping("/pageable")
	public ResponseEntity<Page<Reserva>> listarPageable(Pageable pageable) throws Exception {
		Page<Reserva> reservas = reservaService.listarPaginado(pageable);
		return new ResponseEntity<Page<Reserva>>(reservas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public EntityModel<Reserva> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Reserva obj = reservaService.listarPorId(id);

		EntityModel<Reserva> recurso = EntityModel.of(obj);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link.withRel("Reserva-recurso"));

		return recurso;
	}

	@PostMapping
	public ResponseEntity<Reserva> registrar(@Valid @RequestBody Reserva reserva) throws Exception {

		Reserva obj = reservaService.registarTransacional(reserva);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdReserva())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Reserva> modificar(@Valid @RequestBody Reserva reserva) throws Exception {
		Reserva obj = reservaService.modificar(reserva);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdReserva())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		reservaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
