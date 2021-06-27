CREATE TABLE Item(
    id BIGSERIAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    preco DECIMAL,
    cardapio_id BIGSERIAL NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome),
    CONSTRAINT FK_Cardapio_Item FOREIGN KEY (cardapio_id) REFERENCES Cardapio(id)
);
