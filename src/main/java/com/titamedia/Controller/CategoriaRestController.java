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

import com.titamedia.model.Categoria;
import com.titamedia.service.ICategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {

	@Autowired
	private ICategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> listar() throws Exception {
		List<Categoria> Categorias = categoriaService.listar();
		return new ResponseEntity<List<Categoria>>(Categorias, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@GetMapping("/pageable")
	public ResponseEntity<Page<Categoria>> listarPageable(Pageable pageable) throws Exception {
		Page<Categoria> categorias = categoriaService.listarPageable(pageable);
		return new ResponseEntity<Page<Categoria>>(categorias, HttpStatus.OK);
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@GetMapping("/{id}")
	public EntityModel<Categoria> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Categoria obj = categoriaService.listarPorId(id);

		EntityModel<Categoria> recurso = EntityModel.of(obj);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link.withRel("Categoria-recurso"));

		return recurso;
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@PostMapping
	public ResponseEntity<Categoria> registrar(@Valid @RequestBody Categoria categoria) throws Exception {

		Categoria obj = categoriaService.registrar(categoria);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdCategoria()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@PutMapping
	public ResponseEntity<Categoria> modificar(@Valid @RequestBody Categoria categoria) throws Exception {
		Categoria obj = categoriaService.modificar(categoria);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdCategoria()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		categoriaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
