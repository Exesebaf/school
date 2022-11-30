--liquibase formatted sql

-- changeset Vavilov:1
CREATE INDEX student_name_idx ON student (name);

-- changeset Vavilov:2

CREATE UNIQUE INDEX faculty_name_color_idx ON faculty (name,color);