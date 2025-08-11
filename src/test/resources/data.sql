-- Insert mock roles
INSERT INTO roles (id, name)
values (98, 'ROLE_USER'),
       (99, 'ROLE_ADMIN');

-- Insert mock user
INSERT INTO users (id, username, password, email, role_id)
values (99, 'demo', '$2a$10$xUsDIsHlpDL6ArRb0qgdM.kH/Na2JZgxYBL2sATnf7VKYSUafO99e', 'demo@gmail.com', 99);


INSERT INTO projects (id, name, description, owner_id)
values (99, 'Projet Test', 'Description du projet', 99);

INSERT INTO sprints (id, name, project_id, description, start_date, end_date)
values (99, 'Sprint 1', 99, 'Description du sprint', '2025-09-01', '2025-09-22');

INSERT INTO tasks (id, name, description, sprint_id, due_date, status, assignee_id)
values (99, 'Tache 1', 'Description de la tache 1', 99, '2023-01-01', 'READY', 99);