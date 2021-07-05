package com.titamedia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name ="categoria",catalog = "titamediapp_db")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;
	
	@Size(min = 7, message = "El nombre debe contener minimo 7 letras")
	@NotNull(message = "Campo 'nombre' obligatorio")
	@Column(nullable = false, length = 100)
	private String nombre;
	
	@Column(nullable = true, length = 100)
	private String descripcion;
	
	@Column
	private Boolean estado;
}
