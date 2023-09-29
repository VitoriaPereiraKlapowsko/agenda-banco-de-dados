CREATE TABLE IF NOT EXISTS agenda(
    codigo int,
    nome varchar(45),
    PRIMARY KEY (codigo)
);

CREATE TABLE IF NOT EXISTS telefone(
    telefone INT,
    codigo INT,
    PRIMARY KEY (telefone),
    FOREIGN KEY (codigo) REFERENCES agenda(codigo)
);

CREATE TABLE IF NOT EXISTS email(
    email varchar(100),
    codigo int,
    PRIMARY key (email),
    FOREIGN KEY (codigo) REFERENCES agenda(codigo)
);

