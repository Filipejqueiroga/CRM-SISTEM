PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS Empresa (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cnpj TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Franquia (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cidade TEXT NOT NULL,
    status TEXT NOT NULL,
    empresa_id INTEGER NOT NULL,
    tipo_negocio TEXT NOT NULL,
    FOREIGN KEY(empresa_id) REFERENCES Empresa(id)
);

CREATE TABLE IF NOT EXISTS Usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT NOT NULL,
    nome_usuario TEXT NOT NULL,
    senha TEXT NOT NULL,
    tipo_usuario TEXT NOT NULL,
    empresa_id INTEGER,
    franquia_id INTEGER,
    FOREIGN KEY(empresa_id) REFERENCES Empresa(id),
    FOREIGN KEY(franquia_id) REFERENCES Franquia(id)
);

CREATE TABLE IF NOT EXISTS Cliente (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    numero_telefone TEXT NOT NULL,
    tipo_plano TEXT NOT NULL,
    franquia_id INTEGER,
    FOREIGN KEY(franquia_id) REFERENCES Franquia(id)
);

CREATE TABLE IF NOT EXISTS Venda (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_cliente INTEGER,
    franquia_id INTEGER,
    descricao TEXT NOT NULL,
    valor REAL,
    data TEXT,
    FOREIGN KEY(id_cliente) REFERENCES Cliente(id),
    FOREIGN KEY(franquia_id) REFERENCES Franquia(id)
);


CREATE TABLE IF NOT EXISTS Lead (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    numero_telefone TEXT NOT NULL,
    status TEXT NOT NULL,
    franquia_id INTEGER,
    FOREIGN KEY(franquia_id) REFERENCES Franquia(id)
);

CREATE TABLE IF NOT EXISTS Checkin (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cliente_id INTEGER,
    usuario_id INTEGER,
    franquia_id INTEGER,
    data TEXT,
    hora TEXT,
    FOREIGN KEY(cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY(usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY(franquia_id) REFERENCES Franquia(id)
);


INSERT INTO Empresa (nome, cnpj) VALUES ('Selfit', '12345678000199');

INSERT INTO Franquia (nome, cidade, status, empresa_id, tipo_negocio) VALUES
('Selfit João Pessoa', 'João Pessoa', 'Ativa', 1, 'Academia'),
('Selfit São Paulo', 'São Paulo', 'Ativa', 1, 'Academia');

INSERT INTO Usuario (email, nome_usuario, senha, tipo_usuario, empresa_id)
VALUES ('admin@selfit.com', 'admin_selfit', 'senha123', 'franqueador', 1);

INSERT INTO Usuario (email, nome_usuario, senha, tipo_usuario, franquia_id)
VALUES ('joao@selfit.com', 'joao_jp', 'senha456', 'franqueado', 1);

INSERT INTO Usuario (email, nome_usuario, senha, tipo_usuario, franquia_id)
VALUES ('maria@selfit.com', 'maria_sp', 'senha789', 'funcionario', 2);

INSERT INTO Cliente (nome, numero_telefone, tipo_plano, franquia_id) VALUES
('Filipe', '83 8768-3922', 'anual', 1),
('Pedro', '83 9999-1111', 'mensal', 1),
('Gabriela', '83 2222-3333', 'anual', 2),
('Bruno', '83 4444-5555', 'semestral', 2);

INSERT INTO Venda (id_cliente, franquia_id, descricao, valor, data) VALUES
(1, 2,'Pagamento plano anual - 2025', 1200.00, '03/06/2025'),
(2, 2,'Compra suplemento Whey Protein', 180.00, '06/09/2023'),
(3, 2,'Taxa de matrícula', 150.00, '02/08/2012');

INSERT INTO Lead (nome, numero_telefone, status, franquia_id) VALUES
('Thais', '83 8768-3922','em negociação', 1),
('Lincoln', '83 1234-5678','aguardando resposta', 2);

INSERT INTO Checkin (cliente_id, usuario_id, franquia_id,data, hora) VALUES
(1, 2, 2,'2025-09-19', '15:22'),
(2, 2, 2,'2025-09-19', '09:56'),
(3, 3, 2,'2025-09-19', '14:29');
