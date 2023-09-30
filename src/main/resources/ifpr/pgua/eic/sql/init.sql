CREATE TABLE IF NOT EXISTS agenda (
    codigo INT NOT NULL,
    nome VARCHAR(45) NOT NULL,
    telefone INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE IF NOT EXISTS telefone(
    telefone int NOT NULL,
    codigo int NOT NULL,
    PRIMARY KEY (telefone),
    FOREIGN KEY (codigo) REFERENCES agenda(codigo)
);

CREATE TABLE IF NOT EXISTS email(
    email varchar(100) NOT NULL,
    codigo int  NOT NULL,
    PRIMARY KEY (email),
    FOREIGN KEY (codigo) REFERENCES agenda(codigo)
);

