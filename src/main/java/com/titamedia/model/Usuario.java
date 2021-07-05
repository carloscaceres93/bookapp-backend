package com.titamedia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario", catalog = "titamediapp_db")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@NotNull(message = "campo 'usuario' oblogatorio")
	@Email(message = "el usuario debe ser un correo electronico valido")
	@Column(name = "usuario", nullable = false, unique = true)
	private String username;

	@Size(min = 8, message = "la clave debe contener minmo 8 caracteres")
	@NotNull(message = "campo 'clave' oblogatorio")
	@Column(name = "clave", nullable = false)
	private String password;

	@Column
	private Boolean estado;
}
