CREATE SCHEMA IF NOT EXISTS project;
CREATE TABLE IF NOT EXISTS project.book
(
    id bigserial NOT NULL,
    book_name character varying,
    book_description character varying
);