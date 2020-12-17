CREATE TABLE literature
(
    id SERIAL PRIMARY KEY,
    title  VARCHAR(25) NOT NULL,
    author VARCHAR(25) NULL,
    type   VARCHAR(25) NULL
);