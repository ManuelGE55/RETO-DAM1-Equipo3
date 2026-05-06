CREATE DATABASE IF NOT EXISTS inventario;
USE inventario;

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

DROP TABLE IF EXISTS ubicacion;
CREATE TABLE ubicacion(
	id_ubicacion INT AUTO_INCREMENT,
    armario INT,
    balda INT,
    cajon INT,
    descripcion VARCHAR(50),
    PRIMARY KEY(id_ubicacion)
);