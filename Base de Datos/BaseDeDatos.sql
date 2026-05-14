/*
Crear base de datos
*/

DROP DATABASE IF EXISTS `inventario`;
CREATE DATABASE IF NOT EXISTS `inventario`;
USE `inventario`;

DROP TABLE IF EXISTS movimiento;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS alerta_stock;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS ubicacion;


/*
CREAR TABLAS
*/


CREATE TABLE ubicacion(
	id_ubicacion INT,
    armario INT,
    balda INT,
    cajon INT,
    descripcion VARCHAR(50),
    PRIMARY KEY(id_ubicacion)
);
CREATE TABLE material(
	id_material INT AUTO_INCREMENT,
    nombre VARCHAR(30),
    descripcion VARCHAR(60),
    cantidad INT,
    stock_minimo INT,
    categoria ENUM("Hardware","Herramienta","Fungible","Cuaderno"),
    estado ENUM("Disponible","Prestado","En reparación","Retirado"),
    id_ubicacion INT,
    PRIMARY KEY(id_material),
    FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion)
);
CREATE TABLE IF NOT EXISTS alerta_stock (
	id_alerta INT auto_increment PRIMARY KEY,
	nombre_material VARCHAR(30),
	fecha date,
	mensaje VARCHAR(60),
	resuelta boolean
    -- ,FOREIGN KEY (nombre_material) REFERENCES material(nombre)
);
CREATE TABLE usuario(
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20),
	apellidos VARCHAR(30),
	email VARCHAR(30) UNIQUE,
	contraseña VARCHAR(20),
	rol ENUM ("profesor", "administrador"),
	activo BOOLEAN,
	fecha_creacion DATE
);
CREATE TABLE movimiento(
	id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
	id_usuario INT,
	id_material INT,
	fecha DATE,
	observacion VARCHAR(60),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
	FOREIGN KEY (id_material) REFERENCES material(id_material)
);


/*
INSERTAR DATOS
*/


