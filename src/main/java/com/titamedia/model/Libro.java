package com.titamedia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "libro", catalog = "titamediapp_db")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLibro;

	@NotNull(message = "Campo 'nombre' obligatorio")
	@Size(min =3, message = "El nombre debe contener minimo 7 letras")
	@Column(nullable = false, length = 100)
	private String nombre;

	@Size(min = 3, message = "El Autor debe contener minimo 3 letras")
	@NotNull(message = "Campo 'Autor' obligatorio")
	@Column(nullable = false, length = 100)
	private String autor;

	@NotNull(message = "Campo 'Paginas' obligatorio")
	@Column(nullable = false)
	private Integer paginas;

	@NotNull(message = "Campo 'Cantidad disponible' obligatorio")
	@Column(nullable = false)
	private Integer cantidadDisponible;

	@Column(nullable = true)
	private Integer cantidadReservada;

	@NotNull(message = "Campo 'categoria' obligatorio")
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;
	
	@NotNull(message = "Campo 'tarifa' obligatorio")
	@ManyToOne
	@JoinColumn(name = "id_tarifa", nullable = false)
	private Tarifa tarifa;
	
	@Column
	private Boolean estado;

}
