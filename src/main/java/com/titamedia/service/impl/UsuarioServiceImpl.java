package com.titamedia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.titamedia.exception.ModeloNotFoundException;
import com.titamedia.model.Rol;
import com.titamedia.model.Usuario;
import com.titamedia.model.UsuarioRol;
import com.titamedia.repository.IRolRepository;
import com.titamedia.repository.IUsuarioRepository;
import com.titamedia.repository.IUsuarioRolRespository;
import com.titamedia.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepo;
	
	@Autowired
	private IUsuarioRolRespository usuarioRolRepo;
	
	@Autowired
	private IRolRepository rolRepo;

	@Transactional
	@Override
	public Usuario registrar(Usuario t) throws Exception {
		Optional<Usuario> validarUaurio = usuarioRepo.validarExisteUusario(t.getUsername());
		UsuarioRol usuarioRol = new UsuarioRol();
		Usuario usuario = new Usuario();
		Optional<Rol> rol = rolRepo.findById(Rol.ID.CLIENTE); 
		
		
		if (validarUaurio.isPresent()) {
			throw new ModeloNotFoundException("El usuario ya existe en la base de datos");
		}
		
		if(t.getPassword().isEmpty()) {
			throw new ModeloNotFoundException("La contraseña es obligatoria");
		}

		usuario = usuarioRepo.save(t);
		
		usuarioRol.setUsuario(usuario);
		usuarioRol.setRol(rol.get());
		usuarioRolRepo.save(usuarioRol);
		
		return usuario;
	}

	@Override
	public Usuario modificar(Usuario t) throws Exception {
		Optional<Usuario> usuario = usuarioRepo.findById(t.getIdUsuario());

		if (!usuario.isPresent()) {
			throw new ModeloNotFoundException("El usuario no existe en la base de datos");
		}
		return usuarioRepo.save(t);
	}

	@Override
	public List<Usuario> listar() throws Exception {
		List<Usuario> usuarios = usuarioRepo.findAll();

		if (usuarios.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron usuarios disponibles");
		}
		return usuarios;
	}

	@Override
	public Usuario listarPorId(Integer id) throws Exception {
		Optional<Usuario> usuario = usuarioRepo.findById(id);

		if (!usuario.isPresent()) {
			throw new ModeloNotFoundException("El usuario no existe en la base de datos");
		}
		return usuario.get();
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		usuarioRepo.deleteById(id);
	}

	@Override
	public List<Usuario> findByEstado(Boolean estado) throws Exception {
		List<Usuario> usuarios = usuarioRepo.findByEstado(estado);

		if (usuarios.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron datos disponibles");
		}
		return usuarios;
	}

	@Override
	public Usuario deshabilitarUsuario(Usuario usuario) throws Exception {
		Optional<Usuario> user = usuarioRepo.findById(usuario.getIdUsuario());
		
		if(user.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron datos disponibles");
		}
		
		user.get().setEstado(false);
		usuarioRepo.save(user.get());
		return user.get();
	}
}
