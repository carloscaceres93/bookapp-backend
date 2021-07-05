package com.titamedia.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "reserva", catalog = "titamediapp_db")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReserva;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_libro", nullable = false)
	private Libro libro;
	
	@NotNull(message = "debe especificar la cantidad de libros a reservar")
	@Column(nullable = false)
	private Integer cantidad;
	
	@NotNull(message = "debe especificar la fecha de reservación del libro")
	@Column(nullable = false)
	private LocalDate fechareservacion;

	
	@NotNull(message = "debe especificar la fecha de devolución del libro")
	@Column(nullable = false)
	private LocalDate fechaDevolucion;

	@Column
	private Boolean estado;
}
