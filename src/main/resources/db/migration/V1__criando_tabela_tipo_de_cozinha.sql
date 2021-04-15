CREATE TABLE Tipo_Cozinha(
    id BIGSERIAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome)
);
