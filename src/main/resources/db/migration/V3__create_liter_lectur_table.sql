CREATE TABLE literature_to_lectures
(
    lect_id integer NOT NULL,
    lit_id  integer NOT NULL,
    PRIMARY KEY (lect_id, lit_id),
    FOREIGN KEY (lect_id) REFERENCES lectures (id)
        on delete cascade,
    FOREIGN KEY (lit_id) REFERENCES literature (id)
        on delete cascade
);




