DROP trigger IF exists movimiento;
DROP trigger IF exists alerta_stock;

DELIMITER //

CREATE trigger trg_movimiento
AFTER UPDATE ON material
FOR EACH ROW
BEGIN
INSERT INTO movimiento(
id_usuario, id_material, cantidad, fecha, observación
)
SELECT 
@id_usuario,
id.material,
@cantidad,
CURDATE(),
@observaciones
FROM material 
WHERE NEW.id_ubicacion
AND cantidad <= stock_minimo;
END//

CREATE TRIGGER trg_alerta_stock
AFTER UPDATE ON material
FOR EACH ROW
BEGIN
IF (NEW.cantidad<NEW.stock_minimo) THEN
	INSERT alerta_stock(id_material, fecha, mensaje, resuelta)
    SELECT
    id_material,
    CURDATE(),
    CONCAT("Stock de ", nombre, " insuficiente"),
    false
    FROM material
    WHERE NEW.cantidad
ELSEIF (cantidad>stock_minimo) THEN
	UPDATE alerta_stock
    SET resulta = true
    WHERE SELECT(id_material FROM material WHERE NEW.cantidad) AND resuelta = false
END IF;
DELIMITER ;