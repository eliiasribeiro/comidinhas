CREATE TABLE Restaurante(
    id BIGSERIAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cnpj VARCHAR(25) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    cep  VARCHAR(9) NOT NULL,
    descricao TEXT,
    tipo_cozinha_id BIGSERIAL NOT NULL,
    logo VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome,cnpj),
    CONSTRAINT FK_Restaurante_TipoCozinha FOREIGN KEY (tipo_cozinha_id) REFERENCES Tipo_Cozinha(id)
);
