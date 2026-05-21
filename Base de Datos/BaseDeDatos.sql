/*
==============================================================================================================================================
CREAR BASE DE DATOS
==============================================================================================================================================
*/

DROP DATABASE IF EXISTS `inventario`;
CREATE DATABASE IF NOT EXISTS `inventario`;
USE `inventario`;

DROP TABLE IF EXISTS movimiento;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS alerta_stock;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS ubicacion;
DROP TABLE IF EXISTS datos_material;


/*
==============================================================================================================================================
CREAR TABLAS
==============================================================================================================================================
*/


CREATE TABLE IF NOT EXISTS ubicacion(
	id_ubicacion INT,
    armario INT,
    balda INT,
    cajon INT,
    descripcion VARCHAR(50),
    PRIMARY KEY(id_ubicacion)
);

CREATE TABLE IF NOT EXISTS datos_material(
	nombre VARCHAR(30) PRIMARY KEY,
    cantidad INT,
    stock_minimo INT,
    categoria ENUM("HARDWARE","HERRAMIENTA","FUNGIBLE","CUADERNO")
);

CREATE TABLE IF NOT EXISTS material(
	id_material INT AUTO_INCREMENT,
    nombre VARCHAR(30),
    descripcion VARCHAR(60),
    estado ENUM("DISPONIBLE","PRESTADO","EN_REPARACION","RETIRADO"),
    id_ubicacion INT,
    PRIMARY KEY(id_material),
    FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion),
    FOREIGN KEY (nombre) REFERENCES datos_material(nombre)
);

CREATE TABLE IF NOT EXISTS alerta_stock (
	id_alerta INT auto_increment PRIMARY KEY,
	nombre_material VARCHAR(30),
	fecha date,
	mensaje VARCHAR(60),
	resuelta boolean
    -- ,FOREIGN KEY (nombre_material) REFERENCES material(nombre)
);
CREATE TABLE IF NOT EXISTS usuario(
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20),
	apellidos VARCHAR(30),
	email VARCHAR(30) UNIQUE,
	contraseña VARCHAR(255),
	rol ENUM ("profesor", "administrador"),
	activo BOOLEAN,
	fecha_creacion DATE
);
CREATE TABLE IF NOT EXISTS movimiento(
	id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
	id_usuario INT,
	id_material INT,
	fecha DATE,
	observacion VARCHAR(60),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
	FOREIGN KEY (id_material) REFERENCES material(id_material)
);


/*
==============================================================================================================================================
INSERTAR DATOS
==============================================================================================================================================
*/


