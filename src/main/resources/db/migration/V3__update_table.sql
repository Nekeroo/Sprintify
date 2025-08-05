ALTER TABLE sprints
    ADD COLUMN start_date DATE,
    ADD COLUMN end_date DATE;

UPDATE sprints
SET start_date = CURDATE(),
    end_date = DATE_ADD(CURDATE(), INTERVAL 14 DAY)
WHERE start_date IS NULL;

ALTER TABLE sprints
    MODIFY COLUMN start_date DATE NOT NULL,
    MODIFY COLUMN end_date DATE NOT NULL;