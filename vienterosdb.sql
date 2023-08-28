use vienterosdb;

CREATE TABLE usuarios(
	id_usuario INT PRIMARY KEY auto_increment,
    nombre VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE proyectos(
	id_proyecto INT PRIMARY KEY auto_increment,
    tipo VARCHAR(20) NOT NULL,
    tiempo_estimado_dd INT
);

CREATE TABLE asignar_proyectos(
	id_asignar INT PRIMARY KEY auto_increment,
    id_usuario INT,
    id_proyecto INT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_proyecto) REFERENCES proyectos(id_proyecto)
);

-- usaremos double para el dinero porque con money o decimal se complica en springboot :)

CREATE TABLE planeacion(
	id_planeacion INT PRIMARY KEY auto_increment,
    inversion DOUBLE(10,3),
    gasto DOUBLE(10,3),
    total DOUBLE(10,3),
    FOREIGN KEY (id_proyecto) REFERENCES proyectos(id_proyecto)
); 

CREATE TABLE productos(
	id_producto INT PRIMARY KEY auto_increment,
    nombre VARCHAR(40) NOT NULL,
    descripcion VARCHAR(60),
    cantidad_inicial INT(20) NOT NULL,
    cantidad_final INT(20),
    peso DOUBLE(20,3),
    fecha DATE,
    FOREIGN KEY (id_proyecto) REFERENCES proyectos(id_proyecto)
);

CREATE TABLE insumos(
	id_insumo INT PRIMARY KEY auto_increment,
    nombre VARCHAR(60), 
    cantidad INT(20),
    precio DOUBLE(10,3),
    FOREIGN KEY (id_proyecto) REFERENCES proyectos(id_proyecto)
); 