INSERT INTO ubicacion VALUES

    -- Baldas independientes
    (2001,NULL,2001,NULL,"Balda B001"),
    (2002,NULL,2002,NULL,"Balda B002"),
    (2003,NULL,2003,NULL,"Balda B003"),
    (2004,NULL,2004,NULL,"Balda B004"),
    (2005,NULL,2005,NULL,"Balda B005"),
    (2006,NULL,2006,NULL,"Balda B006"),
    (2007,NULL,2007,NULL,"Balda B007"),
    (2008,NULL,2008,NULL,"Balda B008"),
    (2009,NULL,2009,NULL,"Balda B009"),
    (2010,NULL,2010,NULL,"Balda B010"),
    (2011,NULL,2011,NULL,"Balda B011"),
    (2012,NULL,2012,NULL,"Balda B012"),
    (2013,NULL,2013,NULL,"Balda B013"),
    (2014,NULL,2014,NULL,"Balda B014"),
    (2015,NULL,2015,NULL,"Balda B015"),
    (2016,NULL,2016,NULL,"Balda B016"),
    (2017,NULL,2017,NULL,"Balda B017"),
    (2018,NULL,2018,NULL,"Balda B018"),
    
	-- Baldas dentro de armarios
    -- Armario A1
	(2101,11,2101,NULL,"Balda B101"),
    (2102,11,2102,NULL,"Balda B102"),
    (2103,11,2103,NULL,"Balda B103"),
    (2104,11,2104,NULL,"Balda B104"),
    (2105,11,2105,NULL,"Balda B105"),
    (2106,11,2106,NULL,"Balda B106"),
    (2107,11,2107,NULL,"Balda B107"),
    (2108,11,2108,NULL,"Balda B108"),
    (2109,11,2109,NULL,"Balda B109"),
    (2110,11,2110,NULL,"Balda B110"),
    (2111,11,2111,NULL,"Balda B111"),
    (2112,11,2112,NULL,"Balda B112"),
    -- Armario A2
    (2201,12,2201,NULL,"Balda B201"),
    (2202,12,2202,NULL,"Balda B202"),
    -- Armario A3
    (2301,13,2301,NULL,"Balda B301"),
    (2302,13,2302,NULL,"Balda B302"),
    -- Armario A4
    (2401,14,2401,NULL,"Balda B401"),
    (2402,14,2402,NULL,"Balda B402"),
    -- Armario A5
    (2501,15,2501,NULL,"Balda B501"),
    (2502,15,2502,NULL,"Balda B502"),
    (2503,15,2503,NULL,"Balda B503"),
    (2504,15,2504,NULL,"Balda B504"),
    (2505,15,2505,NULL,"Balda B505"),
    (2506,15,2506,NULL,"Balda B506"),
    (2507,15,2507,NULL,"Balda B507"),
    (2508,15,2508,NULL,"Balda B508"),
    (2509,15,2509,NULL,"Balda B509"),
    (2510,15,2510,NULL,"Balda B510"),
    (2511,15,2511,NULL,"Balda B511"),
    (2512,15,2512,NULL,"Balda B512"),
    
    -- Cajones en baldas
    -- Balda B002
    (300201,NULL,2002,300201,"Cajon C00201"),
    (300202,NULL,2002,300202,"Cajon C00202"),
    (300203,NULL,2002,300203,"Cajon C00203"),
    (300204,NULL,2002,300204,"Cajon C00204"),
    (300205,NULL,2002,300205,"Cajon C00205"),
    (300206,NULL,2002,300206,"Cajon C00206"),
    -- Balda B003
    (300301,NULL,2003,300301,"Cajon C00301"),
    -- Balda B006
    (300601,NULL,2006,300601,"Cajon C00601"),
    (300602,NULL,2006,300602,"Cajon C00602"),
    (300603,NULL,2006,300603,"Cajon C00603"),
    (300604,NULL,2006,300604,"Cajon C00604"),
    (300605,NULL,2006,300605,"Cajon C00605"),
    (300606,NULL,2006,300606,"Cajon C00606"),
    -- Balda B007
    (300701,NULL,2007,300701,"Cajon C00701"),
    -- Balda B008
    (300801,NULL,2008,300801,"Cajon C00801"),
    (300802,NULL,2008,300802,"Cajon C00802"),
    (300803,NULL,2008,300803,"Cajon C00803"),
    (300804,NULL,2008,300804,"Cajon C00804"),
    (300805,NULL,2008,300805,"Cajon C00805"),
    -- Balda B009
    (300901,NULL,2009,300901,"Cajon C00901"),
    (300902,NULL,2009,300902,"Cajon C00902"),
    -- Balda B010
    (301001,NULL,2010,301001,"Cajon C01001"),
    (301002,NULL,2010,301002,"Cajon C01002"),
    (301003,NULL,2010,301003,"Cajon C01003"),
    (301004,NULL,2010,301004,"Cajon C01004"),
    -- Balda B013
    (301301,NULL,2013,301301,"Cajon C01301"),
    (301302,NULL,2013,301302,"Cajon C01302"),
    -- Balda B014
    (301401,NULL,2014,301401,"Cajon C01401"),
    (301402,NULL,2014,301402,"Cajon C01402"),
    -- Balda B016
    (301601,NULL,2016,301601,"Cajon C01601"),
    (301602,NULL,2016,301602,"Cajon C01602"),
    (301603,NULL,2016,301603,"Cajon C01603"),
    (301604,NULL,2016,301604,"Cajon C01604"),
    (301605,NULL,2016,301605,"Cajon C01605"),
    -- Balda B017
    (301701,NULL,2017,301701,"Cajon C01701"),
    -- Balda B018
    (301801,NULL,2018,301801,"Cajon C01801"),
    (301802,NULL,2018,301802,"Cajon C01802")
;

INSERT INTO datos_material VALUES	-- ESTO MEJOR HACERLO CON UN PROCEDIMIENTO QUE SE EJECUTE AL FINAL DE LA BASE, Y CON UN TRIGGER

	("Manual",3,1,"CUADERNO"),
    
    ("Libro DAM",8,3,"CUADERNO"),
    
    ("Teclado USB",2,1,"HARDWARE"),
    
    ("Pistola de silicona",1,1,"HERRAMIENTA"),
    
    ("Mascara",1,1,"HERRAMIENTA"),
    
    ("Portatil",1,1,"HARDWARE"),
    
    ("Tinta",1,1,"FUNGIBLE"),
    
    ("Tornillos",3,2,"FUNGIBLE"),
    
    ("Cable ethernet",1,1,"HARDWARE")
