TRUNCATE ONLY PROVEEDORES
RESTART IDENTITY CASCADE;
TRUNCATE ONLY AUTHORITIES
RESTART IDENTITY CASCADE;
TRUNCATE ONLY USUARIOS
RESTART IDENTITY CASCADE;
TRUNCATE ONLY ORDEN_COMPRAS
RESTART IDENTITY CASCADE;
TRUNCATE ONLY PRODUCTOS
RESTART IDENTITY CASCADE;
TRUNCATE ONLY CATEGORIAS
RESTART IDENTITY CASCADE;
TRUNCATE ONLY ALMACENES
RESTART IDENTITY CASCADE;
TRUNCATE ONLY PEDIDOS
RESTART IDENTITY CASCADE;
TRUNCATE ONLY CLIENTES
RESTART IDENTITY CASCADE;
TRUNCATE ONLY PEDIDO_DETALLE
RESTART IDENTITY CASCADE;

DELETE FROM PROVEEDORES;
DELETE FROM AUTHORITIES;
DELETE FROM USUARIOS;
DELETE FROM ORDEN_COMPRAS;
DELETE FROM PRODUCTOS;
DELETE FROM CATEGORIAS;
DELETE FROM ALMACENES;
DELETE FROM PEDIDOS;
DELETE FROM PEDIDO_DETALLE;
DELETE FROM CLIENTES;
INSERT INTO authorities(id,authority) VALUES(1,'ROLE_ADMIN');
INSERT INTO authorities(id,authority) VALUES(2,'ROLE_JEFE');
INSERT INTO authorities(id,authority) VALUES(3,'ROLE_AUXILIAR');
INSERT INTO proveedores(id,ruc,numero,razon_social,direccion,contacto,correo) VALUES(1,'AX4252','957846373','HP Company Inc.','Surco, MO','Fabricio Barrientos','proveedor1@gmail.com');
INSERT INTO proveedores(id,ruc,numero,razon_social,direccion,contacto,correo) VALUES(2,'AX4251','957842373','Lenovo Company Inc.','Surco, MO','Maikol Taboada','proveedor2@gmail.com');
INSERT INTO proveedores(id,ruc,numero,razon_social,direccion,contacto,correo) VALUES(3,'AC2142','957843373','MSI Company Inc.','Surco, MO','Juan Gutierrez','proveedor3@gmail.com');
INSERT INTO proveedores(id,ruc,numero,razon_social,direccion,contacto,correo) VALUES(4,'ASG441','954342373','Huawei Company Inc.','Surco, MO','Brayan Ortiz','proveedor4@gmail.com');

INSERT INTO usuarios(usu_codigo,apellidos,dni,edad,nombres,password,rol_id) VALUES(1,'Barrientos','70262920',20,'Rogger','$2a$10$JnHnNnoZiH1rTCXe7Qml7.Uzsm95jwdnmZLGnezGYzsQK0nFICEUG',2);
INSERT INTO usuarios(usu_codigo,apellidos,dni,edad,nombres,password,rol_id) VALUES(2,'Cabrera','10245299',22,'Miguel','$2a$10$JnHnNnoZiH1rTCXe7Qml7.Uzsm95jwdnmZLGnezGYzsQK0nFICEUG',3);
INSERT INTO orden_compras(id,correlativo,fecha_registro,serie,proveedor_id,estado) VALUES(1,'AFF00F','2022-08-30 00:00:00','ACFV0A',1,0);
INSERT INTO orden_compras(id,correlativo,fecha_registro,serie,proveedor_id,estado) VALUES(2,'CCC00D','2022-08-30 00:00:00','ADRFVM',2,0);
INSERT INTO orden_compras(id,correlativo,fecha_registro,serie,proveedor_id,estado) VALUES(3,'AEF00E','2022-08-30 00:00:00','VCGV0D',3,0);
INSERT INTO orden_compras(id,correlativo,fecha_registro,serie,proveedor_id,estado) VALUES(4,'PMJ001','2022-08-30 00:00:00','ASCV0W',4,0);

INSERT INTO categorias(descripcion,estado,fecha_baja,fecha_creacion,fecha_modificacion) VALUES('Televisores',1,'2022-09-10 00:00:00','2022-08-30 00:00:00','2022-08-30 00:00:00');
INSERT INTO categorias(descripcion,estado,fecha_baja,fecha_creacion,fecha_modificacion) VALUES('Cocinas',1,'2022-09-10 00:00:00','2022-08-30 00:00:00','2022-08-30 00:00:00');
INSERT INTO categorias(descripcion,estado,fecha_baja,fecha_creacion,fecha_modificacion) VALUES('Redes',1,'2022-09-10 00:00:00','2022-08-30 00:00:00','2022-08-30 00:00:00');

INSERT INTO almacenes(codigo,descripcion,direccion,estado,fecha_baja,fecha_creacion,fecha_modificacion) VALUES('ACDF54SC','Almacen Productos','Surco, Mo',1,'2022-09-20 00:00:00','2022-08-30 00:00:00','2022-08-30 00:00:00');


