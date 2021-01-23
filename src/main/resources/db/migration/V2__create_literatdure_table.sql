create table literature
(
	id serial primary key not null,
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



