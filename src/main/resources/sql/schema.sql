CREATE TYPE status_enum AS ENUM ('AGENDADO', 'CANCELADO', 'ENVIADO', 'FALHA');
CREATE TYPE tipo_comunicacao_enum AS ENUM ('EMAIL', 'PUSH', 'SMS', 'WHATSAPP');

CREATE TABLE agendamento_comunicacao (
                                         id BIGSERIAL PRIMARY KEY,
                                         criado_em TIMESTAMP,
                                         data_hora_agendada TIMESTAMP NOT NULL,
                                         destinatario VARCHAR(255) NOT NULL,
                                         mensagem VARCHAR(255) NOT NULL,
                                         status status_enum DEFAULT 'AGENDADO',
                                         tipo tipo_comunicacao_enum NOT NULL
);


