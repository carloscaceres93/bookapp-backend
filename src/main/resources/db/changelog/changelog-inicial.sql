-- liquibase formatted sql

-- changeset hiko:1
-- comment: creacion de tablas
CREATE TABLE categoria(
  id_categoria INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(100) NULL DEFAULT NULL,
  estado BOOLEAN NOT NULL DEFAULT 1
);

CREATE TABLE tarifa(
	id_tarifa INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
    precio DECIMAL NOT NULL,
	descripcion VARCHAR(100) NULL DEFAULT NULL,
	estado BOOLEAN NOT NULL DEFAULT 1
  );
  
CREATE TABLE libro (
  id_libro INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  autor VARCHAR(100) NOT NULL,
  paginas INT NOT NULL,
  cantidad_disponible INT NOT NULL,
  cantidad_reservada INT NULL DEFAULT 0,
  estado BOOLEAN NOT NULL DEFAULT 1,
  id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria)
    REFERENCES categoria (id_categoria),
  id_tarifa INT NOT NULL,
	FOREIGN KEY (id_tarifa)
    REFERENCES tarifa (id_tarifa)
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

INSERT INTO rol(id_rol, nombre, descripcion) VALUES
	(1, 'ADMINISTRADOR','Acceso total'),
	(2, 'CLIENTE', 'Reserva de libros');

  INSERT INTO tarifa(nombre, precio,descripcion,estado) VALUES 
   ('tarifa 1', 10000,'tarifa 1', true),
   ('tarifa 2', 20000,'tarifa 2', true),
   ('tarifa 3', 30000,'tarifa 3', true),
   ('tarifa 4', 40000,'tarifa 4', true),
   ('tarifa 5', 50000,'tarifa 5', true),
   ('tarifa 6', 60000,'tarifa 6', true),
   ('tarifa 7', 70000,'tarifa 7', true),
   ('tarifa 8', 80000,'tarifa 8', true),
   ('tarifa 9', 90000,'tarifa 9', true);

INSERT INTO categoria(descripcion,nombre, estado) VALUES
	('Libros especializados en conocimiento y aprendizaje', 'TEXTOS ESCOLARES',1),
	('Libros para el aprendizaje de un carrera en especifica', 'PROFESIONALES',1),
	('Libros para pasar un rato agradable', 'LITERATURA',1),
	('Libros para dar tus primeros pasos al conocimiento', 'INFANTILES',1),
	('Libros de interes genereal', 'INTERES GENERAL',1),
	('Libros de interes para jovenes', 'JUVENIL',1),
	('Literatura explicada mediante viñetas', 'COMICS Y NOVELA GRIAFICA',1);
    
insert into usuario(usuario, clave , estado)values
	('admistrador@gmail.com','$2a$10$wSUzu47QdDI3cB1paE11s.R0NxpSrkXkyOw4RlKoqkgERMBVRkDzS', true);
    
INSERT INTO usuario_rol(id_usuario, id_rol) VALUES
	(1,1);
    
INSERT INTO libro(nombre, autor, paginas, cantidad_disponible, cantidad_reservada, id_tarifa, id_categoria, estado) values
	('Juego de Tronos. Canción de hielo y fuego I', 'George R. R. Martin', 789, 90, 0, 1, 3, 1),    
	('El viejo y el mar', 'Ernest Hemingway', 200, 40, 0, 2, 3, 1),
	('Listo para cualquier cosa', 'Keiko Kasza', 200, 40, 0, 3, 4, 1),
	('El país mas hermoso del mundo', 'David Sánchez Juliao', 200, 40, 0, 4, 1, 1),
    ('libro prueba 1', 'Ryan Foley', 200, 40, 0, 5, 2, 1),
    ('libro prueba 2', 'Ryan Foley', 200, 40, 0, 5, 2, 1),
    ('libro prueba 3', 'Ryan Foley', 200, 40, 0, 5, 4, 1),
    ('libro prueba 4', 'Ryan Foley', 200, 40, 0, 5, 5, 1),
    ('libro prueba 5', 'Ryan Foley', 200, 40, 0, 5, 6, 1),
	('Zeus y el ascenso de los olimpicos', 'Ryan Foley', 200, 40, 0, 5, 7, 1);

INSERT INTO menu(nombre,icono,url) VALUES
	('inicio','home','/pages/inicio'),
	('Libros','book','/pages/libro'),
    ('Categorias','category','/pages/categoria'),
    ('Reservas','local_library','/pages/reserva'),
    ('Gestionar libros','library_books','/pages/gestionar-libro'),
    ('Gestionar tarifas','paid','/pages/gestionar-tarifa');
    
    
INSERT INTO menu_rol(id_menu, id_rol) VALUES
	(1,1),
    (1,2),
    (2,2),
    (3,1),
    (4,1),
    (5,1),
    (6,1);
    
    
    
