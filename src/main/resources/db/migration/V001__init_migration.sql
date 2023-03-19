-- Создание схемы
CREATE SCHEMA IF NOT EXISTS vote_site;

-- Создание последовательности;
CREATE SEQUENCE IF NOT EXISTS vote_site.vote_site_seq;

-- Создание таблицы;
CREATE TABLE IF NOT EXISTS vote_site.user
(
    id serial PRIMARY KEY,
    name text,
    second_name text,
    phone text,
    email text,
    role text,
    password text,
    avatar bytea
);








