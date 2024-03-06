CREATE TABLE IF NOT EXISTS employees
(
    id            SERIAL             NOT NULL
    CONSTRAINT employees_pkey
    PRIMARY KEY,
    name          VARCHAR(64)        NOT NULL,
    surname       VARCHAR(64)        NOT NULL,
    telephone     VARCHAR(64) UNIQUE NOT NULL,
    department_id INTEGER
    );

CREATE TABLE IF NOT EXISTS positions
(
    id   SERIAL      NOT NULL
    CONSTRAINT position_pkey
    PRIMARY KEY,
    name VARCHAR(64) NOT NULL
    );

CREATE TABLE IF NOT EXISTS employees_positions
(
    employee_id INTEGER NOT NULL
    CONSTRAINT employee_fk REFERENCES employees,
    position_id INTEGER NOT NULL
    CONSTRAINT position_fk REFERENCES positions
);

CREATE TABLE IF NOT EXISTS departments
(
    id   SERIAL      NOT NULL
    CONSTRAINT department_pkey
    PRIMARY KEY,
    name VARCHAR(64) NOT NULL
    );