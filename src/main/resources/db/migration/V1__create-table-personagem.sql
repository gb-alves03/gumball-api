create table personagem(
    id UUID primary key,
    nome varchar(255) not null,
    especie varchar(120) not null,
    idade int,
    datacreated timestamp
);