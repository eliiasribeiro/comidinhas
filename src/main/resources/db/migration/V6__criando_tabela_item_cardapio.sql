CREATE TABLE Item_Cardapio(
    id BIGSERIAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    descricao TEXT NOT NULL,
    preco DECIMAL,
    logo VARCHAR(255),
    cardapio_id BIGSERIAL NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome),
    CONSTRAINT FK_Cardapio_Item FOREIGN KEY (cardapio_id) REFERENCES Cardapio(id)
);
