
drop table if exists Cidade;

create table Cidade(
  id int not null AUTO_INCREMENT,
  nome varchar(100) not null,
  uf varchar(2) not null,
  capital boolean not null,  
  PRIMARY KEY ( ID )
);

drop table if exists Comercio;

create table Comercio(
    id int not null AUTO_INCREMENT,
    nome_comercio varchar(100) not null,
    responsavel_comercio varchar(100) not null,
    tipo_comercio varchar(100) not null,
    id_cidade int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cidade) REFERENCES Cidade(id)
);