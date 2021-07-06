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
@Table(catalog = "titamediapp_db")
public class Tarifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTarifa;

	@Size(min = 4, message = "El nombre debe contener minimo 4 letras")
	@NotNull(message = "Campo 'nombre' obligatorio")
	@Column(nullable = false, length = 100)
	private String nombre;

	@NotNull(message = "Campo 'nombre' obligatorio")
	@Column(nullable = false, length = 100)
	private Double precio;

	@Column(nullable = true, length = 100)
	private String descripcion;

	@Column
	private Boolean estado;
}