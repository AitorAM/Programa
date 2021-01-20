DELIMITER ;
CREATE database if not exists Empresa;
USE Empresa;
DROP table if exists proveedores;
CREATE table proveedores (
	id_proveedor 	INT UNSIGNED UNIQUE NOT NULL,
	nombre 			VARCHAR(200) NOT NULL,
	fecha_alta 		DATE NOT NULL,
    id_cliente      INT NOT NULL,
    PRIMARY KEY     (id_proveedor)
);

INSERT INTO proveedores (id_proveedor, nombre, fecha_alta, id_cliente) VALUES (1, 'Coca-Cola', '2021-01-22',5);
INSERT INTO proveedores (id_proveedor, nombre, fecha_alta, id_cliente) VALUES (2, 'Pepsi', '2021-01-24',5);
INSERT INTO proveedores (id_proveedor, nombre, fecha_alta, id_cliente) VALUES (3, 'Redbull', '2021-01-08',6);

