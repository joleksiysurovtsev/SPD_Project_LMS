create table lectures
(
    id serial primary key not null,
    name_of_lecture varchar(255) not null,
    lecture_date timestamp not null,
    lector_name varchar(50) not null,
    lecture_type varchar(50) not null,
    duration_of_lesson int
);






