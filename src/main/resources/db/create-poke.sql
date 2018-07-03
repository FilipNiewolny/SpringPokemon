--DROP TABLE poke IF EXISTS;

CREATE TABLE poke (

id  INTEGER PRIMARY KEY ,
name VARCHAR (30),
weight VARCHAR (30),
speciesUrl VARCHAR (300),
speciesName VARCHAR (30),
stats VARCHAR (300),
abilities VARCHAR (300)
);