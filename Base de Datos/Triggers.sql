DROP trigger IF exists trg_movimiento;
DROP trigger IF exists trg_alerta_stock;

DELIMITER //

CREATE trigger trg_movimiento
AFTER UPDATE ON material
FOR EACH ROW
BEGIN
INSERT INTO movimiento(
id_usuario, id_material, cantidad, fecha, observacion
)
SELECT 
@id_usuario,
id_material,
@cantidad,
CURDATE(),
@observaciones
FROM material 
WHERE id_ubicacion = NEW.id_ubicacion;
END//

CREATE TRIGGER trg_alerta_stock
AFTER UPDATE ON material
FOR EACH ROW
BEGIN
CALL actualizarCantidad();
IF (NEW.cantidad<NEW.stock_minimo) THEN
	INSERT alerta_stock(id_material, fecha, mensaje, resuelta)
    SELECT
    id_material,
    CURDATE(),
    CONCAT("Stock de ", nombre, " insuficiente"),
    false
    FROM material
    WHERE cantidad = NEW.cantidad;
ELSEIF (cantidad>stock_minimo) THEN
	UPDATE alerta_stock
    SET resuelta = true
    WHERE id_material = NEW.id_material AND resuelta = false;
END IF;
END//
	
DELIMITER ;
