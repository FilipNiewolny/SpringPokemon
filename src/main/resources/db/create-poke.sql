--DROP TABLE poke IF EXISTS;

CREATE TABLE poke (

id  INTEGER  not null auto_increment,
PokeId INTEGER,
name VARCHAR (30),
weight VARCHAR (30),
speciesUrl VARCHAR (300),
speciesName VARCHAR (30),
stats VARCHAR (300),
abilities ARRAY,
PRIMARY key (id)
);