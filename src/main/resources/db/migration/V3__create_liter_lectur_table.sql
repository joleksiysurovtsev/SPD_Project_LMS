create table literatures_lecture
(
	lecturesID int not null,
	literatureID int not null,
	primary key (lecturesID, literatureID),
	constraint FK_lectures
		foreign key () references literatures_lecture
			on update cascade on delete cascade,
	constraint FK_literature
		foreign key () references literatures_lecture
			on update cascade on delete cascade
);



create index literatures_lecture__index
	on literatures_lecture ();


