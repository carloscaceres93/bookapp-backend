package com.titamedia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.titamedia.model.Usuario;
import com.titamedia.repository.IUsuarioRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.findOneByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}

		UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), usuario.getEstado(), true, true, true,
				buscarRolePorUsuario(usuario));

		return ud;
	}

	private List<SimpleGrantedAuthority> buscarRolePorUsuario(Usuario usuario) {

		List<String> lstStrRole = usuarioDao.buscarRolePorUsuario(usuario.getUsername());

		List<SimpleGrantedAuthority> lstRole = new ArrayList<>(lstStrRole.size());

		lstStrRole.forEach(role -> {

			SimpleGrantedAuthority item = new SimpleGrantedAuthority(
					String.format(role.toUpperCase().trim()));
			lstRole.add(item);
		});

		return lstRole;
	}
}
