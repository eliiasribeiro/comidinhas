CREATE TABLE Ingrediente(
    id BIGSERIAL NOT NULL,
    nome jsonb NOT NULL,
    item_id BIGSERIAL,
    PRIMARY KEY (id),
    UNIQUE (nome),
    CONSTRAINT FK_Ingrediente_Item FOREIGN KEY (item_id) REFERENCES Item(id)
);