INSERT INTO ubicacion VALUES

	-- Armarios
    (11,1,NULL,NULL,"Armario A1"),
    (12,2,NULL,NULL,"Armario A2"),
    (13,3,NULL,NULL,"Armario A3"),
	(14,4,NULL,NULL,"Armario A4"),
    (15,5,NULL,NULL,"Armario A5"),
    
    -- Baldas independientes
    (2001,NULL,1,NULL,"Balda B001"),
    (2002,NULL,2,NULL,"Balda B002"),
    (2003,NULL,3,NULL,"Balda B003"),
    (2004,NULL,4,NULL,"Balda B004"),
    (2005,NULL,5,NULL,"Balda B005"),
    (2006,NULL,6,NULL,"Balda B006"),
    (2007,NULL,7,NULL,"Balda B007"),
    (2008,NULL,8,NULL,"Balda B008"),
    (2009,NULL,9,NULL,"Balda B009"),
    (2010,NULL,10,NULL,"Balda B010"),
    (2011,NULL,11,NULL,"Balda B011"),
    (2012,NULL,12,NULL,"Balda B012"),
    (2013,NULL,13,NULL,"Balda B013"),
    (2014,NULL,14,NULL,"Balda B014"),
    (2015,NULL,15,NULL,"Balda B015"),
    (2016,NULL,16,NULL,"Balda B016"),
    (2017,NULL,17,NULL,"Balda B017"),
    (2018,NULL,18,NULL,"Balda B018"),
    
	-- Baldas dentro de armarios
    -- Armario A1
	(2101,11,101,NULL,"Balda B101"),
    (2102,11,102,NULL,"Balda B102"),
    (2103,11,103,NULL,"Balda B103"),
    (2104,11,104,NULL,"Balda B104"),
    (2105,11,105,NULL,"Balda B105"),
    (2106,11,106,NULL,"Balda B106"),
    (2107,11,107,NULL,"Balda B107"),
    (2108,11,108,NULL,"Balda B108"),
    (2109,11,109,NULL,"Balda B109"),
    (2110,11,110,NULL,"Balda B110"),
    (2111,11,111,NULL,"Balda B111"),
    (2112,11,112,NULL,"Balda B112"),
    -- Armario A2
    (2201,12,201,NULL,"Balda B201"),
    (2202,12,202,NULL,"Balda B202"),
    -- Armario A3
    (2301,13,301,NULL,"Balda B301"),
    (2302,13,302,NULL,"Balda B302"),
    -- Armario A4
    (2401,14,401,NULL,"Balda B401"),
    (2402,14,402,NULL,"Balda B402"),
    -- Armario A5
    (2501,15,501,NULL,"Balda B501"),
    (2502,15,502,NULL,"Balda B502"),
    (2503,15,503,NULL,"Balda B503"),
    (2504,15,504,NULL,"Balda B504"),
    (2505,15,505,NULL,"Balda B505"),
    (2506,15,506,NULL,"Balda B506"),
    (2507,15,507,NULL,"Balda B507"),
    (2508,15,508,NULL,"Balda B508"),
    (2509,15,509,NULL,"Balda B509"),
    (2510,15,510,NULL,"Balda B510"),
    (2511,15,511,NULL,"Balda B511"),
    (2512,15,512,NULL,"Balda B512"),
    
    -- Cajones en baldas
    -- Balda B002
    (300201,NULL,2,00201,"Cajon C00201"),
    (300202,NULL,2,00202,"Cajon C00202"),
    (300203,NULL,2,00203,"Cajon C00203"),
    (300204,NULL,2,00204,"Cajon C00204"),
    (300205,NULL,2,00205,"Cajon C00205"),
    (300206,NULL,2,00206,"Cajon C00206"),
    -- Balda B003
    (300301,NULL,3,00301,"Cajon C00301"),
    -- Balda B006
    (300601,NULL,6,00601,"Cajon C00601"),
    (300602,NULL,6,00602,"Cajon C00602"),
    (300603,NULL,6,00603,"Cajon C00603"),
    (300604,NULL,6,00604,"Cajon C00604"),
    (300605,NULL,6,00605,"Cajon C00605"),
    (300606,NULL,6,00606,"Cajon C00606"),
    -- Balda B007
    (300701,NULL,7,00701,"Cajon C00701"),
    -- Balda B008
    (300801,NULL,8,00801,"Cajon C00801"),
    (300802,NULL,8,00802,"Cajon C00802"),
    (300803,NULL,8,00803,"Cajon C00803"),
    (300804,NULL,8,00804,"Cajon C00804"),
    (300805,NULL,8,00805,"Cajon C00805"),
    -- Balda B009
    (300901,NULL,9,00901,"Cajon C00901"),
    (300902,NULL,9,00902,"Cajon C00902"),
    -- Balda B010
    (301001,NULL,10,01001,"Cajon C01001"),
    (301002,NULL,10,01002,"Cajon C01002"),
    (301003,NULL,10,01003,"Cajon C01003"),
    (301004,NULL,10,01004,"Cajon C01004"),
    -- Balda B013
    (301301,NULL,13,01301,"Cajon C01301"),
    (301302,NULL,13,01302,"Cajon C01302"),
    -- Balda B014
    (301401,NULL,14,01401,"Cajon C01401"),
    (301402,NULL,14,01402,"Cajon C01402"),
    -- Balda B016
    (301601,NULL,16,01601,"Cajon C01601"),
    (301602,NULL,16,01602,"Cajon C01602"),
    (301603,NULL,16,01603,"Cajon C01603"),
    (301604,NULL,16,01604,"Cajon C01604"),
    (301605,NULL,16,01605,"Cajon C01605"),
    -- Balda B017
    (301701,NULL,17,01701,"Cajon C01701"),
    -- Balda B018
    (301801,NULL,18,01801,"Cajon C01801"),
    (301802,NULL,18,01802,"Cajon C01802")
;