INSERT INTO productos(descripcion,estado,fecha_baja,fecha_modificacion,fecha_registro,sku,stock,unidad,almacen_id,categoria_id) VALUES('Samsung TV AU800',1,'2022-09-10 00:00:00','2022-09-10 00:00:00','2022-08-30 00:00:00','ACDC1205',0,'m',1,1);
INSERT INTO productos(descripcion,estado,fecha_baja,fecha_modificacion,fecha_registro,sku,stock,unidad,almacen_id,categoria_id) VALUES('Cocina Bosch',1,'2022-09-10 00:00:00','2022-09-10 00:00:00','2022-08-30 00:00:00','FCVSD105',0,'cm',1,2);
INSERT INTO productos(descripcion,estado,fecha_baja,fecha_modificacion,fecha_registro,sku,stock,unidad,almacen_id,categoria_id) VALUES('Router Huawei',1,'2022-09-10 00:00:00','2022-09-10 00:00:00','2022-08-30 00:00:00','PSFJC125',0,'cm',1,3);

INSERT INTO orden_compra_detalle(id,cantidad,costo,orden_id,producto_id) VALUES(1,5,1000,1,1);
INSERT INTO orden_compra_detalle(id,cantidad,costo,orden_id,producto_id) VALUES(2,10,3000,1,2);
INSERT INTO orden_compra_detalle(id,cantidad,costo,orden_id,producto_id) VALUES(3,15,5000,1,3);

INSERT INTO orden_compra_detalle(id,cantidad,costo,orden_id,producto_id) VALUES(4,20,10000,2,1);
INSERT INTO orden_compra_detalle(id,cantidad,costo,orden_id,producto_id) VALUES(5,10,8000,2,2);
INSERT INTO orden_compra_detalle(id,cantidad,costo,orden_id,producto_id) VALUES(6,8,5000,3,3);
INSERT INTO orden_compra_detalle(id,cantidad,costo,orden_id,producto_id) VALUES(7,10,8000,3,1);
INSERT INTO orden_compra_detalle(id,cantidad,costo,orden_id,producto_id) VALUES(8,12,4000,4,2);
INSERT INTO orden_compra_detalle(id,cantidad,costo,orden_id,producto_id) VALUES(9,12,5000,4,3);

INSERT INTO clientes(id,contacto,correo,direccion,razon_social,ruc,telefono) VALUES(1,'HHHHH','cliente1@gmail.com','Surco, Mo','Venta','A00000S','910215122');

INSERT INTO clientes(id,contacto,correo,direccion,razon_social,ruc,telefono) VALUES(2,'FFFFF','cliente2@gmail.com','Lince, Ang','Venta','A0SDFF3','911042358');

INSERT INTO clientes(id,contacto,correo,direccion,razon_social,ruc,telefono) VALUES(3,'CCCCC','cliente3@gmail.com','Surquillo, Dt','Venta','AFSA0S','919541235');

INSERT INTO pedidos(id,correlativo,estado,fecha_registro,serie,cliente_id) VALUES(1,'CVS120',0,'2022-09-10 00:00:00','000001',1);
INSERT INTO pedidos(id,correlativo,estado,fecha_registro,serie,cliente_id) VALUES(2,'GPUI22',0,'2022-09-10 00:00:00','000002',2);
INSERT INTO pedidos(id,correlativo,estado,fecha_registro,serie,cliente_id) VALUES(3,'UYGHJ1',0,'2022-09-10 00:00:00','000003',2);
INSERT INTO pedidos(id,correlativo,estado,fecha_registro,serie,cliente_id) VALUES(4,'BNHYS0',0,'2022-09-10 00:00:00','000004',3);

INSERT INTO pedido_detalle(id,cantidad,precio,pedido_id,producto_id) VALUES(1,5,5000,1,1);
INSERT INTO pedido_detalle(id,cantidad,precio,pedido_id,producto_id) VALUES(2,2,4000,1,2);
INSERT INTO pedido_detalle(id,cantidad,precio,pedido_id,producto_id) VALUES(3,3,1000,1,3);

INSERT INTO pedido_detalle(id,cantidad,precio,pedido_id,producto_id) VALUES(4,1,3000,2,1);
INSERT INTO pedido_detalle(id,cantidad,precio,pedido_id,producto_id) VALUES(5,5,5000,2,3);
INSERT INTO pedido_detalle(id,cantidad,precio,pedido_id,producto_id) VALUES(6,2,2000,3,2);

INSERT INTO pedido_detalle(id,cantidad,precio,pedido_id,producto_id) VALUES(7,1,3000,4,1);
INSERT INTO pedido_detalle(id,cantidad,precio,pedido_id,producto_id) VALUES(8,1,4000,4,2);
INSERT INTO pedido_detalle(id,cantidad,precio,pedido_id,producto_id) VALUES(9,1,2000,4,3);