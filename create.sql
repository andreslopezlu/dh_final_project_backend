create table IF NOT EXISTS DOMICILIOS(ID INT auto_increment PRIMARY KEY,CALLE VARCHAR(255),NUMERO INT,PROVINCIA VARCHAR (255), PAIS varchar(255), CODIGO_POSTAL INT);
create table IF NOT EXISTS ODONTOLOGOS(ID INT auto_increment PRIMARY KEY,NOMBRE VARCHAR(255),APELLIDO VARCHAR (255),MATRICULA int);
create table IF NOT EXISTS PACIENTES(ID INT auto_increment PRIMARY KEY,NOMBRE VARCHAR(255),APELLIDO VARCHAR (255), DOMICILIO_ID INT, DNI INT, FECHA_ALTA TIMESTAMP WITHOUT TIME ZONE);
create table IF NOT EXISTS TURNOS(ID INT auto_increment PRIMARY KEY,PACIENTE_ID INT,ODONTOLOGO_ID INT,FECHA TIMESTAMP WITHOUT TIME ZONE);
create table IF NOT EXISTS USUARIOS(ID INT auto_increment PRIMARY KEY,USUARIO VARCHAR(255),PASSWORD VARCHAR(255),ROL VARCHAR(255));
