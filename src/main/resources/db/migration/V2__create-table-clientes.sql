
CREATE TABLE clientes (
                id BIGINT AUTO_INCREMENT NOT NULL,
                nome VARCHAR(100) NOT NULL,
                enail VARCHAR(50) NOT NULL,
                telefone VARCHAR(50) NOT NULL,
                cpf VARCHAR(11) NOT NULL,
                rua VARCHAR(100) NOT NULL,
                bairro VARCHAR(100) NOT NULL,
                cep VARCHAR(50) NOT NULL,
                uf VARCHAR(2) NOT NULL,
                numero VARCHAR(50),
                complemento VARCHAR(100),
                PRIMARY KEY (id)
);