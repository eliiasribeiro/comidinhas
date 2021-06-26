CREATE TABLE Item(
    id BIGSERIAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    preco DECIMAL,
    cardapio_id BIGSERIAL NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome)
);
