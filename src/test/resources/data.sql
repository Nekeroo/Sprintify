create table roles
(
    id   bigint auto_increment
        primary key,
    name varchar(50) not null,
    constraint name
        unique (name)
);

create table users
(
    id       bigint auto_increment
        primary key,
    username varchar(50)  not null,
    password varchar(255) not null,
    email    varchar(100) null,
    role_id  bigint       not null,
    constraint username
        unique (username),
    constraint fk_role
        foreign key (role_id) references roles (id)
);

create table projects
(
    id          bigint auto_increment
        primary key,
    name        varchar(100) not null,
    description text         null,
    owner_id    bigint       not null,
    constraint fk_project_owner
        foreign key (owner_id) references users (id)
);

create table sprints
(
    id         bigint auto_increment
        primary key,
    name       varchar(100) not null,
    project_id bigint       not null,
    constraint fk_sprint_project
        foreign key (project_id) references projects (id)
);

create table tasks
(
    id          bigint auto_increment
        primary key,
    name        varchar(100) not null,
    description text         null,
    sprint_id   bigint       not null,
    constraint fk_task_sprint
        foreign key (sprint_id) references sprints (id)
);

