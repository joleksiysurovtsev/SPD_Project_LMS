create table lectures
(
    lect_id serial not null,
    name_of_lecture varchar(255) not null,
    lecture_date timestamp not null,
    lector_name varchar(50) not null,
    lecture_type varchar(50) not null,
    duration_of_lesson int
);

create unique index lectures_lect_id_uindex
    on lectures (lect_id);

alter table lectures
    add constraint lectures_pk
        primary key (lect_id);




