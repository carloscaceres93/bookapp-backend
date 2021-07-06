package com.titamedia.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.titamedia.model.Usuario;
import com.titamedia.service.IUsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() throws Exception {
		List<Usuario> usuarios = usuarioService.listar();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@GetMapping("/estado")
	public ResponseEntity<List<Usuario>> listarPorEstado(Boolean estado) throws Exception{
		List<Usuario> usuarios = usuarioService.findByEstado(estado);
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public EntityModel<Usuario> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Usuario obj = usuarioService.listarPorId(id);

		EntityModel<Usuario> recurso = EntityModel.of(obj);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link.withRel("Reserva-recurso"));

		return recurso;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario) throws Exception {

		Usuario obj = usuarioService.registrar(usuario);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Usuario> modificar(@Valid @RequestBody Usuario usuario) throws Exception {
		Usuario obj = usuarioService.modificar(usuario);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/deshabilitar")
	public ResponseEntity<Void> deshabilitarUsuario(@Valid @RequestBody Usuario usuario) throws Exception {
		usuarioService.deshabilitarUsuario(usuario);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
