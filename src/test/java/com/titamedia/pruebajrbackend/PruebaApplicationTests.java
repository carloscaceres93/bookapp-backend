package com.titamedia.pruebajrbackend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.titamedia.model.Usuario;
import com.titamedia.repository.IUsuarioRepository;


@SpringBootTest
class PruebaApplicationTests {
	
	@Autowired
	private IUsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Test
	void verficarClave() {
		Usuario us = new Usuario();
		us.setUsername("mitocode21@gmail.com");
		us.setPassword(bcrypt.encode("12345678"));
		us.setEstado(true);
		
		Usuario retorno = repo.save(us);
		assertTrue(retorno.getPassword().equals(us.getPassword()));
	}
}
