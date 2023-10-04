-- liquibase formatted sql

-- changeset sMakarenko:1
CREATE TABLE task (
    id     SERIAL,
    idChat INT,
    text   TEXT,
    date   TIMESTAMP WITHOUT TIME ZONE
)