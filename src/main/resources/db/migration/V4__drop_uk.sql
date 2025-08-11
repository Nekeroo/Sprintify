-- Supprimer la contrainte de clé étrangère
ALTER TABLE tasks DROP FOREIGN KEY FK_task_assignee;

-- Supprimer la contrainte unique
ALTER TABLE tasks DROP INDEX UK_task_assignee;

-- Recréer la clé étrangère sans contrainte unique
ALTER TABLE tasks
    ADD CONSTRAINT FK_task_assignee
        FOREIGN KEY (assignee_id) REFERENCES users (id);