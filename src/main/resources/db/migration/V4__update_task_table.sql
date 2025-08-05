UPDATE tasks
SET due_date = CURDATE()
WHERE due_date IS NULL;