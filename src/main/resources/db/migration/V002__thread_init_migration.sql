

-- Создание таблицы;
CREATE TABLE IF NOT EXISTS vote_site.vote_thread
(
    id serial PRIMARY KEY,
    name text,
    description text,
    author integer constraint data_source_fk_connection_id_fkey references vote_site.user,
    likes int,
    dislikes int,
    image bytea,
    creation_date date,
    creation_time time
);








