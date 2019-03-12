/* Poblar tabla Region */
INSERT INTO regiones (id,nombre) VALUES (1,'Sudamérica');
INSERT INTO regiones (id,nombre) VALUES (2,'Centroamérica');
INSERT INTO regiones (id,nombre) VALUES (3,'Norteamérica');
INSERT INTO regiones (id,nombre) VALUES (4,'Europa');
INSERT INTO regiones (id,nombre) VALUES (5,'Asia');
INSERT INTO regiones (id,nombre) VALUES (6,'Africa');
INSERT INTO regiones (id,nombre) VALUES (7,'Oceanía');
INSERT INTO regiones (id,nombre) VALUES (8,'Antártida');

/* Poblar tabla clientes */
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(1, 'Pablo', 'Tulian', 'pablo@bolsadeideas.com', '2019-02-01');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(2, 'Mr. John', 'Doess', 'john.Does@gmail.com', '2019-02-02');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2019-02-03');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2019-02-04');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(5, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2019-02-01');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(6, 'Richard', 'Helm', 'richard.helm@gmail.com', '2019-02-10');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(7, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2019-02-18');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(8, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2019-02-28');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(1, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '2019-02-03');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(2, 'Magma', 'LeeSin', 'magma.LeeSin@gmail.com', '2019-02-04');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'Tornado', 'Roes', 'tornado.Roes@gmail.com', '2019-02-05');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'Jade', 'Does', 'jane.Does@gmail.com', '2019-02-06');

/* Creé algunos usuarios con sus roles */
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('polijt', '$2a$10$HhcSzaifkePUhiTlTCf.Pu.ChFX5DaA1RCme1x0OCi/y5OX51rPtS',1,'Pablo','Tulián','pablotulian10@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$B5mrcw/RGoNdjwAE1ej7J.96dmytIAg0V.aF7IjDDyj9h5Fvwx2lK',1,'Administrador','Administrador','poli.jt@hotmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);