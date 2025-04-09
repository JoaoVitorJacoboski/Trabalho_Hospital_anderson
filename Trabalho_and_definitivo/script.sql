CREATE TABLE paciente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero INTEGER,
    bairro VARCHAR(50) NOT NULL,
    complemento VARCHAR(50),
    cidade VARCHAR(50) NOT NULL,
    uf CHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela Medico
CREATE TABLE medico (
    id SERIAL PRIMARY KEY,
    crm INTEGER UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    especialidade VARCHAR(50) NOT NULL CHECK (especialidade IN ('Ortopedia', 'Cardiologia', 'Ginecologia', 'Dermatologia')),
    logradouro VARCHAR(100) NOT NULL,
    numero INTEGER,
    bairro VARCHAR(50) NOT NULL,
    complemento VARCHAR(50),
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela Consulta
CREATE TABLE consulta (
    id SERIAL PRIMARY KEY,
    id_paciente INTEGER NOT NULL REFERENCES paciente(id),
    id_medico INTEGER NOT NULL REFERENCES medico(id),
    dt_hora TIMESTAMP NOT NULL,
    cancelada BOOLEAN DEFAULT FALSE,
    motivo_cancelamento VARCHAR(255)
);

-- Índices para otimização
CREATE INDEX idx_consulta_medico_dt_hora ON consulta(id_medico, dt_hora);
CREATE INDEX idx_consulta_paciente_dt_hora ON consulta(id_paciente, dt_hora);