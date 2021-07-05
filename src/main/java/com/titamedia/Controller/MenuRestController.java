package com.titamedia.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.titamedia.model.Menu;
import com.titamedia.service.IMenuService;

@RestController
@RequestMapping("/api/menus")
public class MenuRestController {
	
	@Autowired
	private IMenuService menuService;
	
	@GetMapping
	public ResponseEntity<List<Menu>> listar() throws Exception{
		List<Menu> menus = new ArrayList<>();
		menus = menuService.listar();
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<List<Menu>> listarPorUsuario(@RequestBody String nombre) throws Exception{
		List<Menu> menus = new ArrayList<>();
		menus = menuService.listarMenuPorUsuario(nombre);
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}

}
