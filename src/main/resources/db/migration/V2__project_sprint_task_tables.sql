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
    name       VARCHAR(100) NOT NULL,
    project_id BIGINT       NOT NULL,
    CONSTRAINT fk_sprint_project FOREIGN KEY (project_id) REFERENCES projects (id)
);

-- TABLE : tasks
CREATE TABLE IF NOT EXISTS tasks
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    sprint_id   BIGINT       NOT NULL,
    CONSTRAINT fk_task_sprint FOREIGN KEY (sprint_id) REFERENCES sprints (id)
);

-- Insert samper user
INSERT INTO users (id, username, password, email, role_id)
SELECT *
FROM (SELECT 1,
             'Demo Owner',
             '$2a$10$1.0vvXgq5BuZkqMpzmn.6OSBBcgWxjoEo0zrjbt3/96mu8vZWihtK
            ',
             'demo@example.com',
             2) AS tmp
WHERE NOT EXISTS (SELECT 1
                  FROM users
                  WHERE id = 1)
LIMIT 1;

-- Insert a sample project
INSERT INTO projects (name, description, owner_id)
SELECT *
FROM (SELECT 'Projet Test', 'Projet démo pour Sprintify', 1) AS tmp
WHERE NOT EXISTS (SELECT 1
                  FROM projects
                  WHERE name = 'Projet Test')
LIMIT 1;

-- Insert a sample sprint
INSERT INTO sprints (name, project_id)
SELECT *
FROM (SELECT 'Sprint 1', (SELECT id FROM projects WHERE name = 'Projet Test' LIMIT 1)) AS tmp
WHERE NOT EXISTS (SELECT 1
                  FROM sprints
                  WHERE name = 'Sprint 1')
LIMIT 1;

-- Insert a sample task
INSERT INTO tasks (name, description, sprint_id)
SELECT *
FROM (SELECT 'Tâche d''exemple',
             'Une tâche de démonstration',
             (SELECT id FROM sprints WHERE name = 'Sprint 1' LIMIT 1)) AS tmp
WHERE NOT EXISTS (SELECT 1
                  FROM tasks
                  WHERE name = 'Tâche d''exemple')
LIMIT 1;
