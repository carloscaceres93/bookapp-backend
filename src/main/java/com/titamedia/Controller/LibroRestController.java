package com.titamedia.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.titamedia.model.Libro;
import com.titamedia.service.ILibroService;

@RestController
@RequestMapping("/api/libros")
public class LibroRestController {

	@Autowired
	private ILibroService libroService;


	@GetMapping
	public ResponseEntity<List<Libro>> listar() throws Exception {
		List<Libro> Libros = libroService.listar();
		return new ResponseEntity<List<Libro>>(Libros, HttpStatus.OK);
	}
	

	@GetMapping("idCategoria/{id}")
	public ResponseEntity<List<Libro>> listarPorCategoria(@PathVariable("id") Integer id) throws Exception {
		List<Libro> Libros = libroService.listarPorCategoria(id);
		return new ResponseEntity<List<Libro>>(Libros, HttpStatus.OK);
	}


	@GetMapping("/pageable")
	public ResponseEntity<Page<Libro>> listarPageable(Pageable pageable) throws Exception {
		Page<Libro> Libros = libroService.listarPaginado(pageable);
		return new ResponseEntity<Page<Libro>>(Libros, HttpStatus.OK);
	}

	@GetMapping("/libros-disponibles")
	public ResponseEntity<Page<Libro>> listarLibrosDisponibles(Pageable pageable) throws Exception {
		Page<Libro> Libros = libroService.listarLibrosDisponibles(pageable);
		return new ResponseEntity<Page<Libro>>(Libros, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public EntityModel<Libro> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Libro obj = libroService.listarPorId(id);

		EntityModel<Libro> recurso = EntityModel.of(obj);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link.withRel("Libro-recurso"));

		return recurso;
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@PostMapping
	public ResponseEntity<Libro> registrar(@Valid @RequestBody Libro libro) throws Exception {

		Libro obj = libroService.registrar(libro);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdLibro())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@PutMapping
	public ResponseEntity<Libro> modificar(@Valid @RequestBody Libro libro) throws Exception {
		Libro obj = libroService.modificar(libro);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdLibro())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		libroService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
