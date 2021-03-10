CREATE TABLE Tipo_Comida(
    id BIGSERIAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome)
);