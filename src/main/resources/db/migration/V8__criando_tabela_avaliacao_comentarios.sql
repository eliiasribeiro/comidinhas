CREATE TABLE Avaliacao_Comentarios(
    id BIGSERIAL NOT NULL,
    comentario TEXT  NOT NULL,
    avaliacao_id BIGSERIAL NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_AvaliacaoComentarios_Avaliacao FOREIGN KEY (avaliacao_id) REFERENCES Avaliacao(id)
);
