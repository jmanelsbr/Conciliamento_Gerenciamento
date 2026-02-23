CREATE TABLE transacoes_internas (
    id_transacao_interna UUID PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor_previsto NUMERIC(19, 2) NOT NULL,
    data_transacao TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL
);