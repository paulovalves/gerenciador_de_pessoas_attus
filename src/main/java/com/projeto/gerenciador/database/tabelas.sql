CREATE TABLE gerenciador.pessoas (
                                     id SERIAL PRIMARY KEY,
                                     nome_completo VARCHAR(255) NOT NULL,
                                     data_nascimento DATE NOT NULL
);

CREATE TABLE gerenciador.enderecos (
                                       id SERIAL PRIMARY KEY,
                                       logradouro VARCHAR(255) NOT NULL,
                                       numero VARCHAR(255),
                                       cep VARCHAR(255) NOT NULL,
                                       cidade VARCHAR(255) NOT NULL,
                                       estado VARCHAR(255) NOT NULL,
                                       principal BOOLEAN,
                                       pessoa_id BIGINT REFERENCES gerenciador.pessoas(id)
);

ALTER TABLE gerenciador.enderecos ADD CONSTRAINT enderecos_unique UNIQUE (numero,cep,pessoa_id,estado,cidade);
