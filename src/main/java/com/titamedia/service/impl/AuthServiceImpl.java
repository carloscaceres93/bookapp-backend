package com.titamedia.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

	public boolean tieneAcceso(String path) {
		
		boolean rpta = false;

		String metodoRol = "";

		switch (path) {
		case "todos":
			metodoRol = "ADMINISTRADOR,CLIENTE";
			break;

		case "admin":
			metodoRol = "ADMINISTRADOR";
			break;
			
		case "user":
			metodoRol = "CLIENTE";
			break;

		}
		
		String metodoRoles[] = metodoRol.split(",");
		
		Authentication usuarioLogueado = SecurityContextHolder.getContext().getAuthentication();

		System.out.println(usuarioLogueado.getName());

		for (GrantedAuthority auth : usuarioLogueado.getAuthorities()) {
			String rolUser = auth.getAuthority();
			System.out.println(rolUser);

			for (String rolMet : metodoRoles) {
				if (rolUser.equalsIgnoreCase(rolMet)) {
					rpta = true;
				}
			}
		}
		
		return rpta;
	}
}
