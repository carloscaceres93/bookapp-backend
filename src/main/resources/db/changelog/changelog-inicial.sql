-- liquibase formatted sql

-- changeset hiko:1
-- comment: creacion de tablas
CREATE TABLE categoria(
  id_categoria INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(100) NULL DEFAULT NULL,
  estado BOOLEAN NOT NULL DEFAULT 1
);

CREATE TABLE libro (
  id_libro INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  autor VARCHAR(100) NOT NULL,
  paginas INT NOT NULL,
  cantidad_disponible INT NOT NULL,
  cantidad_reservada INT NULL DEFAULT NULL,
  estado BOOLEAN NOT NULL DEFAULT 1,
  tarifa VARCHAR(255) NOT NULL,
  id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria)
    REFERENCES categoria (id_categoria)
);

CREATE TABLE usuario(
  id_usuario INT PRIMARY KEY AUTO_INCREMENT,
  usuario VARCHAR(255) NOT NULL UNIQUE, 
  clave VARCHAR(255) NOT NULL,
  estado BOOLEAN NOT NULL DEFAULT 1
);

CREATE TABLE reserva (
  id_reserva INT PRIMARY KEY AUTO_INCREMENT,
  cantidad INT NOT NULL,
  estado BOOLEAN NOT NULL DEFAULT 1,
  fecha_devolucion DATE NOT NULL,
  fechareservacion DATE NOT NULL,
  id_libro INT NOT NULL,
	FOREIGN KEY (id_libro)
    REFERENCES libro (id_libro),
  id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario)
    REFERENCES usuario (id_usuario)
);

CREATE TABLE rol (
  id_rol INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
  descripcion VARCHAR(255) NOT NULL
);

INSERT INTO rol(id_rol, nombre, descripcion) VALUES
	(1, 'ROLE_ADMINISTRADOR','Acceso total'),
	(2, 'ROLE_CLIENTE', 'Reserva de libros');

CREATE TABLE usuario_rol (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  FOREIGN KEY (id_usuario)
    REFERENCES usuario (id_usuario),
  id_rol INT NOT NULL DEFAULT 2,
    FOREIGN KEY (id_rol)
    REFERENCES rol (id_rol)
);

CREATE TABLE menu (
  id_menu INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  icono VARCHAR(20),
  url VARCHAR(100)
);

CREATE TABLE menu_rol (
  id_menu INT NOT NULL,
	FOREIGN KEY (id_menu)
    REFERENCES menu (id_menu),
  id_rol INT NOT NULL,
    FOREIGN KEY (id_rol)
    REFERENCES rol (id_rol),
  primary key(id_menu,id_rol)
);


INSERT INTO categoria(descripcion,nombre, estado) VALUES
	('Libros especializados en conocimiento y aprendizaje', 'TEXTOS ESCOLARES',1),
	('Libros para el aprendizaje de un carrera en especifica', 'PROFESIONALES',1),
	('Libros para pasar un rato agradable', 'LITERATURA',1),
	('Libros para dar tus primeros pasos al conocimiento', 'INFANTILES',1),
	('Libros de interes genereal', 'INTERES GENERAL',1),
	('Libros de interes para jovenes', 'JUVENIL',1),
	('Literatura explicada mediante viñetas', 'COMICS Y NOVELA GRIAFICA',1);
    
INSERT INTO usuario(estado,clave, usuario) VALUES
	(1, '$2a$10$xv2vd0LuokUSfHgr0cN8h.JjOugpMLGk46zeMvqH9ItacGrHhO.O2', 'admin@gmail.com'),
	(1, '$2a$10$EHcpisP3er1KCWgzsFSBCeKETeiAWEK9i9nr3GdlG.n3Q8NAqcGhm', 'usuarioprueba@gmail.com');
    
INSERT INTO usuario_rol(id_usuario, id_rol) VALUES
	(1,1),
    (2,2);
    
INSERT INTO libro(nombre, autor, paginas, cantidad_disponible, cantidad_reservada, tarifa, id_categoria, estado) values
	('Juego de Tronos. Canción de hielo y fuego I', 'George R. R. Martin', 789, 90, 0, 50000, 3, 1),    
	('El viejo y el mar', 'Ernest Hemingway', 200, 40, 0, 30000, 3, 1),
	('Listo para cualquier cosa', 'Keiko Kasza', 200, 40, 0, 15000, 4, 1),
	('El país mas hermoso del mundo', 'David Sánchez Juliao', 200, 40, 0, 20000, 1, 1),
	('Zeus y el ascenso de los olimpicos', 'Ryan Foley', 200, 40, 0, 30000, 7, 1);

-- changeset hiko:2

INSERT INTO menu(nombre,icono,url) VALUES
	('inicio','home','/pages/inicio'),
	('Libros','book','/pages/libro'),
    ('Reservas','local_library','/pages/reserva'),
    ('Usuarios','manage_accounts','/pages/usuario'),
    ('Categorias','category','/pages/categoria');
