-- Insert mock roles
INSERT INTO roles (id, name)
values (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

-- Insert mock user
INSERT INTO users (id, username, password, email, role_id)
values (1, 'demo', '$2a$10$.vC4G0pAwbngnAn3zVS2N.E8jtEnvQ6QgUd/vF5E0Qc7FNmjxWp76', 'demo@gmail.com', 2);
