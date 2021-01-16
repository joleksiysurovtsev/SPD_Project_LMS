create table literature
(
	lit_id serial not null,
	type varchar(50) not null,
	title varchar(255) not null,
	author varchar(50) not null,
	date_was_added date default now() not null,
	genre varchar(50),
	published_in_year int,
	url_address varchar,
	issue_of_journal int,
	title_of_article varchar(255)
);

create unique index literature_lit_id_uindex
	on literature (lit_id);

alter table literature
	add constraint literature_pk
		primary key (lit_id);


