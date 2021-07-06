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

import com.titamedia.model.Tarifa;
import com.titamedia.service.ITarifaService;

@RestController
@RequestMapping("/api/tarifas")
public class TarifaResController {

	@Autowired
	private ITarifaService tarifaService;

	@GetMapping
	public ResponseEntity<List<Tarifa>> listar() throws Exception {
		List<Tarifa> tarifas = tarifaService.listar();
		return new ResponseEntity<List<Tarifa>>(tarifas, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@GetMapping("/pageable")
	public ResponseEntity<Page<Tarifa>> listarPageable(Pageable pageable) throws Exception {
		Page<Tarifa> tarifas = tarifaService.listarPageable(pageable);
		return new ResponseEntity<Page<Tarifa>>(tarifas, HttpStatus.OK);
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@GetMapping("/{id}")
	public EntityModel<Tarifa> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Tarifa obj = tarifaService.listarPorId(id);

		EntityModel<Tarifa> recurso = EntityModel.of(obj);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link.withRel("tarifa-recurso"));

		return recurso;
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@PostMapping
	public ResponseEntity<Tarifa> registrar(@Valid @RequestBody Tarifa tarifa) throws Exception {

		Tarifa obj = tarifaService.registrar(tarifa);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdTarifa()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@PutMapping
	public ResponseEntity<Tarifa> modificar(@Valid @RequestBody Tarifa tarifa) throws Exception {
		Tarifa obj = tarifaService.modificar(tarifa);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdTarifa()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("@authServiceImpl.tieneAcceso('admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		tarifaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
