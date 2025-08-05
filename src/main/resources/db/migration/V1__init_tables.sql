-- Table des rôles
CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Table des utilisateurs
CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(100),
    role_id  BIGINT       NOT NULL,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Insérer des rôles par défaut
INSERT INTO roles (name)
SELECT *
FROM (SELECT 'ROLE_USER') AS tmp
WHERE NOT EXISTS (SELECT name
                  FROM roles
                  WHERE name = 'ROLE_USER')
LIMIT 1;

INSERT INTO roles (name)
SELECT *
FROM (SELECT 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (SELECT name
                  FROM roles
                  WHERE name = 'ROLE_ADMIN')
LIMIT 1;

-- TABLE : projects
CREATE TABLE IF NOT EXISTS projects
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    owner_id    BIGINT       NOT NULL,
    CONSTRAINT fk_project_owner FOREIGN KEY (owner_id) REFERENCES users (id)
);

-- TABLE : sprints
CREATE TABLE IF NOT EXISTS sprints
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    name       VARCHAR(100) NOT NULL,
    project_id BIGINT       NOT NULL,
    start_date DATE         NOT NULL,
    end_date   DATE         NOT NULL,
    CONSTRAINT fk_sprint_project FOREIGN KEY (project_id) REFERENCES projects (id)
);

-- TABLE : tasks
CREATE TABLE IF NOT EXISTS tasks
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    sprint_id   BIGINT       NOT NULL,
    due_date    DATE,
    status      INT,
    assignee_id BIGINT,
    CONSTRAINT UK_task_assignee UNIQUE (assignee_id),
    CONSTRAINT FK_task_assignee FOREIGN KEY (assignee_id) REFERENCES users (id),
    CONSTRAINT fk_task_sprint FOREIGN KEY (sprint_id) REFERENCES sprints (id)
);

-- Insérer les rôles
INSERT INTO roles (name)
SELECT 'ROLE_USER'
WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE name = 'ROLE_USER'
);

INSERT INTO roles (name)
SELECT 'ROLE_ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE name = 'ROLE_ADMIN'
);

-- Insérer un utilisateur démo (rôle admin)
INSERT INTO users (username, password, email, role_id)
SELECT 'Demo Owner',
       '$2a$10$1.0vvXgq5BuZkqMpzmn.6OSBBcgWxjoEo0zrjbt3/96mu8vZWihtK', -- BCrypted password
       'demo@example.com',
       (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'Demo Owner'
);

-- Insérer un projet
INSERT INTO projects (name, description, owner_id)
SELECT 'Projet Test',
       'Projet démo pour Sprintify',
       (SELECT id FROM users WHERE username = 'Demo Owner')
WHERE NOT EXISTS (
    SELECT 1 FROM projects WHERE name = 'Projet Test'
);

-- Insérer un sprint
INSERT INTO sprints (name, project_id, start_date, end_date, description)
SELECT 'Sprint 1',
       (SELECT id FROM projects WHERE name = 'Projet Test'),
       CURRENT_DATE,
       CURRENT_DATE + INTERVAL '14' DAY,
       'Sprint de démonstration'
WHERE NOT EXISTS (
    SELECT 1 FROM sprints WHERE name = 'Sprint 1'
);

-- Insérer une tâche
INSERT INTO tasks (name, description, sprint_id, due_date)
SELECT 'Tâche d''exemple',
       'Une tâche de démonstration',
       (SELECT id FROM sprints WHERE name = 'Sprint 1'),
       CURRENT_DATE + INTERVAL '7' DAY
WHERE NOT EXISTS (
    SELECT 1 FROM tasks WHERE name = 'Tâche d''exemple'
);