;

INSERT INTO material VALUES

    (1,"Manual","Manual de Word 2007","DISPONIBLE",2001),
    (2,"Manual","Manual de Word 2007","DISPONIBLE",2001),
    (3,"Manual","Manual de Word 2007","DISPONIBLE",2001),
    
    (4,"Libro DAM","Libro de texto 1ºDAM 2015","DISPONIBLE",2003),
    (5,"Libro DAM","Libro de texto 1ºDAM 2015","DISPONIBLE",2003),
    (6,"Libro DAM","Libro de texto 1ºDAM 2015","DISPONIBLE",2003),
    (7,"Libro DAM","Libro de texto 1ºDAM 2015","DISPONIBLE",2003),
    (8,"Libro DAM","Libro de texto 1ºDAM 2015","DISPONIBLE",2003),
    (9,"Libro DAM","Libro de texto 1ºDAM 2015","DISPONIBLE",2003),
    (10,"Libro DAM","Libro de texto 1ºDAM 2015","DISPONIBLE",2003),
    (11,"Libro DAM","Libro de texto 1ºDAM 2015","DISPONIBLE",2003),
    
    (12,"Teclado USB","Teclado con conexión USB","DISPONIBLE",300901),
    (13,"Teclado USB","Teclado con conexión USB", "DISPONIBLE",300901),
    
    (14,"Pistola de silicona","Pistola de silicona","DISPONIBLE",301604),
    
    (15,"Mascara","Proteccion para los ojos","DISPONIBLE",301601),
    
    (16,"Portatil","Portatil","DISPONIBLE",2012),
    
    (17,"Tinta","Tinta para la impresora","DISPONIBLE",2202),
    
    (18,"Tornillos","Caja de tornillos pequeños","DISPONIBLE",2201),
    (19,"Tornillos","Caja de tornillos pequeños","DISPONIBLE",2201),
    (20,"Tornillos","Caja de tornillos pequeños","DISPONIBLE",2201),
    
    (21,"Cable ethernet","Cable ethernet de 1 metro","DISPONIBLE",301603)
;

INSERT INTO usuario VALUES
	(1,"Roberto","Macho Gonzalez","robermach@gmail.com",1234,"profesor",true,"2026-04-28"),
    (2,"Pedro","Sanchez","pedrosanxe@gmail.com",4321,"profesor",true,"2018-10-15"),
    (3,"Hugo","Fernandez","hfer@gmail.com",4132,"administrador",true,"2026-05-07")
;


/*
==============================================================================================================================================
VARIABLES
==============================================================================================================================================
*/

SET @id_usuario=NULL;
SET @modo_actualizacion=FALSE;

-- El modo actualización sirve para permitir (FALSE) o bloquear el trigger movimiento (TRUE).
-- Si está en FALSE, la tabla movimiento no registrará nada
-- Esto sirve para usar actualizarCantidad() sin que se genere un montón de filas en la tabla movimiento


/*
==============================================================================================================================================
PROCEDIMIENTOS
==============================================================================================================================================
*/


-- Este procedimiento se tiene que utilizar desde la aplicación.
-- Recibe el identificador del usuario que esté utilizando la aplicación,
-- y mete ese identificador dentro de la variable local @id_usuario

DELIMITER //
CREATE PROCEDURE definirIdUsuario(IN id INT)
READS SQL DATA
BEGIN
	SELECT id
    INTO @id_usuario;
END //
DELIMITER ;


-- INSERTAR NUEVOS TIPOS DE MATERIALES EN datos_material


/*
==============================================================================================================================================
TRIGGERS
==============================================================================================================================================
*/


-- Este TRIGGER registra actualizaciones en dentro de la tabla "material"
-- Inserta una fila en la tabla "movimiento" con el id del usuario, el id del material,
-- la fecha actual y un comentario con lo que se ha modificado.

DELIMITER //
CREATE TRIGGER trg_movimiento
BEFORE UPDATE ON material
FOR EACH ROW
BEGIN
	DECLARE observaciones VARCHAR(80);
	SET observaciones = 'Se ha modificado : ';
		IF NEW.id_ubicacion!=OLD.id_ubicacion THEN SET observaciones = concat(observaciones,'id_ubicacion ');END IF;
        IF NEW.estado!=OLD.estado THEN SET observaciones = concat(observaciones,'estado ');END IF;
        IF NEW.descripcion!=OLD.descripcion THEN SET observaciones = concat(observaciones,'descripcion ');END IF;
        IF NEW.nombre!=OLD.nombre THEN SET observaciones = concat(observaciones,'nombre ');END IF;
		INSERT INTO movimiento(id_usuario,id_material,fecha,observacion)
		VALUES(@id_usuario,NEW.id_material,curdate(),observaciones);
