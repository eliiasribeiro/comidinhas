CREATE TABLE Cardapio(
    id BIGSERIAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    restaurante_id BIGSERIAL NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome),
    CONSTRAINT FK_Cardapio_Restaurante FOREIGN KEY (restaurante_id) REFERENCES Restaurante(id)

);