INSERT INTO departments (id, name)
VALUES (1, 'Administration'),
       (2, 'Marketing'),
       (3, 'Sales'),
       (4, 'After sales'),
       (5, 'Human resources'),
       (6, 'Accountants'),
       (7, 'Warehouse');

INSERT INTO positions (id, name)
VALUES (1, 'Administrator'),
       (2, 'Marketing specialist'),
       (3, 'Salesman'),
       (4, 'Senior salesman'),
       (5, 'Guarantee manager'),
       (6, 'Mechanic'),
       (7, 'HR specialist'),
       (8, 'Accountant'),
       (9, 'Chief accountant'),
       (10, 'Warehouseman');

INSERT INTO employees (id, name, surname, telephone, department_id)
VALUES (1, 'Ivan', 'Ivanov', '+3752912345678', 1),
       (2, 'Fedor', 'Fedorov', '+3752922345678', 2),
       (3, 'Igor', 'Petrov', '+3752915345678', 3),
       (4, 'John', 'Cena', '+3752917345678', 4),
       (5, 'Jack', 'Vorobei', '+3752919345678', 5),
       (6, 'John', 'Connor', '+3752911245678', 6),
       (7, 'Lebron', 'James', '+3752912645678', 7),
       (8, 'Hanna', 'Schmitz', '+3752912945678', 1),
       (9, 'Angela', 'Cullen', '+3752912045678', 2),
       (10, 'Steve', 'Rogers', '+3752912345278', 3),
       (11, 'Natasha', 'Romanov', '+3752912345378', 4),
       (12, 'Bruce', 'Banner', '+3752912345778', 5),
       (13, 'Angelina', 'Jolie', '+3752912345078', 6),
       (14, 'Sasha', 'Grey', '+3752912345671', 7);

INSERT INTO employees_positions (employee_id, position_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (3, 4),
       (4, 5),
       (5, 6),
       (5, 7),
       (6, 8),
       (7, 9),
       (7, 10),
       (8, 1),
       (9, 2),
       (10, 3),
       (10, 4),
       (11, 5),
       (12, 6),
       (13, 7),
       (14, 8),
       (14, 9);