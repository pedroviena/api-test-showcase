-- Inserir dados na tabela de usuários (users)
-- A senha 'password123' é apenas um exemplo. Em um ambiente real, ela deveria ser criptografada.
INSERT INTO users (id, name, email, password) VALUES
(1, 'Ana Silva', 'ana.silva@example.com', 'password123'),
(2, 'Bruno Costa', 'bruno.costa@example.com', 'password123'),
(3, 'Carla Dias', 'carla.dias@example.com', 'password123');

-- Inserir dados na tabela de produtos (product)
INSERT INTO product (id, name, description, price) VALUES
(101, 'Notebook Pro', 'Notebook de alta performance com 16GB RAM e SSD 512GB', 7500.00),
(102, 'Mouse Sem Fio', 'Mouse ergonômico com conexão Bluetooth', 150.50),
(103, 'Teclado Mecânico', 'Teclado mecânico retroiluminado para gamers', 499.99),
(104, 'Monitor 4K', 'Monitor de 27 polegadas com resolução 4K UHD', 2200.00);

-- Ajustar a sequência dos IDs para evitar conflitos ao criar novos registros
-- O valor '4' deve ser o próximo ID disponível para usuários
-- O valor '105' deve ser o próximo ID disponível para produtos
ALTER SEQUENCE users_seq RESTART WITH 4;
ALTER SEQUENCE product_seq RESTART WITH 105;