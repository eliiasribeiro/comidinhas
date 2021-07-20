CREATE TABLE Avaliacao(
    id BIGSERIAL NOT NULL,
    nota DECIMAL  NOT NULL,
    restaurante_id BIGSERIAL NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_Avaliacao_Restaurante FOREIGN KEY (restaurante_id) REFERENCES Restaurante(id)
);
