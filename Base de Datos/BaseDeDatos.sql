CREATE DATABASE IF NOT EXISTS `inventario`;

USE `inventario`;

DROP TABLE IF EXISTS ubicacion;
CREATE TABLE ubicacion(
	id_ubicacion INT AUTO_INCREMENT,
    armario INT,
    balda INT,
    cajon INT,
    descripcion VARCHAR(50),
    PRIMARY KEY(id_ubicacion)
);

DROP TABLE IF EXISTS material;
CREATE TABLE material(
	id_material INT AUTO_INCREMENT,
    nombre VARCHAR(30),
    descripcion VARCHAR(60),
    cantidad INT,
    stock_minimo INT,
    categoria ENUM("Hardware","Herramienta","Fungible"),
    estado ENUM("Disponible","Prestado","En reparación","Retirado"),
    id_ubicacion INT,
    PRIMARY KEY(id_material),
    KEY (id_ubicacion)
); 

DROP TABLE IF EXISTS alerta_stock;
CREATE TABLE IF NOT EXISTS alerta_stock (
id_alerta INT auto_increment PRIMARY KEY,
id_material INT unique,
fehca date,
mensaje VARCHAR(60),
resuelta boolean,
FOREIGN KEY (id_material) REFERENCES material(id_material)
);

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario(
id_usuario INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(20),
apellidos VARCHAR(30),
email VARCHAR(20) UNIQUE,
contraseña VARCHAR(20),
rol ENUM ("profesor", "administrador"),
activo BOOLEAN,
fecha_creacion DATE
);

DROP TABLE IF EXISTS movimiento;
CREATE TABLE movimiento(
id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
id_usuario INT,
id_material INT,
cantidad INT,
fecha DATE,
observación VARCHAR(60),
FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
FOREIGN KEY (id_material) REFERENCES material(id_material)
);



