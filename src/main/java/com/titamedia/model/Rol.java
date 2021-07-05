package com.titamedia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "rol", catalog = "titamediapp_db")
public class Rol {

	@Id
	private Integer idRol;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	public interface ID {
		int ADMIN = 1;
		int CLIENTE = 2;
	}

	public Rol() {
	}

	public Rol(Integer idRol) {
		this.idRol = idRol;
	}
}
