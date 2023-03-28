CREATE TABLE IF NOT EXISTS vote_site.thread_rating
(
    user_id int,
    thread_id int,
    PRIMARY KEY(user_id, thread_id),
    FOREIGN KEY(user_id) REFERENCES vote_site.user(id),
    FOREIGN KEY (thread_id) REFERENCES  vote_site.vote_thread(id)

);