END //
DELIMITER ;

-- Este TRIGGER se activa cuando se elimina una fila de la tabla "material"
-- Si el nuevo número de materiales con un mismo nombre, es menor al stock_minimo
-- registrado en la fila "datos_material" correspondiente, se inserta una nueva
-- fila en la tabla "alerta_stock"

DELIMITER //
CREATE TRIGGER trg_alerta_stock
AFTER DELETE ON material
FOR EACH ROW
BEGIN
	DECLARE mensajes VARCHAR(60);
    DECLARE diferencia INT;
    DECLARE nombreMaterial VARCHAR(30);
    DECLARE cantidadActual INT;
    DECLARE stockMin INT;
    SET nombreMaterial = OLD.nombre;
    SELECT count(*) INTO cantidadActual FROM material WHERE nombre=nombreMaterial AND estado="DISPONIBLE";
    SELECT stock_minimo INTO stockMin FROM datos_material WHERE nombre=nombreMaterial;
    UPDATE datos_material SET cantidad=cantidadActual WHERE nombre=nombreMaterial;
	IF(cantidadActual<stockMin) THEN
		SET diferencia = stockMin-cantidadActual;
		SET mensajes = concat('La diferencia entre cantidad y stock mínimo es de ',diferencia);
		INSERT INTO alerta_stock(nombre_material,fecha,mensaje,resuelta)
        VALUES(
			nombreMaterial,
            curdate(),
            mensajes,
            FALSE
        );
    END IF;
END //

CREATE TRIGGER trg_alerta_stock_up
AFTER UPDATE ON material
FOR EACH ROW
BEGIN
	DECLARE mensajes VARCHAR(60);
    DECLARE diferencia INT;
    DECLARE nombreMaterial VARCHAR(30);
    DECLARE cantidadActual INT;
    DECLARE stockMin INT;
    SET nombreMaterial = OLD.nombre;
    SELECT count(*) INTO cantidadActual FROM material WHERE nombre=nombreMaterial AND estado= "DISPONIBLE";
    SELECT stock_minimo INTO stockMin FROM datos_material WHERE nombre=nombreMaterial;
    UPDATE datos_material SET cantidad=cantidadActual WHERE nombre=nombreMaterial;
	IF(cantidadActual<stockMin) THEN
		SET diferencia = stockMin-cantidadActual;
		SET mensajes = concat('La diferencia entre cantidad y stock mínimo es de ',diferencia);
		INSERT INTO alerta_stock(nombre_material,fecha,mensaje,resuelta)
        VALUES(
			nombreMaterial,
            curdate(),
            mensajes,
            FALSE
        );
    END IF;
END//
    
    CREATE TRIGGER trg_comprobar_alerta_stock_up
AFTER UPDATE ON material
FOR EACH ROW
BEGIN
	DECLARE mensajes VARCHAR(60);
    DECLARE diferencia INT;
    DECLARE nombreMaterial VARCHAR(30);
    DECLARE cantidadActual INT;
    DECLARE stockMin INT;
    SET nombreMaterial = OLD.nombre;
    SELECT count(*) INTO cantidadActual FROM material WHERE nombre=nombreMaterial AND estado= "DISPONIBLE";
    SELECT stock_minimo INTO stockMin FROM datos_material WHERE nombre=nombreMaterial;
    UPDATE datos_material SET cantidad=cantidadActual WHERE nombre=nombreMaterial;
	IF(cantidadActual>=stockMin) THEN
		UPDATE alerta_stock SET resuelta=TRUE WHERE nombre_material=nombreMaterial;
    END IF;
    
END //

DELIMITER ;

-- 

DELIMITER //
CREATE TRIGGER actualizarCantidad -- FUNCIONA
AFTER INSERT ON material
FOR EACH ROW
BEGIN
    DECLARE newCantidad INT;
	-- Contar cuantos materiales hay con el mismo nombre
	SELECT count(*) INTO newCantidad FROM material WHERE nombre=new.nombre;
	-- Actualizar campo "cantidad" de esos materiales con el resultado obtenido
	UPDATE datos_material SET cantidad=newCantidad WHERE nombre=new.nombre;
END //

DELIMITER ;

UPDATE usuario SET contraseña = MD5(contraseña) WHERE id_usuario >0;