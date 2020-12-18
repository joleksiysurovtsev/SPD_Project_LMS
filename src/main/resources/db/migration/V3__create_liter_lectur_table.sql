CREATE TABLE literature_to_lectures (
                              id SERIAL PRIMARY KEY,
                              literature_id INTEGER NOT NULL,
                              lecture_id INTEGER NOT NULL,
                              CONSTRAINT "FK_user_id" FOREIGN KEY (literature_id)
                                  REFERENCES "literature" ("lit_id"),
                              CONSTRAINT "FK_site_id" FOREIGN KEY (lecture_id)
                                  REFERENCES "lectures" ("lect_id")
);

CREATE UNIQUE INDEX "UI_literature_to_lectures_literature_id_lectures_id"
    ON "literature_to_lectures"
        USING btree
        ("literature_id", "lecture_id");