INSERT INTO material VALUES
	
    -- ¡¡LEER ESTO!!
    
    -- En un objeto Material se están guardando atributos individuales (categoría y estado)
    -- pero también se están guardando atributos generales (stock_minimo y cantidad).
    -- Lo ideal sería que ese stock mínimo, y la cantidad actual se guardasen en un
    -- objeto general (inventario); pero también se pueden guardar en cada material, aunque
    -- el valor tendría que ser siempre el mismo.
    
    -- Cuando un material cambia su estado de "Disponible" a cualquier otro
    -- la cantidad tiene que bajar en todos los materiales con EL MISMO NOMBRE,
    -- no con la misma categoría

    (1,"Manual","Manual de Word 2007",3,1,"Cuaderno","Disponible",2001),
    (2,"Manual","Manual de Word 2007",3,1,"Cuaderno","Disponible",2001),
    (3,"Manual","Manual de Word 2007",3,1,"Cuaderno","Disponible",2001),
    
    (4,"Libro DAM","Libro de texto 1ºDAM 2015",8,3,"Cuaderno","Disponible",2003),
    (5,"Libro DAM","Libro de texto 1ºDAM 2015",8,3,"Cuaderno","Disponible",2003),
    (6,"Libro DAM","Libro de texto 1ºDAM 2015",8,3,"Cuaderno","Disponible",2003),
    (7,"Libro DAM","Libro de texto 1ºDAM 2015",8,3,"Cuaderno","Disponible",2003),
    (8,"Libro DAM","Libro de texto 1ºDAM 2015",8,3,"Cuaderno","Disponible",2003),
    (9,"Libro DAM","Libro de texto 1ºDAM 2015",8,3,"Cuaderno","Disponible",2003),
    (10,"Libro DAM","Libro de texto 1ºDAM 2015",8,3,"Cuaderno","Disponible",2003),
    (11,"Libro DAM","Libro de texto 1ºDAM 2015",8,3,"Cuaderno","Disponible",2003),
    
    (12,"Teclado USB","Teclado con conexión USB",2,1,"Hardware","Disponible",300901),
    (13,"Teclado USB","Teclado con conexión USB",2,1,"Hardware","Disponible",300901),
    
    (14,"Pistola de silicona","Pistola de silicona",1,1,"Herramienta","Disponible",301604),
    
    (15,"Mascara","Proteccion para los ojos",1,1,"Herramienta","Disponible",301601),
    
    (16,"Portatil","Portatil",1,1,"Hardware","Disponible",2012),
    
    (17,"Tinta","Tinta para la impresora",1,1,"Fungible","Disponible",2202),
    
    (18,"Tornillos","Caja de tornillos pequeños",3,2,"Fungible","Disponible",2201),
    (19,"Tornillos","Caja de tornillos pequeños",3,2,"Fungible","Disponible",2201),
    (20,"Tornillos","Caja de tornillos pequeños",3,2,"Fungible","Disponible",2201),
    
    (21,"Cable ethernet","Cable ethernet de 1 metro",1,1,"Hardware","Disponible",301603)
;
INSERT INTO usuario VALUES
	(1,"Roberto","Macho Gonzalez","robermach@gmail.com",1234,"profesor",true,"2026-04-28"),
    (2,"Pedro","Sanchez","pedrosanxe@gmail.com",4321,"profesor",true,"2018-10-15"),
    (3,"Hugo","Fernandez","hfer@gmail.com",4132,"administrador",true,"2026-05-07")
;


/*
VARIABLES
*/

SET @id_usuario=NULL;
-- El modo actualización sirve para permitir (FALSE) o bloquear el trigger movimiento (TRUE).
-- Si está en FALSE, la tabla movimiento no registrará nada
-- Esto sirve para usar actualizarCantidad() sin que se genere un montón de filas en la tabla movimiento
SET @modo_actualizacion=FALSE;


/*
PROCEDIMIENTOS Y FUNCIONES
*/


-- HAY QUE CAMBIAR ESTE PROCEDIMIENTO PARA QUE DEFINA LA VARIABLE CON LA ID DEL USUARIO QUE ESTÁ TRABAJANDO
-- RECIBIR PARÁMETRO DESDE PROGRAMA
DELIMITER //

