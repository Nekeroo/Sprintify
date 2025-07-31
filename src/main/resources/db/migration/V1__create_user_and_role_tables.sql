-- Table des rôles
CREATE TABLE IF NOT EXISTS roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

-- Table des utilisateurs
CREATE TABLE IF NOT EXISTS users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100),
                       role_id BIGINT NOT NULL,
                       CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Insérer des rôles par défaut
INSERT INTO roles (name)
SELECT * FROM (SELECT 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM roles WHERE name = 'ROLE_USER'
) LIMIT 1;

INSERT INTO roles (name)
SELECT * FROM (SELECT 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM roles WHERE name = 'ROLE_ADMIN'
) LIMIT 1;