CREATE PROCEDURE definirIdUsuario()
READS SQL DATA
BEGIN
	SELECT id_usuario
    INTO @id_usuario
    FROM usuario
    WHERE activo IS TRUE;
END //

-- ESTE PROCEDIMIENTO SE TIENE QUE USAR DESDE EL PROGRAMA
-- HAY QUE UTILIZARLO SIEMPRE QUE SE HAGA UNA MODIFICACIÓN EN LA BASE DE DATOS ¡¡MUY IMPORTANTE!!
CREATE PROCEDURE actualizarCantidad() -- FUNCIONA
READS SQL DATA
BEGIN
	DECLARE contador INT;
    DECLARE newCantidad INT;
    DECLARE nombreMat VARCHAR(30);
    SET contador=1;
    SET @modo_actualizacion = TRUE;
    REPEAT 
		-- Seleccionar nombre de material
        SELECT nombre INTO nombreMat FROM material WHERE id_material=contador;
		-- Contar cuantos materiales hay con el mismo nombre
        SELECT count(*) INTO newCantidad FROM material WHERE nombre=nombreMat;
	 	-- Actualizar campo "cantidad" de esos materiales con el resultado obtenido
        UPDATE material SET cantidad=newCantidad WHERE nombre=nombreMat;
        SET contador=contador+1;
    UNTIL contador=(SELECT count(*) FROM material)
    END REPEAT;
    SET @modo_actualizacion = FALSE;
END //
DELIMITER ;


/*
TRIGGERS
*/


DROP TRIGGER IF EXISTS trg_movimiento;
DROP TRIGGER IF EXISTS trg_alerta_stock;
DROP TRIGGER IF EXISTS trg_actualizar_del;
DROP TRIGGER IF EXISTS trg_actualizar_upd;


-- Este TRIGGER tiene que registrar cada movimiento que haya en la base de datos
DELIMITER //

CREATE TRIGGER trg_movimiento
AFTER UPDATE ON material
FOR EACH ROW
BEGIN
	DECLARE observaciones VARCHAR(80);
	SET observaciones = 'Se ha modificado : ';
    
	IF @modo_actualizacion = FALSE THEN
		IF NEW.nombre!=OLD.nombre THEN SET observaciones = concat(observaciones,'nombre ');END IF;
		IF NEW.descripcion!=OLD.descripcion THEN SET observaciones = concat(observaciones,'descripcion ');END IF;
		IF NEW.stock_minimo!=OLD.stock_minimo THEN SET observaciones = concat(observaciones,'stock_minimo ');END IF;
		IF NEW.categoria!=OLD.categoria THEN SET observaciones = concat(observaciones,'categoria ');END IF;
		IF NEW.estado!=OLD.estado THEN SET observaciones = concat(observaciones,'estado ');END IF;
		IF NEW.id_ubicacion!=OLD.id_ubicacion THEN SET observaciones = concat(observaciones,'id_ubicacion ');END IF;
    
		INSERT INTO movimiento(id_usuario,id_material,fecha,observacion)
		VALUES(@id_usuario,NEW.id_material,curdate(),observaciones);
	END IF;
    
END //

-- Este TRIGGER revisa que la cantidad de materiales no sea inferior al stock mínimo
-- PARA QUE FUNCIONE, ANTES SE TIENE QUE HABER EJECUTADO actualizarCantidad()
CREATE TRIGGER trg_alerta_stock
AFTER DELETE ON material
FOR EACH ROW
BEGIN
	DECLARE mensajes VARCHAR(60);
    DECLARE diferencia INT;
    DECLARE nombreMaterial VARCHAR(30);
    DECLARE cantidadActual INT;
    
    SET nombreMaterial = OLD.nombre;
    
    SELECT count(*) INTO cantidadActual FROM material WHERE nombre=nombreMaterial;
    
	IF(cantidadActual<OLD.stock_minimo) THEN
		SET diferencia = OLD.stock_minimo-cantidadActual;
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





